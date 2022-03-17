package com.coyotronics.frc2022.commands.Auto;
import com.coyotronics.frc2022.Constants.Auto;
import com.coyotronics.frc2022.subsystems.DischargeSubsystem;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;
import com.coyotronics.frc2022.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoSequence extends SequentialCommandGroup {
    public AutoSequence(DriveBaseSubsystem drive, DischargeSubsystem shooter, IntakeSubsystem intake) {
        addCommands(
            new RotateTo(drive, 180),
            new DriveTo(drive, 10)
            


        );
    }
}
