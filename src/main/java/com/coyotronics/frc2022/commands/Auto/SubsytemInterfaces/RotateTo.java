package com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;
import com.coyotronics.frc2022.subsystems.GryoSubsystem;


import com.kauailabs.navx.ftc.AHRS;
import com.kauailabs.navx.ftc.navXPIDController;
public class RotateTo extends CommandBase {
    private final DriveBaseSubsystem driveBase;
    private final GryoSubsystem gyro;
    private final double rotatePerThread =  1.15;
    private double degreesToRotate;
    private double error = 2;
    private final byte NAVX_DEVICE_UPDATE_RATE_HZ = 50;

    private final double TARGET_ANGLE_DEGREES = 90.0;
    private final double TOLERANCE_DEGREES = 2.0;
    private final double MIN_MOTOR_OUTPUT_VALUE = -1.0;
    private final double MAX_MOTOR_OUTPUT_VALUE = 1.0;
    private final double YAW_PID_P = 0.005;
    private final double YAW_PID_I = 0.0;
    private final double YAW_PID_D = 0.0;
    navXPIDController.PIDResult yawPIDResult;
    private navXPIDController yawPIDController;
    public RotateTo(DriveBaseSubsystem db, GryoSubsystem gryo, double degrees) {
        this.driveBase = db;
        this.gyro = gryo;
        this.degreesToRotate = (gyro.getAngle() + degrees) % 360;
        addRequirements(this.driveBase);
    }
    public void initialize() {
        this.driveBase.stop();    
        yawPIDController = new navXPIDController(gyro.getGyro(),
                                    navXPIDController.navXTimestampedDataSource.YAW);
        yawPIDResult = new navXPIDController.PIDResult();
        yawPIDController.setSetpoint(TARGET_ANGLE_DEGREES);
        yawPIDController.setContinuous(true);
        yawPIDController.setOutputRange(MIN_MOTOR_OUTPUT_VALUE, MAX_MOTOR_OUTPUT_VALUE);
        yawPIDController.setTolerance(navXPIDController.ToleranceType.ABSOLUTE, TOLERANCE_DEGREES);
        yawPIDController.setPID(YAW_PID_P, YAW_PID_I, YAW_PID_D);

    }
    public boolean isDone() {
        return Math.abs(this.gyro.getAngle() - degreesToRotate) < error;
    }
    public void execute() {
        if(isDone()) return;
        if (yawPIDController.isNewUpdateAvailable(yawPIDResult)) {
            if (yawPIDResult.isOnTarget()) {
                driveBase.stop();
            } else {
                double output = yawPIDResult.getOutput();
                driveBase.tankDrive(output, -output);
            }
        }
        
    }
    public void end(boolean interrupted) {
        this.driveBase.stop();
    }
     public boolean isFinished() {
        return isDone();
    }
    
}

