package com.coyotronics.frc2022.subsystems;

import com.coyotronics.frc2022.Constants;

public class TransportSubsystem extends ToggleSubsystem {
  
    public TransportSubsystem() {
      super(Constants.Shooter.kStorageMotor, -Constants.Shooter.kTransportSpeed);
    }
    public void runFoward() {
      super.toggleSpeed = -Constants.Shooter.kTransportSpeed;
      super.run();
    }
    public void runBackward() {
      super.toggleSpeed = Constants.Shooter.kTransportSpeed;
      super.run();
    }
}

