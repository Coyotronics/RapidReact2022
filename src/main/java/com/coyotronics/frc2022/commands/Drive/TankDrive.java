package com.coyotronics.frc2022.commands.Drive;

import com.coyotronics.frc2022.RobotContainer;
import com.coyotronics.frc2022.subsystems.DriveBase;
import com.coyotronics.frc2022.util.Util;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TankDrive extends CommandBase {
  private final DriveBase driveBase;

  public TankDrive(DriveBase db) {
    this.driveBase = db;
    addRequirements(db);
  }

  public void initialize() {}

  public void execute() {
      double left = RobotContainer.controller.getRawAxis(XboxController.Axis.kLeftY.value); //[-1...0...1]
      double right = RobotContainer.controller.getRawAxis(XboxController.Axis.kRightY.value);

      Util.OffsetControllerDrift(left);
      Util.OffsetControllerDrift(right);

      driveBase.drive(left, right);
  }

  public void end(boolean interrupted) {}


  public boolean isFinished() {
    return false;
  }
}
