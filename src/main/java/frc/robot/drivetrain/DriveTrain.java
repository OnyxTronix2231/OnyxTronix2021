package frc.robot.drivetrain;

import static frc.robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_FORWARD_SENSITIVITY;
import static frc.robot.drivetrain.DriveTrainConstants.ARCADE_DRIVE_ROTATION_SENSITIVITY;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

    private final DriveTrainComponents components;

    public DriveTrain(DriveTrainComponents components) {
        this.components = components;
        resetEncoders();
    }

    public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
        components.getDifferentialDrive().arcadeDrive(forwardSpeed * ARCADE_DRIVE_FORWARD_SENSITIVITY,
                rotationSpeed * ARCADE_DRIVE_ROTATION_SENSITIVITY, false);
    }

    public void stopDrive() {
        components.getDifferentialDrive().stopMotor();
    }

    public void setNeutralModeToCoast() {
        components.getLeftMasterMotor().setNeutralMode(NeutralMode.Coast);
        components.getLeftSlaveMotor().setNeutralMode(NeutralMode.Coast);
        components.getRightMasterMotor().setNeutralMode(NeutralMode.Coast);
        components.getRightSlaveMotor().setNeutralMode(NeutralMode.Coast);
    }

    public void setNeutralModeToBrake() {
        components.getLeftMasterMotor().setNeutralMode(NeutralMode.Brake);
        components.getLeftSlaveMotor().setNeutralMode(NeutralMode.Brake);
        components.getRightMasterMotor().setNeutralMode(NeutralMode.Brake);
        components.getRightSlaveMotor().setNeutralMode(NeutralMode.Brake);
    }

    private WPI_TalonFX getLeftMaster() {
        return components.getLeftMasterMotor();
    }

    private WPI_TalonFX getRightMaster() {
        return components.getRightMasterMotor();
    }

    private void resetEncoders() {
        getLeftMaster().setSelectedSensorPosition(0);
        getRightMaster().setSelectedSensorPosition(0);
    }
}
