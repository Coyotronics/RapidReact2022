package com.coyotronics.frc2022.commands.Drive;

import com.coyotronics.frc2022.subsystems.DriveBase;
import com.coyotronics.frc2022.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwitchDriveType extends CommandBase {
    public DriveBase db;
    public SwitchDriveType(DriveBase db) { 
        this.db = db;
    }
    public void initialize() {
        if(Constants.Vars.cDriveType == Constants.Drive.DriveType.ARCADE) {
            Constants.Vars.cDriveType = Constants.Drive.DriveType.TANK;
        } else {
            Constants.Vars.cDriveType = Constants.Drive.DriveType.ARCADE;
        }
        this.db.setMotorSpeeds(0, 0);
        SmartDashboard.putString("DRIVE TYPE", Constants.Vars.cDriveType.toString());
    }
    public boolean isFinished() { return true;}
}