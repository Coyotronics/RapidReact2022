package com.coyotronics.frc2022.commands.Auto.Visions;
// package com.coyotronics.frc2022.commands.Auto.SubsytemInterfaces;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import com.coyotronics.frc2022.subsystems.DriveBaseSubsystem;


import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.vision.VisionRunner;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FindBallRed extends CommandBase {

    public FindBallRed(){
        UsbCamera camera = CameraServer.startAutomaticCapture();
        // camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
        VisionThread visionThread = new VisionThread(camera, new RedBallPipeline(), pipeline -> {
            if (!pipeline.filterContoursOutput().isEmpty()) {
                // Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
                // synchronized (imgLock) {
                    // centerX = r.x + (r.width / 2);
                // }
            }
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
