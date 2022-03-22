package com.coyotronics.frc2022.commands.Auto.Sequences;
import com.coyotronics.frc2022.commands.Auto.Groups.DriveAndIntake;
import com.coyotronics.frc2022.commands.Auto.Groups.ReleaseIntake;
import com.coyotronics.frc2022.commands.Auto.Groups.Shoot;
import com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces.DriveTo;
import com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces.RotateTo;
import com.coyotronics.frc2022.subsystems.DischargeSubsystem;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;
import com.coyotronics.frc2022.subsystems.IntakeSubsystem;
import com.coyotronics.frc2022.subsystems.TransportSubsystem;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class TwoBallAuto extends SequentialCommandGroup {
    public TwoBallAuto(DriveBaseSubsystem drive, DischargeSubsystem shooter, IntakeSubsystem intake, TransportSubsystem transport) {
        addCommands(
            new Shoot(transport, shooter, 3),
            new ReleaseIntake(drive),
            new DriveTo(drive, -5),
            new RotateTo(drive, 180),
            new DriveAndIntake(drive, intake, transport, 5, 3),
            new RotateTo(drive, 180),
            new DriveTo(drive, 8),
            new Shoot(transport, shooter, 3)
        );
    }
}