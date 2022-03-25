package com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;
import com.coyotronics.frc2022.subsystems.GryoSubsystem;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class RotateTo extends CommandBase {
    /** Creates a new TurnToNAngle. */
    public double turnAngle, currentAngle;
    private final DriveBaseSubsystem drivebase;
    private final GryoSubsystem gyro;
    double tolerance = 2;
    double kP = 0.03;

    public RotateTo(DriveBaseSubsystem driveBase, GryoSubsystem gyro, double angle) {

        this.turnAngle = gyro.getAngle() + angle;
        this.drivebase = driveBase;
        this.gyro = gyro;
        addRequirements(driveBase);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        currentAngle = gyro.getAngle();
        drivebase.stop();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        currentAngle = gyro.getAngle();
        double error = turnAngle - currentAngle;
        double speed;
        speed = kP * error;
        speed = MathUtil.clamp(speed, -0.5, 0.5);
        drivebase.arcadeDrive(0, speed);

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivebase.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(Math.abs(currentAngle - turnAngle) < tolerance)
            return true;
        return false;
    }


}