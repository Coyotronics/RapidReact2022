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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
  
    private double value1 = 0, value2 = 0;
  public DriveBase() {
    m_leftFrontMotor = new CANSparkMax(Constants.Drive.kLeftFrontMotor, MotorType.kBrushed);
    m_leftBackMotor = new CANSparkMax(Constants.Drive.kLeftBackMotor, MotorType.kBrushed);
    m_rightFrontMotor = new CANSparkMax(Constants.Drive.kRightFrontMotor, MotorType.kBrushed);
    m_rightBackMotor = new CANSparkMax(Constants.Drive.kRightBackMotor, MotorType.kBrushed);

    CANSparkMax[] motors = {m_leftFrontMotor, m_leftBackMotor, m_rightFrontMotor, m_rightBackMotor}; //first 2 = left, second 2 = right;

    for(CANSparkMax motor : motors){
      motor.restoreFactoryDefaults();
    }

    //group motors
    leftMotors = new MotorControllerGroup(m_leftFrontMotor, m_leftBackMotor);
    rightMotors = new MotorControllerGroup(m_rightFrontMotor, m_rightBackMotor);

    rightMotors.setInverted(true);

    //follow motors
    // m_leftBackMotor.follow(m_leftFrontMotor);
    // m_rightBackMotor.follow(m_rightFrontMotor);

    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
    
  }
  public void tDrive(double left, double right) { //[-1...0...1] speed, speed, squareInputs = true
    differentialDrive.tankDrive(left, right, true);
  }
  public void aDrive(double translational, double rotational) {
    // differentialDrive.arcadeDrive(translational, rotational);
    value1 = translational;
    value2 = rotational;
  }

  
 @Override
  public void periodic() {
    SmartDashboard.putString("WORKING", "WORKING MOTOR");
   differentialDrive.arcadeDrive(value1, value2);
   SmartDashboard.putNumber("Trans", value1);
   SmartDashboard.putNumber("Rot", value2);
  }
    
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
