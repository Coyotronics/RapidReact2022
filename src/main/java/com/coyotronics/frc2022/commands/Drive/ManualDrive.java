package com.coyotronics.frc2022.commands.Drive;

import com.coyotronics.frc2022.RobotContainer;
import com.coyotronics.frc2022.subsystems.DriveBase;
import com.coyotronics.frc2022.util.Util;
import com.coyotronics.frc2022.Constants;

import org.opencv.core.Mat;

import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualDrive extends CommandBase {
  private final DriveBase driveBase;

  public ManualDrive(DriveBase db) {
    this.driveBase = db;
    addRequirements(db);
  }

  public void initialize() {
      driveBase.aDrive(0, 0);
  }

    public void execute() {
        // if(Constants.Vars.cDriveType == Constants.Drive.DriveType.ARCADE)
        //     DirectionalDrive.drive(this.driveBase);
        // else
        //     TankDrive.drive(this.driveBase);  
        double translation = RobotContainer.controller.getRawAxis(1); //[-1...0...1]
        double rotation = RobotContainer.controller.getRawAxis(4);
    
       SmartDashboard.putNumber("Trans2", translation);
       SmartDashboard.putNumber("Rot2", rotation);
    
        translation = Util.MultiDeadBand(translation);
        rotation = Util.MultiDeadBand(rotation);
        
        if(Constants.ksafetyMode) {
          rotation *= Constants.kSafetyMultiplier;
          translation *= Constants.kSafetyMultiplier;
        }
    
        driveBase.aDrive(translation, rotation); 
    }
    

    public void switchDriveTypes() {
        if(Constants.Vars.cDriveType == Constants.Drive.DriveType.ARCADE) {
            Constants.Vars.cDriveType = Constants.Drive.DriveType.TANK;
        } else {
            Constants.Vars.cDriveType = Constants.Drive.DriveType.ARCADE;
        }
        SmartDashboard.putString("CHANGED DRIVE TYPE -> ", Constants.Vars.cDriveType.toString());
    }

    public void end(boolean interrupted) {
        driveBase.aDrive(0, 0);
    }
     public boolean isFinished() {
        return false;
    }
}
