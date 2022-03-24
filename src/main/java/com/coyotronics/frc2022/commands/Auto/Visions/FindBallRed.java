package com.coyotronics.frc2022.commands.Auto.Visions;
// package com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;


import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.vision.VisionRunner;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj2.command.CommandBase;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import edu.wpi.first.cscore.CvSource;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class FindBallRed extends CommandBase {

    public FindBallRed(){
        UsbCamera camera = CameraServer.startAutomaticCapture();
        VisionThread visionThread = new VisionThread(camera, new RedBallPipelineVTwo(), pipeline -> {
            Mat res = new Mat();
            if (!pipeline.filterContoursOutput().isEmpty()) {
                for(int i = 0; i < pipeline.filterContoursOutput().size(); ++i) {
                    MatOfPoint contour = pipeline.filterContoursOutput().get(i);
                    Imgproc.drawContours(res, pipeline.filterContoursOutput(), i, new Scalar(255, 255, 255), -1);
                    
                    Rect boundRect = Imgproc.boundingRect(contour);
                    double centerX = boundRect.x + (boundRect.width / 2);
                    double centerY = boundRect.y + (boundRect.height / 2);

                    SmartDashboard.putNumber("CenterX", centerX);
                    SmartDashboard.putNumber("CenterY", centerY);
                }
            }
            CvSource outputStream = CameraServer.putVideo("VisionOutput", 640, 480);
            outputStream.putFrame(res);
        });
        visionThread.start();

    }
    public void initialize() {
    }
    public void end(boolean interrupted) {}
     public boolean isFinished() {
        return false;
    }
}
