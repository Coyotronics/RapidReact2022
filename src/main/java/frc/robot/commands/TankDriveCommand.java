package frc.robot.commands;

import frc.robot.RobotContainer;

import frc.robot.subsystems.DriveBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TankDriveCommand extends CommandBase {
  private final DriveBase driveBase;

  public TankDriveCommand(DriveBase db) {
    this.driveBase = db;
    addRequirements(db);
  }

  public void initialize() {}

  public void execute() {
      GenericHID gm = new GenericHID(1);

      double left = RobotContainer.controller.getRawAxis(Constants.LEFT_Y_AXIS);
      double right = RobotContainer.controller.getRawAxis(Constants.RIGHT_Y_AXIS);

      if(Math.abs(left) < 0.01) left = 0;
      if(Math.abs(right) < 0.01) right = 0;

      driveBase.drive(left, right);
  }

  public void end(boolean interrupted) {}


  public boolean isFinished() {
    return false;
  }
}
