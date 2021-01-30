package frc.robot.climber;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Solenoid;
import pid.CtreController;
import pid.CtreMotionMagicController;
import pid.PIDFTerms;
import sensors.counter.CtreEncoder;


public class BasicClimberComponentsA implements ClimberComponents {
    private final WPI_TalonFX masterMotor;
    private final WPI_TalonFX slaveMotor;
    private final CtreMotionMagicController controller;
    private final CtreEncoder encoder;
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

        encoder = new CtreEncoder(masterMotor);

        controller = new CtreMotionMagicController(masterMotor, encoder,new PIDFTerms(0,0,0,0)
                ,0,0,0);

        solenoid = new Solenoid(0);

    }


    @Override
    public WPI_TalonFX getMasterMotor() {
        return masterMotor;
    }


    @Override
    public WPI_TalonFX getSlaveMotor() {
        return slaveMotor;
    }

    @Override
    public CtreController getController() {
        return controller;
    }

    @Override
    public CtreEncoder getEncoder() {
        return encoder;
    }

    @Override
    public Solenoid getSolenoid() {
        return solenoid;
    }

    private TalonFXConfiguration getFalconConfig;



}
