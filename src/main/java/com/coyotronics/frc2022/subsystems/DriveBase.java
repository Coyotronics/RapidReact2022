// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.coyotronics.frc2022.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.coyotronics.frc2022.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
public class DriveBase extends SubsystemBase {
    private DifferentialDrive differentialDrive;

    private CANSparkMax m_rightFrontMotor;
    private CANSparkMax m_rightBackMotor;
    private CANSparkMax m_leftFrontMotor; 
    private CANSparkMax m_leftBackMotor;

    private MotorControllerGroup leftMotors;
    private MotorControllerGroup rightMotors;

  public DriveBase() {
    m_leftFrontMotor = new CANSparkMax(Constants.Drive.kLeftFrontMotor, MotorType.kBrushless);
    m_leftBackMotor = new CANSparkMax(Constants.Drive.kLeftBackMotor, MotorType.kBrushless);
    m_rightFrontMotor = new CANSparkMax(Constants.Drive.kRightFrontMotor, MotorType.kBrushless);
    m_rightBackMotor = new CANSparkMax(Constants.Drive.kRightBackMotor, MotorType.kBrushless);

    CANSparkMax[] motors = {m_leftFrontMotor, m_leftBackMotor, m_rightFrontMotor, m_rightBackMotor}; //first 2 = left, second 2 = right;

    for(CANSparkMax motor : motors)
      motor.restoreFactoryDefaults();

    //group motors
    leftMotors = new MotorControllerGroup(m_leftFrontMotor, m_leftBackMotor);
    rightMotors = new MotorControllerGroup(m_rightFrontMotor, m_rightBackMotor);

    //follow motors
    // m_leftBackMotor.follow(m_leftFrontMotor);
    // m_rightBackMotor.follow(m_rightFrontMotor);

    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
    
  }
  public void drive(double left, double right) { //[-1...0...1] speed, speed, squareInputs = true
    differentialDrive.tankDrive(left, right, true);
  }

  
 @Override
  public void periodic() {
   
  }
    
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
