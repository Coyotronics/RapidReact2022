// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveBase extends SubsystemBase {
    private DifferentialDrive m_myRobot;
    private static final int leftDeviceID = 1; 
    private static final int rightDeviceID = 2;
    private CANSparkMax m_leftMotor;
    private CANSparkMax m_rightMotor;
  


  /** Creates a new ExampleSubsystem. */
  public DriveBase() {
    m_leftMotor = new CANSparkMax(leftDeviceID, MotorType.kBrushless);
    m_rightMotor = new CANSparkMax(rightDeviceID, MotorType.kBrushless);

    m_leftMotor.restoreFactoryDefaults();
    m_rightMotor.restoreFactoryDefaults();

    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
  }

//   @Override
//   public void periodic() {
//     m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());
//   }
    public void drive(double left, double right) {
        m_myRobot.tankDrive(left, right);
    }
    

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
