package frc.robot.camera;

import edu.wpi.cscore.UsbCamera;

public interface CameraComponents {

    UsbCamera getFirstCamera();

    UsbCamera getSecondCamera();

}
