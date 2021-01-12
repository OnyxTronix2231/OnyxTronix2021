package frc.robot.Roulette;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;

public class RouletteComponentsA implements RouletteComponents{
    private WPI_TalonSRX masterMotor ;
    private Solenoid solenoid;


    public RouletteComponentsA() {
        masterMotor = new WPI_TalonSRX(0);
        solenoid = new Solenoid(0);

    }
    @Override
    public WPI_TalonSRX getMasterMotor(){ return masterMotor;}
    @Override
    public Solenoid getSolenoid() { return solenoid;}
}
