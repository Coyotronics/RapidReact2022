package com.coyotronics.frc2022.commands.Auto.Groups;
import com.coyotronics.frc2022.Constants.Drive;
import com.coyotronics.frc2022.Constants.Intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;
import com.coyotronics.frc2022.subsystems.IntakeSubsystem;
import com.coyotronics.frc2022.subsystems.TransportSubsystem;
import com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces.DriveTo;
import com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces.RunIntake;
import com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces.RunTransport;

public class DriveAndIntake extends ParallelRaceGroup { 
    public DriveAndIntake(DriveBaseSubsystem driveBase, IntakeSubsystem intake, TransportSubsystem transport, double distance, double time) {
        addCommands(
            new RunIntake(intake, time), 
            new RunTransport(transport, time, false),
            new DriveTo(driveBase, distance)
        );
    }
}
