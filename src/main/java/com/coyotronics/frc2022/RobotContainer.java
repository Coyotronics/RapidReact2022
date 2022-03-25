// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.coyotronics.frc2022;

import com.coyotronics.frc2022.commands.Drive.ManualDrive;
import com.coyotronics.frc2022.commands.Drive.SwitchDriveType;
import com.coyotronics.frc2022.commands.Shooter.ShootCommand;
import com.coyotronics.frc2022.commands.Auto.AutoSequence;
import com.coyotronics.frc2022.commands.Auto.Groups.Shoot;
import com.coyotronics.frc2022.commands.Auto.Sequences.EmptyAuto;
import com.coyotronics.frc2022.commands.Auto.Sequences.OneBallAuto;
import com.coyotronics.frc2022.commands.Auto.Sequences.TwoBallAuto;

import java.time.Period;
import java.util.Currency;

import com.coyotronics.frc2022.commands.SwitchCameraCommand;
import com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces.*;
import com.coyotronics.frc2022.commands.Auto.Visions.FindBallRed;
import com.coyotronics.frc2022.commands.Auto.Visions.RedBallPipeline;
import com.coyotronics.frc2022.subsystems.DischargeSubsystem;
import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;
import com.coyotronics.frc2022.subsystems.GryoSubsystem;
import com.coyotronics.frc2022.subsystems.IntakeSubsystem;
import com.coyotronics.frc2022.subsystems.TransportSubsystem;
import com.coyotronics.frc2022.commands.Auto.Visions.RedBallPipelineVTwo;

import com.coyotronics.frc2022.commands.Auto.Visions.FindBallRed;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.vision.VisionThread;


import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.vision.VisionRunner;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj2.command.CommandBase;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;

