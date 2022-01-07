package frc.robot.shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.shooter.ShooterConstants.MIN_ERROR_RPM;
import static frc.robot.shooter.ShooterConstants.ShooterCalculation.RPMToEncoderUnitsInDecisecond;
import static frc.robot.shooter.ShooterConstants.ShooterCalculation.distanceMetersToRPM;
import static frc.robot.shooter.ShooterConstants.TOLERANCE_RPM;

public class Shooter extends SubsystemBase {

    private final ShooterComponents components;
    private final ShooterShuffleboard shooterShuffleboard;

    private double lastRPMError;

    public Shooter(ShooterComponents components) {
        this.components = components;
        initIsBallShot();
        shooterShuffleboard = new ShooterShuffleboard(components);
    }

    @Override
    public void periodic() {
        shooterShuffleboard.update();
    }

    public void moveBySpeed(double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stop() {
        moveBySpeed(0);
        components.getController().disable();
    }

    public void initMoveByRPM(double rpm) {
        components.getController().setSetpoint(RPMToEncoderUnitsInDecisecond(rpm));
        components.getController().enable();
    }

    public double convertDistanceMeterToRPM(double distance) {
        return distanceMetersToRPM(distance);
    }

    public void updateMoveByRPM(double rpm) {
        components.getController().update(RPMToEncoderUnitsInDecisecond(rpm));
    }

    public void initIsBallShot() {
        lastRPMError = Double.MAX_VALUE;
    }

    public boolean updateIsBallShot() {
        boolean isBallShot = false;
        if (components.getController().getCurrentError() >
                MIN_ERROR_RPM && components.getController().getCurrentError() > lastRPMError) {
            isBallShot = true;
        }
        lastRPMError = components.getController().getCurrentError();
        return isBallShot;
    }

    public boolean isOnTarget() {
        return components.getController().isOnTarget(RPMToEncoderUnitsInDecisecond(TOLERANCE_RPM));
    }

//        public double getRpm(){
//            return rpm.getDouble(0);
//        }
}


