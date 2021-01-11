package frc.robot.shooter;

import static frc.robot.RobotConstants.PRIMARY_PID;
import static frc.robot.shooter.ShooterConstants.ShooterComponentsA.RPM_TO_ENCODER_UNITS;
import static frc.robot.shooter.ShooterConstants.ShooterComponentsA.MILLISECOND_TO_MINUTE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private final ShooterComponents components;
    private double lastVelocityError;

    public Shooter(final ShooterComponents components) {
        this.components = components;
        lastVelocityError = Integer.MAX_VALUE;
        Shuffleboard.getTab("Shooter").addNumber("PID Error",
                () -> components.getMasterMotor().getClosedLoopError());
        Shuffleboard.getTab("Shooter").addNumber("Current RPM",
                () -> components.getMasterMotor().getSelectedSensorVelocity() * 600 / 2048.0);
        Shuffleboard.getTab("Shooter").addNumber("Current velocity",
                () -> components.getMasterMotor().getSelectedSensorVelocity());
    }

    public void shootBySpeed(final double speed) {
        components.getMasterMotor().set(speed);
    }

    public void stopMotor() {
        shootBySpeed(0);
    }

    public boolean isOnTarget() {
        return Math.abs(components.getMasterMotor().getClosedLoopError()) < rpmToEncoderUnits(ShooterConstants.TOLERANCE);
    }

    public void configVelocitySlot() {
        components.getMasterMotor().selectProfileSlot(ShooterConstants.ShooterComponentsA.PID_VELOCITY_GAINS, PRIMARY_PID);
    }

    public void setVelocity(final double velocity) {
        components.getMasterMotor().set(ControlMode.Velocity, rpmToEncoderUnits(velocity));
    }

    public void openShooterPiston() {
        components.getSolenoid().set(ShooterConstants.IS_PISTON_OPEN);
    }

    public void closeShooterPiston() {
        components.getSolenoid().set(!ShooterConstants.IS_PISTON_OPEN);
    }

    public double distanceToVelocity(double distance) {
        if (distance > ShooterConstants.ShooterComponentsA.MIDDLE_DISTANCE) {
            return -0.0121 * Math.pow(distance, 2) + 26.707 * distance + 24130;
        }
        return 0.1912 * Math.pow(distance, 2) - 161.44 * distance + 67791;
    }

    // y= -0.0121x2 +26.707x + 24130 > 450
    //y = 0.1912x2 - 161.44x +67791 < 450

    public double rpmToEncoderUnits(double rpm){
        return (rpm * RPM_TO_ENCODER_UNITS) / MILLISECOND_TO_MINUTE;
    }

    public void startChecking() {
        lastVelocityError = Integer.MAX_VALUE;
    }

    public double getVelocityError() {
        return components.getMasterMotor().getClosedLoopError();
    }

    public boolean isBallShot() {
        if (getVelocityError() > rpmToEncoderUnits(ShooterConstants.ShooterComponentsA.MIN_VELOCITY_ERROR) && getVelocityError() > lastVelocityError) {
            lastVelocityError = getVelocityError();
            return true;
        }
        return false;
    }

    public boolean isBallNotShot() {
        return !isBallShot();
    }

    public boolean isReadyToShoot() {
        return getVelocityError() < rpmToEncoderUnits(ShooterConstants.ShooterComponentsA.AT_SHOOTING_VELOCITY);
    }
}
