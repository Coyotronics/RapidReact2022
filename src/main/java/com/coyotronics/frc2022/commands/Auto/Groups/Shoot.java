package com.coyotronics.frc2022.commands.Auto.Groups;

import com.coyotronics.frc2022.subsystems.IntakeSubsystem;
import com.coyotronics.frc2022.subsystems.TransportSubsystem;
import com.coyotronics.frc2022.subsystems.DischargeSubsystem;
import com.coyotronics.frc2022.Constants;
import com.coyotronics.frc2022.Constants.Intake;
import com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces.RunDischarge;
import com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces.RunTransport;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Shoot extends SequentialCommandGroup {
    public Shoot(TransportSubsystem ts, DischargeSubsystem ds, double seconds) { 
        
        addCommands(
            new RunTransport(ts, 0.1, true),
            new ParallelCommandGroup(
                new RunDischarge(ds, seconds),
                new RunTransport(ts, seconds, false)
            )
        );
    }
}
