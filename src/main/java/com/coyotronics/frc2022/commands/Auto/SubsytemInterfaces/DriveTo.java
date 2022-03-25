package com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces;
import com.coyotronics.frc2022.Constants.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;
import com.coyotronics.frc2022.subsystems.GryoSubsystem;
import com.kauailabs.navx.ftc.AHRS;
import com.kauailabs.navx.ftc.navXPIDController;

public class DriveTo extends CommandBase { //centimeters
    private final DriveBaseSubsystem driveBase;
    private final double movePerThread =  0.0405;
    private double speed = 0.25;
    private double radiusToMove;
    private double radiusMoved = 0;

    private navXPIDController yawPIDController;

    private final byte NAVX_DEVICE_UPDATE_RATE_HZ = 50;
    private final double TARGET_ANGLE_DEGREES = 0.0;
    private final double TOLERANCE_DEGREES = 2.0;
    private final double MIN_MOTOR_OUTPUT_VALUE = -1.0;
    private final double MAX_MOTOR_OUTPUT_VALUE = 1.0;
    private final double YAW_PID_P = 0.005;
    private final double YAW_PID_I = 0.0;
    private final double YAW_PID_D = 0.0;
    navXPIDController.PIDResult yawPIDResult;
    GryoSubsystem gyro;
    public double AUCSpeed(double distance) {
        if(distance <= 5) {
            return Math.max(distance / 5 * speed, 0.2);
        } else
            return speed;
    }
    public DriveTo(DriveBaseSubsystem db, GryoSubsystem gyro,double radius) {
        this.driveBase = db;
        this.radiusToMove = radius;
        this.gyro = gyro;
        addRequirements(this.driveBase);
    }
    public void initialize() {
        this.driveBase.stop();    
        this.radiusMoved = 0;
        yawPIDController = new navXPIDController(gyro, navXPIDController.navXTimestampedDataSource.YAW);
        yawPIDResult = new navXPIDController.PIDResult();
                /* Configure the PID controller */
                yawPIDController.setSetpoint(TARGET_ANGLE_DEGREES);
                yawPIDController.setContinuous(true);
                yawPIDController.setOutputRange(MIN_MOTOR_OUTPUT_VALUE, MAX_MOTOR_OUTPUT_VALUE);
                yawPIDController.setTolerance(navXPIDController.ToleranceType.ABSOLUTE, TOLERANCE_DEGREES);
                yawPIDController.setPID(YAW_PID_P, YAW_PID_I, YAW_PID_D);
                yawPIDController.enable(true);
                navx_device.zeroYaw();
    }
    public double limit(double a) {
        return Math.min(Math.max(a, MIN_MOTOR_OUTPUT_VALUE), MAX_MOTOR_OUTPUT_VALUE);
    }
    public boolean isDone() {
        return Math.abs(this.radiusMoved - this.radiusToMove) < (0.05);
    }
    public void execute() {
        if(isDone()) return;
        double speedMove = AUCSpeed(Math.abs(radiusToMove - radiusMoved));
        if (yawPIDController.isNewUpdateAvailable(yawPIDResult)) {
            if (yawPIDResult.isOnTarget()) {
                driveBase.tankDrive(limit(speedMove), limit(speedMove));
            } else {
                double output = yawPIDResult.getOutput();
                driveBase.tankDrive(limit(speedMove + output), limit(speedMove - output));

            }
        }
        if(radiusToMove > 0){
            radiusMoved += (speedMove / speed) * movePerThread;
        }
        else{
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
