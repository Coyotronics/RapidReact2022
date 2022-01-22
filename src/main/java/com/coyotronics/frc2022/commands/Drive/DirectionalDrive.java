package com.coyotronics.frc2022.commands.Drive;

import com.coyotronics.frc2022.RobotContainer;
import com.coyotronics.frc2022.subsystems.DriveBase;
import com.coyotronics.frc2022.util.Util;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DirectionalDrive extends CommandBase {
  private final DriveBase driveBase;

  public DirectionalDrive(DriveBase db) {
    this.driveBase = db;
    addRequirements(db);
  }

  public void initialize() {}

    public void execute() {
        double translation = RobotContainer.controller.getRawAxis(XboxController.Axis.kLeftY.value); //[-1...0...1]
        double rotation = RobotContainer.controller.getRawAxis(XboxController.Axis.kRightX.value);

        translation = Util.OffsetControllerDrift(translation);
        rotation = Util.OffsetControllerDrift(rotation);
        
        //angle above the horizontal on a foward v rotational graph
        double angle = 0;
        
        if(Math.abs(rotation) + Math.abs(translation) > 0)
            angle = (Math.abs(rotation) / (Math.abs(rotation) + Math.abs(translation)));

        double motorOnePower = Math.abs(translation);
        double motorTwoPower = 1 - (2 * angle); //value between [-1...0...1]. 
                                                // -1 means no Y
                                                // 0 means X = Y
                                                // 1 means no X

        if(translation < 0) {
            motorTwoPower = -motorTwoPower;
            motorOnePower = -motorOnePower;
        }

        if(rotation < 0) {
            //swap
            motorTwoPower = Util.swap(motorOnePower, motorOnePower = motorTwoPower);
        }

        driveBase.drive(motorOnePower, motorTwoPower);
    }


  public void end(boolean interrupted) {}


  public boolean isFinished() {
    return false;
  }
}
