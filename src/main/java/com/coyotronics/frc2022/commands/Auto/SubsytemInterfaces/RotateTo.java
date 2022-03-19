package com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;

public class RotateTo extends CommandBase {
    private final DriveBaseSubsystem driveBase;
    private final double rotatePerThread =  1.15;
    private double speed = 0.25;
    private double degreesToRotate;
    private double degreesRotated = 0;
    public double AUCSpeed(double degrees) {
        if(degrees <= 45) {
            return Math.max(degrees / 45 * speed, 0.2);
        } else
            return speed;
    }

    public RotateTo(DriveBaseSubsystem db, double degrees) {
        this.driveBase = db;
        this.degreesToRotate = degrees;
        this.degreesRotated = 0;
        addRequirements(this.driveBase);
    }
    public void initialize() {
        this.driveBase.stop();    
    }
    public boolean isDone() {
        return Math.abs(degreesRotated - degreesToRotate) < 3;
    }
    public void execute() {
        if(isDone()) return;
        double speedMove = AUCSpeed(Math.abs(degreesRotated - degreesToRotate));
        if(degreesToRotate > 0){
            this.driveBase.arcadeDriveAuto(0, -speedMove);
            degreesRotated += (speedMove / speed) * rotatePerThread;
        }
        else{
            this.driveBase.arcadeDriveAuto(0, speedMove);
            degreesRotated -= (speedMove / speed) * rotatePerThread;
        }
    }
    public void end(boolean interrupted) {
        this.driveBase.stop();
    }
     public boolean isFinished() {
        return isDone();
    }
}
