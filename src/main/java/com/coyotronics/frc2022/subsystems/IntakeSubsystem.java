package com.coyotronics.frc2022.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.coyotronics.frc2022.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class IntakeSubsystem extends SubsystemBase {
  
  private CANSparkMax m_intakeMotor;

  public IntakeSubsystem() {
    m_intakeMotor = new CANSparkMax(Constants.Intake.kIntakeMotor, MotorType.kBrushless);
  }
  public void reset() {
    this.m_intakeMotor.set(0);
  }
  public void stop() {
    this.reset();
  }
  public void runIntake(double speed) {
    this.m_intakeMotor.set(speed);
  }

  @Override
  public void periodic() {}
  @Override
  public void simulationPeriodic() {}
}
