package frc.robot.shooter;

import static frc.robot.RobotConstants.PRIMARY_PID;
import static frc.robot.shooter.ShooterConstants.*;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.MAX_VELOCITY;
import static frc.robot.shooter.ShooterConstants.ShooterConstantsA.PID_VELOCITY_GAINS;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private final ShooterComponents components;
    private double lastRPMError;

    public Shooter(final ShooterComponents components) {
        this.components = components;
        lastRPMError = Integer.MAX_VALUE;
        Shuffleboard.getTab("Shooter").addNumber("PID Error",
                () -> components.getMasterMotor().getClosedLoopError());
        Shuffleboard.getTab("Shooter").addNumber("Current RPM",
                () -> encoderUnitsToRPM(components.getMasterMotor().getSelectedSensorVelocity()));
        Shuffleboard.getTab("Shooter").addNumber("Current velocity",
                () -> components.getMasterMotor().getSelectedSensorVelocity());
        Shuffleboard.getTab("Shooter").addNumber("current angle RPM",()->
                encoderUnitsToRPM(components.getAngleMotor().getSelectedSensorVelocity()));
    }

    /** shoot by speed get percent output between -1 to 1 and move the motor in -100% to 100% */
    public void shootBySpeed(final double speed) {
        components.getMasterMotor().set(speed);
    }

    public void changeAngleBySpeed(final double speed){
        components.getAngleMotor().set(ControlMode.PercentOutput,speed);
    }

    /** stopMotor function use shootBySpeed, the function gives the motor 0% what make the motor stop */
    public void stopMotor() {
        shootBySpeed(0);
    }

    public void stopAngleMotor() {
        changeAngleBySpeed(0);
    }

    public void changeAngleByPosition(double angle){
        components.getAngleMotor().set(ControlMode.MotionMagic,angle);
    }

    /** isOnTarget check if you got to the right velocity in order to shoot the ball */
    public boolean isOnTarget() {
        return Math.abs(components.getMasterMotor().getClosedLoopError()) < RPMToEncoderUnits(TOLERANCE);
    }

    public void configVelocitySlot() {
        components.getMasterMotor().selectProfileSlot(PID_VELOCITY_GAINS, PRIMARY_PID);
    }

    /** setVelocity is an function that get RPM value and transfer it to encoder units and sent this into the motor */
    public void setRPM(final double RPM) {
        components.getMasterMotor().set(ControlMode.Velocity, RPMToEncoderUnits(RPM));
    }

    /** this function is the most important function, distanceToVelocity get distance and calculate using 2 formulas
     * in order to get the velocity that is needed in order to shoot the ball to the middle target */
    public double distanceToEncoderUnits(double distance) {
        double encoderUnitsTarget;
        if (distance > MIDDLE_DISTANCE) {
            encoderUnitsTarget = -0.0121 * Math.pow(distance, 2) + 26.707 * distance + 24130;
        }
        else {
            encoderUnitsTarget = 0.1912 * Math.pow(distance, 2) - 161.44 * distance + 67791;
        }
        if (encoderUnitsTarget <= MAX_VELOCITY){
            return encoderUnitsTarget;
        }
        return MAX_VELOCITY;
    }

    // y= -0.0121x2 +26.707x + 24130 > 450
    //y = 0.1912x2 - 161.44x +67791 < 450

    /** this function change RPM units to encoder units */
    public double RPMToEncoderUnits(double RPM){
        return (RPM * ENCODER_UNITS_PER_ROTATION) / MILLISECOND_TO_MINUTE;
    }

    /** this function change encoder units to rpm units */
    public double encoderUnitsToRPM(double encoderUnits){
        return (encoderUnits * MILLISECOND_TO_MINUTE) / ENCODER_UNITS_PER_ROTATION;
    }

    /** this function change angle values to encoder units */
    public double angleToEncoderUnits(final double angle){
        return angle / ANGLE_TO_ENCODER_UNITS;
    }

    /** in order to wait until ball shot wee need to determine a way to check if ball was shhot in order to do it
     * we use velocity error to see if it was shot error is a velocity change that happen when ball is shoot */

    /** this function check the max velocity in the beginning */
    public void startChecking() {
        lastRPMError = Integer.MAX_VALUE;
    }

    /** this function check velocity error in the close loop  */
    public double getRPMError() {
        return encoderUnitsToRPM(components.getMasterMotor().getClosedLoopError());
    }

    /** this function determine if the ball was shoot*/
    public boolean isBallShot() {
        boolean isBallShot = false;
        if (getRPMError() > MIN_ERROR_RPM && getRPMError() > lastRPMError) {
            isBallShot = true;
        }
        lastRPMError = getRPMError();
        return isBallShot;
    }
    /** like isBallShot but checking if the ball didnt shoot */
    public boolean isBallNotShot() {
        return !isBallShot();
    }

    /** check if the wheels got to the right speed in order to shoot */
    public boolean isReadyToShoot() {
        return getRPMError() < RPMToEncoderUnits(AT_SHOOTING_RPM);
    }
}
