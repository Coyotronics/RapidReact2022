package com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces;

import com.coyotronics.frc2022.subsystems.DischargeSubsystem;
import com.coyotronics.frc2022.subsystems.IntakeSubsystem;
import com.coyotronics.frc2022.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunDischarge extends CommandBase {
    private final DischargeSubsystem discharge;
    private double seconds;
    public RunDischarge(DischargeSubsystem ss, double seconds) {
        this.discharge = ss;
        this.seconds = seconds;
        addRequirements(this.discharge);
    }
    public void initialize() {
        this.discharge.reset();
        this.discharge.shootDischarge(Constants.Shooter.kDischargeSpeedHighHub);
    }
    public void end(boolean interrupted) {
        this.discharge.stop();
    }
     public boolean isFinished() {
        return this.seconds * 30 >= seconds;
    }
}
