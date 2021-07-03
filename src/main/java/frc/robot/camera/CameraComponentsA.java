package frc.robot.camera;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

import static frc.robot.camera.CameraConstants.*;

public class CameraComponentsA implements CameraComponents {

    private final UsbCamera camera1;
    private final UsbCamera camera2;

    public CameraComponentsA(){
        camera1 = CameraServer.getInstance().startAutomaticCapture(CAMERA_PORT_ONE);
        setCameraConfiguration(camera1);

        camera2 = CameraServer.getInstance().startAutomaticCapture(CAMERA_PORT_SECOND);
        setCameraConfiguration(camera2);
    }

    public static void setCameraConfiguration(UsbCamera camera){
        camera.setResolution(WIDTH,HEIGHT);
        camera.setFPS(FPS);
        //camera.setBrightness(BRIGTNESS);
        //camera.setExposureAuto();
        camera.setExposureManual(EXPOSURE);
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
