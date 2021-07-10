package frc.robot.camera;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import vision.limelight.Limelight;

import static frc.robot.camera.CameraConstants.*;

public class CameraComponentsA implements CameraComponents {

    private final UsbCamera camera1;
    private final UsbCamera camera2;

    public CameraComponentsA(){
        camera1 = CameraServer.getInstance().startAutomaticCapture();
        setCameraConfiguration(camera1);

        camera2 = CameraServer.getInstance().startAutomaticCapture();
        setCameraConfiguration(camera2);
    }

    public static void setCameraConfiguration(UsbCamera camera){
        camera.setResolution(WIDTH,HEIGHT);
        camera.setFPS(FPS);
        camera.setExposureManual(BRIGHTNESS);
    }

    @Override
    public UsbCamera getFirstCamera() {
        return camera1;
    }

    @Override
    public UsbCamera getSecondCamera() {
        return camera2;
    }
}
