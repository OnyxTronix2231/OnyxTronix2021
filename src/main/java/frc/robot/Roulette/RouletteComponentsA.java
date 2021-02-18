package frc.robot.Roulette;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.LinearSystemSim;
import edu.wpi.first.wpilibj.system.plant.LinearSystemId;
import edu.wpi.first.wpiutil.math.numbers.N1;
import edu.wpi.first.wpiutil.math.numbers.N2;
import pid.CtreMotionMagicController;
import pid.PIDFTerms;
import pid.interfaces.MotionMagicController;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;

import static frc.robot.Roulette.RouletteConstants.*;
import static frc.robot.Roulette.RouletteConstants.RouletteConstantsA.*;

public class RouletteComponentsA implements RouletteComponents {

    private WPI_TalonSRX masterMotor;
    private Solenoid solenoid;
    private CtreEncoder encoder;
    private CtreMotionMagicController controller;
    private ColorSensorV3 colorSensor;
    private final LinearSystemSim<N1, N1, N1> simulator;

    public RouletteComponentsA() {

        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_ID);
        masterMotor.configFactoryDefault();
        solenoid = new Solenoid(SOLENOID_ID);
        encoder = new CtreEncoder(masterMotor);
        controller = new CtreMotionMagicController(masterMotor, encoder,
                new PIDFTerms(KP, KI, KD, KF), MAX_ACCELERATION, MAX_VELOCITY, ACCELERATION_SMOOTHING);
        colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
        simulator = new LinearSystemSim<>(LinearSystemId.identifyVelocitySystem(1, 0.1));
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public Solenoid getSolenoid() {
        return solenoid;
    }

    @Override
    public MotionMagicController getController() {
        return controller;
    }

    @Override
    public Counter getEncoder() {
        return encoder;
    }

    @Override
    public ColorSensorV3 getColorSensor() {
        return colorSensor;
    }

    @Override
    public LinearSystemSim getSimulator() {
        return simulator;
    }

}