import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cscore.CvSource;
import org.opencv.core.Rect;
import edu.wpi.first.cscore.CvSource;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cscore.CvSource;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;

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
  boolean revved = false;
  public static XboxController controller = new XboxController(Constants.Common.kController);
  private final DriveBaseSubsystem driveBase = new DriveBaseSubsystem();
  private final DischargeSubsystem shooter = new DischargeSubsystem();
  private final TransportSubsystem transport = new TransportSubsystem();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  private final GryoSubsystem gryo = new GryoSubsystem();
  private UsbCamera camField;
  // private UsbCamera camIntake;

  /*
    COMMANDS
  */

  ManualDrive drive = new ManualDrive(driveBase);
  private final Command m_oneBallAuto = new OneBallAuto(driveBase, shooter, intake, transport, gryo);
  private final Command m_twoBallAuto = new TwoBallAuto(driveBase, shooter, intake, transport, gryo);
  private final Command m_emptyAuto = new EmptyAuto();

  SendableChooser<Command> m_Chooser = new SendableChooser<>();
  /*
  BUTTONS
  */

  // private final JoystickButton switchDriveType = new JoystickButton(controller, Constants.Controller.X);
  private final JoystickButton shooterDischargeHighButton = new JoystickButton(controller, Constants.Controller.RIGHT_BUMPER);
  private final JoystickButton shooterDischargeLowButton = new JoystickButton(controller, Constants.Controller.LEFT_BUMPER);
  // private final JoystickButton switchCameraSourceButton = new JoystickButton(controller, Constants.Controller.LEFT_BUMPER);
  private final JoystickButton shooterTransportButton = new JoystickButton(controller, Constants.Controller.B);
  private final JoystickButton shooterTransportBackButton = new JoystickButton(controller, Constants.Controller.X);
  private final JoystickButton intakeButton = new JoystickButton(controller, Constants.Controller.Y);
  private final JoystickButton shootButton = new JoystickButton(controller, Constants.Controller.LEFT_BUMPER);
  private final JoystickButton intakeAndTransportButton = new JoystickButton(controller, Constants.Controller.A);
  // private final JoystickButton shootButton = new JoystickButton(controller, Constants.Controller.A);
  // private final JoystickButton shooterStorageReverseButton = new JoystickButton(controller, Constants.Controller.BACK);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    System.out.print("STARTING");
    // Configure the button bindings
    setCameras();
    setDefaults();
    configureButtonBindings();
    // CommandScheduler.getInstance().schedule(new FindBallRed(this.camField));

    FindBallRed();
   
  }
  double since = 0;
  public void setCameras() {
    camField = CameraServer.startAutomaticCapture();
    camField.setResolution(160, 120);
    // camIntake =  CameraServer.startAutomaticCapture(1);
    
    // camIntake.setResolution(10, 10);
    // camIntake.setFPS(5);

    // camField.setResolution(200, 200);
    // camField.setFPS(30);

    CameraServer.getServer().setSource(camField);
    // CameraServer.getServer().setSource(camIntake);
    // camIntake.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
    // camField.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
  }
  public void setDefaults() {
    driveBase.setDefaultCommand(drive);
    m_Chooser.setDefaultOption("2 Ball Auto", m_twoBallAuto);
    m_Chooser.addOption("1 Ball Auto", m_oneBallAuto);
    m_Chooser.addOption("No Auto", m_emptyAuto);
    m_Chooser.addOption("No Auto", m_emptyAuto);
    SmartDashboard.putData(m_Chooser);
    
  }
  Object imgLock = new Object();
  public void FindBallRed() {
    SmartDashboard.putNumber("CenterX", -4);
    new Thread(() -> {
      Mat res = new Mat();
      CvSource outputStream = CameraServer.putVideo("VisionOutput", 640, 480);
      SmartDashboard.putNumber("CenterX", -3);
        while(!Thread.interrupted()) {
            // try {
            //   Thread.sleep(50);
            // } catch (InterruptedException e) {}
            VisionThread visionThread = new VisionThread(camField, new RedBallPipelineVTwo(), pipeline -> {
              
            if (!pipeline.filterContoursOutput().isEmpty()) {
                MatOfPoint largst = pipeline.filterContoursOutput().get(0);
                int index = 0;
                SmartDashboard.putNumber("CenterX", -2);
                for(int i = 0; i < pipeline.filterContoursOutput().size(); ++i) {
                  MatOfPoint contour = pipeline.filterContoursOutput().get(i);
                  if(Imgproc.contourArea(contour) > Imgproc.contourArea(largst)) {
                    largst = contour;
                    index = i;
                  }
                }
                Imgproc.drawContours(res, pipeline.filterContoursOutput(), index, new Scalar(255, 255, 255), -1);
                synchronized (imgLock) {
                  Rect boundRect = Imgproc.boundingRect(largst);
                  double centerX = boundRect.x + (boundRect.width / 2);
                  double centerY = boundRect.y + (boundRect.height / 2);

                  SmartDashboard.putNumber("CenterX", centerX);
                  SmartDashboard.putNumber("CenterY", centerY);

                  outputStream.putFrame(res);           
                 }
            } else {
              SmartDashboard.putNumber("CenterX", -1);
            }
        });
        visionThread.start();
      }
    }).start();
  }
  int cnt = 0;

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // switchDriveType.whenPressed(new SwitchDriveType(this.driveBase));
    shooterDischargeHighButton.whenHeld(new ShootCommand(this.shooter, Constants.Shooter.ShootType.HIGH));
    shooterDischargeLowButton.whenHeld(new ShootCommand(this.shooter, Constants.Shooter.ShootType.LOW));
    // shooterDischargeHighButton.whenPressed(new Shoot(transport, shooter, 3, Constants.Shooter.ShootType.HIGH));
    // shooterDischargeLowButton.whenPressed(new Shoot(transport, shooter, 3, Constants.Shooter.ShootType.LOW));
    shooterTransportButton.whenHeld(new StartEndCommand(this.transport::runFoward, this.transport::stop, this.transport));
    shooterTransportBackButton.whenHeld(new StartEndCommand(this.transport::runBackward, this.transport::stop, this.transport));
    intakeButton.whenHeld(new StartEndCommand(this.intake::run, this.intake::stop, this.intake));
    intakeAndTransportButton.whenPressed(new ParallelCommandGroup(new StartEndCommand(this.intake::run, this.intake::stop, this.intake),
                                                                  new StartEndCommand(this.transport::run, this.transport::stop, transport)));
    // shootButton.whenPressed(new Shoot(transport, shooter, 5));
    // shootButton.whenPressed(this.disc)
    // switchCameraSourceButton.whenPressed(new SwitchCameraCommand(CameraServer.getServer(), camField, camIntake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_Chooser.getSelected();
  }
}
