package frc.robot.loaderConveyor;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.Rev2mDistanceSensor;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import static frc.robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.*;

public class BasicLoaderConveyorComponentsA implements LoaderConveyorComponents {

    private final WPI_TalonSRX masterMotor;

    private Rev2mDistanceSensor distanceSensor;

    public BasicLoaderConveyorComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.enableCurrentLimit(true);
        masterMotor.setInverted(true);

        distanceSensor = new Rev2mDistanceSensor(Rev2mDistanceSensor.Port.kOnboard);
        distanceSensor.setDistanceUnits(Rev2mDistanceSensor.Unit.kMillimeters);
        distanceSensor.setAutomaticMode(true);

        Shuffleboard.getTab("loader").addNumber("distance", this::getCurrentDistance);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public Rev2mDistanceSensor getDistanceSensor() {
        return distanceSensor;
    }

    @Override
    public double getCurrentDistance() {
        return distanceSensor.getRange();
    }

    @Override
    public void reinitializeDistanceSensor() {
    }

    public TalonSRXConfiguration getConfiguration() {
        TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.slot0.kP = VELOCITY_P;
        config.slot1.kI = VELOCITY_I;
        config.slot2.kD = VELOCITY_D;
        config.slot3.kF = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        config.peakCurrentLimit = PEAK_AMP;
        config.peakCurrentDuration = PEAK_AMP_DURATION;
        config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
        return config;
    }
}
