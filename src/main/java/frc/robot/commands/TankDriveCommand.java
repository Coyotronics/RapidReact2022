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
      // GenericHID gm = new GenericHID(1);
      // leftY: up down, rightX: left righ
      double yAxis = RobotContainer.controller.getRawAxis(Constants.LEFT_Y_AXIS);
      double xAxis = RobotContainer.controller.getRawAxis(Constants.RIGHT_X_AXIS);

      if(Math.abs(yAxis) < 0.01) yAxis = 0;
      if(Math.abs(xAxis) < 0.01) xAxis = 0;

    
      if(Math.abs(xAxis) == 1)
        yAxis *= .97;
      double moveVector = Math.sqrt(yAxis * yAxis + xAxis + xAxis);
      double fowardDifferential = yAxis / (yAxis + xAxis);
      rotation(fowardDifferential * moveVector, Math.abs(1 - fowardDifferential * moveVector));


      // if(xAxis == 0 && yAxis != 0) {
      //   tank(yAxis);
      // } else if(xAxis != 0 && yAxis == 0) {
      //   if(xAxis > 0) right(xAxis);
      //   else left(xAxis);
      // } else {
      //   rotation(left, right);
      // }
  }

  public void tank(double axisweight) {
    //>0  = foward, <0 = backward
    driveBase.drive(axisweight, axisweight);
  }
  public void left(double axisweight) {
    axisweight = Math.abs(axisweight);
    driveBase.drive(-axisweight, axisweight);
  }
  public void right(double axisweight) {
    axisweight = Math.abs(axisweight);
    driveBase.drive(axisweight, -axisweight);
  }
  public void rotation(double left, double right) {
    driveBase.drive(left, right);
  }


  public void end(boolean interrupted) {}


  public boolean isFinished() {
    return false;
  }
}
