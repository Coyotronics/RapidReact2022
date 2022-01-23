package com.coyotronics.frc2022.commands.Drive;

import com.coyotronics.frc2022.RobotContainer;
import com.coyotronics.frc2022.subsystems.DriveBase;
import com.coyotronics.frc2022.util.Util;

import edu.wpi.first.wpilibj.XboxController;
public class DirectionalDrive  {
  public static void drive(DriveBase driveBase) {
    double translation = RobotContainer.controller.getRawAxis(XboxController.Axis.kLeftY.value); //[-1...0...1]
    double rotation = RobotContainer.controller.getRawAxis(XboxController.Axis.kRightX.value);

    translation = Util.MultiDeadBand(translation);
    rotation = Util.MultiDeadBand(rotation);
    
    driveBase.aDrive(translation, rotation);   
  }
}
