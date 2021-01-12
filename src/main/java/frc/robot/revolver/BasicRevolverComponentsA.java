package frc.robot.revolver;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.Rev2mDistanceSensor;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import static frc.robot.revolver.RevolverConstants.RevolverComponentsA.*;

public class BasicRevolverComponentsA implements RevolverComponents {

    private final WPI_TalonFX masterMotor;

    public BasicRevolverComponentsA() {
        masterMotor = new WPI_TalonFX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.setInverted(true);
    }

    @Override
    public WPI_TalonFX getMasterMotor() {
        return masterMotor;
    }

    public TalonFXConfiguration getConfiguration() {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.slot0.kP = VELOCITY_P;
        config.slot1.kI = VELOCITY_I;
        config.slot2.kD = VELOCITY_D;
        config.slot3.kF = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        return config;
    }
}
