package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

import static frc.robot.drivetrain.DriveTrainConstants.*;
import static frc.robot.drivetrain.DriveTrainConstants.PERIMETER_METER;
import static frc.robot.drivetrain.DriveTrainConstants.ENCODER_UNITS_PER_ROTATION;

public class DriveTrain extends SubsystemBase {

    private final DriveTrainComponents driveTrainComponents;


    public DriveTrain(DriveTrainComponents driveTrainComponents) {
        this.driveTrainComponents = driveTrainComponents;
        System.out.println("contractor");
        Shuffleboard.getTab("Drivetrain")
                .addNumber("LEFT DRIVE ENCODER",
                        () -> driveTrainComponents.getLeftEncoder().getRate());
        Shuffleboard.getTab("Drivetrain")
                .addNumber("RIGHT DRIVE ENCODER",
                        () -> driveTrainComponents.getRightEncoder().getRate());
        SmartDashboard.putData(driveTrainComponents.getField());
        resetEncoders();
        driveTrainComponents.getField().setRobotPose(new Pose2d(7,10,new Rotation2d(80)));
    }

    public void resetEncoders(){
        driveTrainComponents.getLeftEncoder().reset();
        driveTrainComponents.getRightEncoder().reset();
        driveTrainComponents.getNormelizedPigeonIMU().setYaw(0);
    }
    @Override
    public void periodic() {
        driveTrainComponents.getOdometry().update(
                Rotation2d.fromDegrees(getHeading()),
                encoderUnitsToMeters(driveTrainComponents.getLeftMasterMotor().getSelectedSensorPosition()),
                encoderUnitsToMeters(driveTrainComponents.getRightMasterMotor().getSelectedSensorPosition()));
        driveTrainComponents.getField().setRobotPose(driveTrainComponents.getOdometry().getPoseMeters());
        SmartDashboard.updateValues();
    }

    public void arcadeDrive(double speed, double rotation) {
        driveTrainComponents.getDifferentialDrive().arcadeDrive(speed, rotation);
    }

    public void stop() {
        driveTrainComponents.getLeftMasterMotor().stopMotor();
        driveTrainComponents.getRightMasterMotor().stopMotor();
    }

    public void stopDrive() {
        driveTrainComponents.getLeftMasterMotor().stopMotor();
        driveTrainComponents.getRightMasterMotor().stopMotor();
        driveTrainComponents.getLeftController().disable();
        driveTrainComponents.getRightController().disable();
        driveTrainComponents.getDifferentialDrive().stopMotor();
    }

    public void inItDriveByDistance(double distance) {
        inItMoveByEncoderUnits(Calculations.meterToEncoderUnits(distance));
    }

    public void updateDriveByDistance(double distance){
        updateMoveByEncoderUnits(Calculations.meterToEncoderUnits(distance));
    }

    public void inItMoveByEncoderUnits(double encoderUnits){
        driveTrainComponents.getLeftController().setSetpoint(encoderUnits);
        System.out.println(encoderUnits);
        driveTrainComponents.getRightController().setSetpoint(encoderUnits);
        driveTrainComponents.getLeftController().enable();
        driveTrainComponents.getRightController().enable();
    }

    public void updateMoveByEncoderUnits(double encoderUnits){
        driveTrainComponents.getLeftController().update(encoderUnits);
        driveTrainComponents.getRightController().update(encoderUnits);
    }


    public boolean isOnTarget(){
        return driveTrainComponents.getLeftController().isOnTarget(tolerance) && driveTrainComponents.getRightController().isOnTarget(tolerance);
    }

    public Pose2d getPose() {
        return driveTrainComponents.getOdometry().getPoseMeters();
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(encoderUnitsDeciSecToMetersSec(
                        driveTrainComponents.getLeftMasterMotor().getSelectedSensorVelocity()),
                        encoderUnitsDeciSecToMetersSec(driveTrainComponents.getRightMasterMotor().getSelectedSensorVelocity()));
    }

    public double getTotalRobotSpeedMPS(){
        DifferentialDriveWheelSpeeds ddws = getWheelSpeeds();
        return (ddws.leftMetersPerSecond + ddws.rightMetersPerSecond) / 2;
    }

    public double getDistanceToTarget() {
        Pose2d pose2d = getPose();
        return Math.sqrt(Math.pow(pose2d.getX() - TARGET_POSE.getX(), 2) + Math.pow(pose2d.getY() - TARGET_POSE.getY(), 2));
    }

    private double encoderUnitsToMeters(double units) {
        return units * PERIMETER_METER / ENCODER_UNITS_PER_ROTATION;
    }

    private double encoderUnitsDeciSecToMetersSec(double unitsDeciSec) {
        return encoderUnitsToMeters(unitsDeciSec * DECISECOND_IN_SECOND);
    }
    private double robotAcceleration(double time) {
        return MAX_VELOCITY/time;

    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        if (Robot.isReal()) {
            driveTrainComponents.getLeftMasterMotor().set(leftVolts / VOLTS);
            driveTrainComponents.getRightMasterMotor().set(rightVolts / VOLTS);
            driveTrainComponents.getDifferentialDrive().feed();
        }
    }

    public double getHeading() {
        return driveTrainComponents.getNormelizedPigeonIMU().getRawYaw();
    }
    
}
