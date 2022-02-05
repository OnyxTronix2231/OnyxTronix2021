package frc.robot.camera;


import edu.wpi.first.cscore.UsbCamera;

public interface CameraComponents {

    UsbCamera getFirstCamera();

    UsbCamera getSecondCamera();

}
