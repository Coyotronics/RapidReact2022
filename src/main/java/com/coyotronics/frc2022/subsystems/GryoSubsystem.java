package com.coyotronics.frc2022.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.coyotronics.frc2022.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;


public class GryoSubsystem extends SubsystemBase {
  
  AHRS gyro;
  int countt = 0;

  public GryoSubsystem() {
    try {
	    gyro =new AHRS(SerialPort.Port.kUSB);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
    this.gyro.calibrate();
    
  }
  public double getAngle() {
    return this.gyro.getAngle();
  }
  

  @Override
  public void periodic() {
    ++countt;
    SmartDashboard.putBoolean("Sensed", this.gyro.isConnected());
    SmartDashboard.putBoolean("True Test", true);

    
    SmartDashboard.putNumber("COunt", countt);
    SmartDashboard.putNumber("Rel Angle", this.gyro.getAngle());
  }
  @Override
  public void simulationPeriodic() {}
}
