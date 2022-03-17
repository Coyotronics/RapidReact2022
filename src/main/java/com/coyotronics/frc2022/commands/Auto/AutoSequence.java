package com.coyotronics.frc2022.commands.Auto;
import com.coyotronics.frc2022.Constants.Auto;
import com.coyotronics.frc2022.Constants.Drive;
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

public class AutoSequence extends SequentialCommandGroup {
    public AutoSequence(DriveBaseSubsystem drive, DischargeSubsystem shooter, IntakeSubsystem intake, TransportSubsystem transport) {
        addCommands(
            new ReleaseIntake(drive),
            new RotateTo(drive, 180),
            new DriveTo(drive, 10),
            new DriveAndIntake(drive, intake, transport, 2, 3000),
            new RotateTo(drive, 180),
            new DriveTo(drive, 12),
            new Shoot(transport, shooter, 5)
        );
    }
}
