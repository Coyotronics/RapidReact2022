package com.coyotronics.frc2022.commands.Drive;

import javax.xml.XMLConstants;

import com.coyotronics.frc2022.Constants;
import com.coyotronics.frc2022.RobotContainer;
import com.coyotronics.frc2022.subsystems.DriveBase;
import com.coyotronics.frc2022.util.Util;

import edu.wpi.first.wpilibj.XboxController;
public class TankDrive  {
 public static void drive(DriveBase driveBase) {
    double leftMotion = RobotContainer.controller.getRawAxis(XboxController.Axis.kLeftY.value); //[-1...0...1]
    double rightMotion = RobotContainer.controller.getRawAxis(XboxController.Axis.kRightY.value);

    leftMotion = Util.MultiDeadBand(leftMotion);
    rightMotion = Util.MultiDeadBand(rightMotion);
    
    if(Constants.ksafetyMode) {
      leftMotion *= Constants.kSafetyMultiplier;
      rightMotion *= Constants.kSafetyMultiplier;
    }

    driveBase.setMotorSpeeds(leftMotion, rightMotion);   
  }
}
