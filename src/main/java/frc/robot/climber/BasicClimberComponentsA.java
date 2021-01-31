package frc.robot.climber;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import pid.CtreController;
import pid.CtreMotionMagicController;
import pid.PIDFTerms;
import sensors.counter.CtreEncoder;


public class BasicClimberComponentsA implements ClimberComponents {
    private final WPI_TalonFX rightMotor;
    private final WPI_TalonFX leftMotor;
    private final CtreMotionMagicController controller;
    private final CtreEncoder encoder;

    public BasicClimberComponentsA() {

        rightMotor = new WPI_TalonFX(0);
        rightMotor.configFactoryDefault();
        //   masterMotor.configAllSettings(getMasterMotor());
        rightMotor.setNeutralMode(NeutralMode.Brake);

        leftMotor = new WPI_TalonFX(0);
        leftMotor.configFactoryDefault();
        //  slaveMotor.configAllSettings(getSlaveMotor());
        leftMotor.setNeutralMode(NeutralMode.Brake);

        encoder = new CtreEncoder(rightMotor);

        controller = new CtreMotionMagicController(rightMotor, encoder,new PIDFTerms(0,0,0,0)
                ,0,0,0);


    }

    @Override
    public WPI_TalonFX getRightMotor() {
        return rightMotor;
    }

    @Override
    public WPI_TalonFX getLeftMotor() {
        return leftMotor;
    }

    @Override
    public CtreController getController() {
        return controller;
    }

    @Override
    public CtreEncoder getEncoder() {
        return encoder;
    }

    private TalonFXConfiguration getFalconConfig;

}
