package com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces;
import com.coyotronics.frc2022.Constants.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;

public class DriveTo extends CommandBase { //centimeters
    private final DriveBaseSubsystem driveBase;
    private final double movePerThread =  0.037;
    private int iterationsCompleted = 0;
    private double speed = 0.5;
    private double radiusToMove;

    public DriveTo(DriveBaseSubsystem db, double radius) {
        this.driveBase = db;
        this.radiusToMove = radius;
        addRequirements(this.driveBase);
    }
    public void initialize() {
        this.iterationsCompleted = 0;
        this.driveBase.stop();    
    }
    public boolean isDone() {
        return Math.abs(this.iterationsCompleted * movePerThread - radiusToMove) < (movePerThread + 1);
    }
    public void execute() {
        if(isDone()) return;
        ++iterationsCompleted;
        if(radiusToMove > 0)
            this.driveBase.arcadeDrive(-speed, 0);
        else
            this.driveBase.arcadeDrive(speed, 0);
    }
    public void end(boolean interrupted) {
        this.driveBase.stop();
    }
     public boolean isFinished() {
        return Math.abs(this.iterationsCompleted * movePerThread - radiusToMove) < (movePerThread + 1);
    }
}
