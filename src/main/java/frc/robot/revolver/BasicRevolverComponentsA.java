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
        masterMotor.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public WPI_TalonFX getMasterMotor() {
        return masterMotor;
    }
}
