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
    this.driveBase.setMotorSpeeds(0, 0);
  }

    public void execute() {
        if(Constants.Vars.cDriveType == Constants.Drive.DriveType.ARCADE)
            DirectionalDrive.drive(this.driveBase);
        else
            TankDrive.drive(this.driveBase);  
    }
    public void end(boolean interrupted) {
        this.driveBase.setMotorSpeeds(0, 0);
    }
     public boolean isFinished() {
        return false;
    }
}
