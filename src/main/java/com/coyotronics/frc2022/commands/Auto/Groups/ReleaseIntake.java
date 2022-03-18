package com.coyotronics.frc2022.commands.Auto.Groups;

import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces.DriveTo;
public class ReleaseIntake extends SequentialCommandGroup{
    public ReleaseIntake(DriveBaseSubsystem db) {
        addCommands(
            new DriveTo(db, 3),
            new DriveTo(db, -3),
            new DriveTo(db, 3),
            new DriveTo(db, -3)
        );
    }
}
