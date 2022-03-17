package com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces;

import com.coyotronics.frc2022.Constants.Drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;

import org.xml.sax.ext.DeclHandler;

public class RotateTo extends CommandBase {
    private final DriveBaseSubsystem driveBase;
    private final double rotatePerThread =  2.5;
    private int iterationsCompleted = 0;
    private double speed = 0.5;
    private double degreesToRotate;

    public RotateTo(DriveBaseSubsystem db, double degrees) {
        this.driveBase = db;
        this.degreesToRotate = degrees;
        addRequirements(this.driveBase);
    }
    public void initialize() {
        this.iterationsCompleted = 0;
        this.driveBase.stop();    
    }
    public boolean isDone() {
        return Math.abs(this.iterationsCompleted * rotatePerThread - degreesToRotate) < (rotatePerThread + 1);
    }
    public void execute() {
        if(isDone()) return;
        ++iterationsCompleted;
        if(degreesToRotate > 0)
            this.driveBase.arcadeDrive(0, -speed);
        else
            this.driveBase.arcadeDrive(0, speed);
    }
    public void end(boolean interrupted) {
        this.driveBase.stop();
    }
     public boolean isFinished() {
         SmartDashboard.putNumber("Rotation", iterationsCompleted * rotatePerThread);
         SmartDashboard.putNumber("Degrees", degreesToRotate);
         SmartDashboard.putNumber("Bool", Math.abs(iterationsCompleted * rotatePerThread - degreesToRotate));


        return isDone();
    }
}
