package frc.robot.climber;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Solenoid;

public class BasicClimberComponentsA implements ClimberComponents {
    private final WPI_TalonFX masterMotor;
    private final WPI_TalonFX slaveMotor;
    private final Solenoid solenoid;

    public BasicClimberComponentsA() {

        masterMotor = new WPI_TalonFX(0);
        masterMotor.configFactoryDefault();
        //   masterMotor.configAllSettings(getMasterMotor());
        masterMotor.setNeutralMode(NeutralMode.Brake);

        slaveMotor = new WPI_TalonFX(0);
        slaveMotor.configFactoryDefault();
        //  slaveMotor.configAllSettings(getSlaveMotor());
        slaveMotor.setNeutralMode(NeutralMode.Brake);
        slaveMotor.follow(masterMotor);

        solenoid = new Solenoid(0);
    }


    @Override
    public WPI_TalonFX getMasterMotor() {
        return null;
    }

    @Override
    public WPI_TalonFX getSlaveMotor() {
        return null;
    }

    @Override
    public Solenoid getSolenoid() {return null; }

    private TalonFXConfiguration getFalconConfig;



}
