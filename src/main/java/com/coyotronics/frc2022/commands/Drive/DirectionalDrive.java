package com.coyotronics.frc2022.commands.Drive;

import com.coyotronics.frc2022.RobotContainer;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;
import com.coyotronics.frc2022.util.Util;
import com.coyotronics.frc2022.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DirectionalDrive  {
  public static void drive(DriveBaseSubsystem driveBase) {
    double translation = RobotContainer.controller.getRawAxis(Constants.Controller.LEFT_STICK_Y); //[-1...0...1]
    // double rotation =/ RobotContainer.controller.getRawAxis(Constants.Controller.RIGHT_STICK_X) * 0.85;
    double rotation = RobotContainer.controller.getRawAxis(Constants.Controller.RIGHT_STICK_X) * 0.85;

    translation = Util.MultiDeadBand(translation);
    rotation = Util.MultiDeadBand(-rotation);
    
    if(Constants.ksafetyMode) {
      rotation *= Constants.kSafetyMultiplier;
      translation *= Constants.kSafetyMultiplier;
    }

    // SmartDashboard.putNumber("TIME", System.currentTimeMillis());
    // SmartDashboard.putNumber("Xbox Left Y", translation);
    // SmartDashboard.putNumber("Xbox Right X", rotation);

    driveBase.arcadeDrive(translation, rotation);   
  }
}
