package com.coyotronics.frc2022.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.coyotronics.frc2022.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class TransportSubsystem extends SubsystemBase {
  
  private CANSparkMax m_transportMotor;

  public TransportSubsystem() {
    m_transportMotor = new CANSparkMax(Constants.Shooter.kStorageMotor, MotorType.kBrushless);

    m_transportMotor.restoreFactoryDefaults();
  }
  public void reset() {
    this.m_transportMotor.set(0);
  }
  public void stop() {
    this.reset();
  }
  public void runTransport(double speed) {
    this.m_transportMotor.set(speed);
  }

  @Override
  public void periodic() {}
  @Override
  public void simulationPeriodic() {}
}
