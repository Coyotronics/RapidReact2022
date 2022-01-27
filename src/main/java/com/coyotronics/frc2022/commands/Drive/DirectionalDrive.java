package com.coyotronics.frc2022.commands.Drive;

import com.coyotronics.frc2022.RobotContainer;
import com.coyotronics.frc2022.subsystems.DriveBase;
import com.coyotronics.frc2022.util.Util;
import com.coyotronics.frc2022.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.XboxController;
public class DirectionalDrive  {
  public static void drive(DriveBase driveBase) {
    double translation = RobotContainer.controller.getRawAxis(1); //[-1...0...1]
    double rotation = RobotContainer.controller.getRawAxis(4);

    translation = Util.MultiDeadBand(translation);
    rotation = Util.MultiDeadBand(rotation);
    
    if(Constants.ksafetyMode) {
      rotation *= Constants.kSafetyMultiplier;
      translation *= Constants.kSafetyMultiplier;
    }

    SmartDashboard.putNumber("TIME", System.currentTimeMillis());

    SmartDashboard.putNumber("Xbox Left Y", translation);
    SmartDashboard.putNumber("Xbox Right X", rotation);

    driveBase.aDrive(translation, rotation);   
  }
}
