package com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces;
import com.coyotronics.frc2022.Constants.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;

public class DriveTo extends CommandBase { //centimeters
    private final DriveBaseSubsystem driveBase;
    private final double movePerThread =  0.0405;
    private double speed = 0.25;
    private double radiusToMove;
    private double radiusMoved = 0;
    public double AUCSpeed(double distance) {
        if(distance <= 5) {
            return Math.max(distance / 5 * speed, 0.2);
        } else
            return speed;
    }
    public DriveTo(DriveBaseSubsystem db, double radius) {
        this.driveBase = db;
        this.radiusToMove = radius;

        addRequirements(this.driveBase);
    }
    public void initialize() {
        this.radiusMoved = 0;
        this.driveBase.stop();    
    }
    public boolean isDone() {
        return Math.abs(this.radiusMoved - this.radiusToMove) < (0.05);
    }
    public void execute() {
        if(isDone()) return;
        double speedMove = AUCSpeed(Math.abs(radiusToMove - radiusMoved));
        if(radiusToMove > 0){
            this.driveBase.arcadeDriveAuto(-speedMove, 0);
            radiusMoved += (speedMove / speed) * movePerThread;
        }
        else{
            this.driveBase.arcadeDriveAuto(speedMove, 0);
            radiusMoved -= (speedMove / speed) * movePerThread;
        }
    }
    public void end(boolean interrupted) {
        this.driveBase.stop();
    }
     public boolean isFinished() {
        return isDone();
    }
}
