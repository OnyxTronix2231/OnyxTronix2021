package frc.robot.Roulette;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import pid.CtreMotionMagicController;
import pid.PIDFTerms;
import pid.interfaces.MotionMagicController;
import sensors.counter.Counter;
import sensors.counter.CtreEncoder;

import static frc.robot.Roulette.RouletteConstants.CHANNEL;
import static frc.robot.Roulette.RouletteConstants.CollectorConstantsA.*;
import static frc.robot.Roulette.RouletteConstants.DEVICE_NUMBER;

public class RouletteComponentsA implements RouletteComponents {

    private final WPI_TalonSRX masterMotor;
    private final Solenoid solenoid;
    private final CtreEncoder encoder;
    private final CtreMotionMagicController controller;
    private final ColorSensorV3 colorSensor;

    public RouletteComponentsA() {
        masterMotor = new WPI_TalonSRX(DEVICE_NUMBER);
        masterMotor.configFactoryDefault();
        solenoid = new Solenoid(CHANNEL);
        encoder = new CtreEncoder(masterMotor);
        controller = new CtreMotionMagicController(masterMotor, encoder,
                new PIDFTerms(K_P, K_I, K_D, K_F), MAX_ACCELERATION, MAX_VELOCITY, ACCELERATION_SMOOTHING);
        colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
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

}
