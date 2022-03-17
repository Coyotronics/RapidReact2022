package com.coyotronics.frc2022.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.coyotronics.frc2022.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class TransportSubsystem extends ToggleSubsystem {
  
    public TransportSubsystem() {
      super(Constants.Shooter.kStorageMotor, -Constants.Shooter.kTransportSpeed);
    }
}
