package com.coyotronics.frc2022.commands.Auto;

import com.coyotronics.frc2022.Constants.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;

public class RotateTo extends CommandBase {
    private final DriveBaseSubsystem driveBase;
    private final double rotatePerThread =  5;
    private int iterationsCompleted = 0;
    private double speed = 0.25;
    private double degreesToRotate;

    public RotateTo(DriveBaseSubsystem db, double degrees) {
        this.driveBase = db;
        this.degreesToRotate = degrees;
        addRequirements(this.driveBase);
    }
    public void initialize() {
        this.driveBase.stop();    
    }
    public void execute() {
        ++iterationsCompleted;
        this.driveBase.arcadeDrive(0, speed);
    }
    public void end(boolean interrupted) {
        this.driveBase.stop();
    }
     public boolean isFinished() {
        return Math.abs(this.iterationsCompleted * rotatePerThread - degreesToRotate) < (2 * rotatePerThread + 1);
    }
}
