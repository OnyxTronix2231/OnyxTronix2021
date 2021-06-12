package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.system.plant.DCMotor;

import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.CONVERSION_RATE;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainSimConstantsA.DRIVE_TRAIN_MASS;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainSimConstantsA.MOMENT_OF_INERTIA;
import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.TRACKWIDTH_METERS;
import static frc.robot.drivetrain.DriveTrainConstants.WHEEL_DIAMETER_METER;

public class DriveTrainVirtualComponentsA implements DriveTrainVirtualComponents {

    private final DifferentialDrive differentialDrive;
    private final DifferentialDrive simDifferentialDrive;
    private final DifferentialDriveOdometry odometry;
    private final DifferentialDrivetrainSim driveTrainSim;

    public DriveTrainVirtualComponentsA(SimulationDriveTrainComponents simComponents) {
        simDifferentialDrive = new DifferentialDrive(simComponents.getLeftMasterMotor(), simComponents.getRightMasterMotor());
        simDifferentialDrive.setRightSideInverted(false);
        simDifferentialDrive.setSafetyEnabled(false);

        differentialDrive = null;

        odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0));
        odometry.resetPosition(new Pose2d(), new Rotation2d());

        driveTrainSim = new DifferentialDrivetrainSim(DCMotor.getFalcon500(2), CONVERSION_RATE,
                MOMENT_OF_INERTIA, DRIVE_TRAIN_MASS, WHEEL_DIAMETER_METER / 2, TRACKWIDTH_METERS, null);
    }

    public DriveTrainVirtualComponentsA(DriveTrainComponents components) {
        differentialDrive = new DifferentialDrive(components.getLeftMasterMotor(), components.getRightMasterMotor());
        differentialDrive.setRightSideInverted(false);
        differentialDrive.setSafetyEnabled(false);

        simDifferentialDrive = null;

        odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0));
        odometry.resetPosition(new Pose2d(), new Rotation2d());

        driveTrainSim = null;
    }

    @Override
    public DifferentialDrive getDifferentialDrive() {
        return differentialDrive;
    }

    @Override
    public DifferentialDrive getSimDifferentialDrive() {
        return simDifferentialDrive;
    }

    @Override
    public DifferentialDrivetrainSim getDriveTrainSim() {
        return driveTrainSim;
    }

    @Override
    public DifferentialDriveOdometry getOdometry() {
        return odometry;
    }
}
