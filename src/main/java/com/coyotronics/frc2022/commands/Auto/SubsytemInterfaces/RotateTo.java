package com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;
import com.coyotronics.frc2022.subsystems.GryoSubsystem;

public class RotateTo extends CommandBase {
    private final DriveBaseSubsystem driveBase;
    private final GryoSubsystem gyro;
    private final double rotatePerThread =  1.15;
    private double speed = 0.25;
    private double degreesToRotate;
    private double currentDegrees = 0;
    private double error = 2;
    public RotateTo(DriveBaseSubsystem db, GryoSubsystem gryo, double degrees) {
        this.driveBase = db;
        this.degreesToRotate = degrees;
        addRequirements(this.driveBase);
    }
    public void initialize() {
        this.currentDegrees = 0;
        this.driveBase.stop();    
    }
    public boolean isDone() {
        return Math.abs(degreesRotated - degreesToRotate) < 3;
    }
    public void execute() {
        if(isDone()) return;
        
    }
    public void end(boolean interrupted) {
        this.driveBase.stop();
    }
     public boolean isFinished() {
        return isDone();
    }
    
}

function rotateToAngle(targetAngle):
    error = targetAngle - gyroAngle # check out wpilib documentation for getting the angle from the gyro
    if error > threshold
        this.rotation =  error*kP
        return False
    else:
        this.rotation = 0
        return True

function move(fwd, rotation):
    // This function allows for joystick input
    this.fwd = fwd
    this.rotation = rotation

function execute():
    // Execute function that should be called every loop
    this.robotdrive.drive(this.fwd, this.rotation)

    this.fwd = 0
    this.rotation = 0