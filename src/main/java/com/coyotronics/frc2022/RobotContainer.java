// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.coyotronics.frc2022;

import com.coyotronics.frc2022.commands.Drive.ManualDrive;
import com.coyotronics.frc2022.commands.Drive.SwitchDriveType;
import com.coyotronics.frc2022.commands.Intake.IntakeCommand;
import com.coyotronics.frc2022.commands.Shooter.ShootCommand;
import com.coyotronics.frc2022.commands.Shooter.RunTransportCommand;


import com.coyotronics.frc2022.subsystems.DischargeSubsystem;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;
import com.coyotronics.frc2022.subsystems.IntakeSubsystem;
import com.coyotronics.frc2022.subsystems.TransportSubsystem;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.simulation.JoystickSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  /*
    SUBSYTEMS
  */
  public static XboxController controller = new XboxController(Constants.Common.kController);
  private final DriveBaseSubsystem driveBase = new DriveBaseSubsystem();
  private final DischargeSubsystem shooter = new DischargeSubsystem();
  private final TransportSubsystem transport = new TransportSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();

  /*
    COMMANDS
  */

  ManualDrive drive = new ManualDrive(driveBase);

  /*
  BUTTONS
  */

  private final JoystickButton switchDriveType = new JoystickButton(controller, Constants.Controller.X);
  private final JoystickButton shooterDischargeHighButton = new JoystickButton(controller, Constants.Controller.RIGHT_BUMPER);
  private final JoystickButton shooterDischargeLowButton = new JoystickButton(controller, Constants.Controller.LEFT_BUMPER);
  private final JoystickButton shooterTransportButton = new JoystickButton(controller, Constants.Controller.B);
  private final JoystickButton intakeButton = new JoystickButton(controller, Constants.Controller.Y);
  // private final JoystickButton shooterStorageReverseButton = new JoystickButton(controller, Constants.Controller.BACK);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    setDefaults();
    configureButtonBindings();
  }

  public void setDefaults() {
    driveBase.setDefaultCommand(drive);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    switchDriveType.whenPressed(new SwitchDriveType(this.driveBase));
    shooterDischargeHighButton.whenHeld(new ShootCommand(this.shooter, Constants.Shooter.ShootType.HIGH));
    shooterDischargeLowButton.whenHeld(new ShootCommand(this.shooter, Constants.Shooter.ShootType.LOW));
    shooterTransportButton.whenHeld(new RunTransportCommand(this.transport));
    intakeButton.whenHeld(new IntakeCommand(this.intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    
    // An ExampleCommand will run in autonomous
    return null;
  }
}
