package com.coyotronics.frc2022.commands.Auto;

import com.coyotronics.frc2022.subsystems.IntakeSubsystem;
import com.coyotronics.frc2022.subsystems.TransportSubsystem;
import com.coyotronics.frc2022.subsystems.DischargeSubsystem;
import com.coyotronics.frc2022.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Shoot extends CommandBase {
    private final DischargeSubsystem shooter;
    private final TransportSubsystem transport;
    private double seconds;
    public Shoot(TransportSubsystem ts, DischargeSubsystem ds, double seconds) {
        this.transport = ts;
        this.seconds = seconds;
        addRequirements(this.intake);
    }
    public void initialize() {
        this.intake.reset();
        this.intake.run();
    }
    public void end(boolean interrupted) {
        this.intake.stop();
    }
     public boolean isFinished() {
        return this.seconds * 30 >= seconds;
    }
}
