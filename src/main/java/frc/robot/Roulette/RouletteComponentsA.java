package frc.robot.Roulette;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
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

import static frc.robot.Roulette.RouletteConstants.CHANNEL;
import static frc.robot.Roulette.RouletteConstants.CollectorConstantsA.*;
import static frc.robot.Roulette.RouletteConstants.DEVICE_NUMBER;

public class RouletteComponentsA implements RouletteComponents {

    private WPI_TalonSRX masterMotor;
    private Solenoid solenoid;
    private CtreEncoder encoder;
    private CtreMotionMagicController controller;
    private ColorSensorV3 colorSensor;
    private final LinearSystemSim<N2, N1, N1> simulator;

    public RouletteComponentsA() {
        masterMotor = new WPI_TalonSRX(DEVICE_NUMBER);
        solenoid = new Solenoid(CHANNEL);
        encoder = new CtreEncoder(masterMotor);
        controller = new CtreMotionMagicController(masterMotor, encoder,
                new PIDFTerms(K_P, K_I, K_D, K_F), MAX_ACCELERATION, MAX_VELOCITY, ACCELERATION_SMOOTHING);
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
