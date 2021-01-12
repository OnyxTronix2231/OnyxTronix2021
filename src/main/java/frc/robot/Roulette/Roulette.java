package frc.robot.Roulette;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Roulette extends SubsystemBase {

    private final RouletteComponents components;

    public Roulette(RouletteComponents components) {
        this.components = components;
    }

    public void openPiston(){
        components.getSolenoid().set(true);
    }
    public void closePiston(){
        components.getSolenoid().set(false);
    }
    public void setSpeed(double speed){
        components.getMasterMotor().set(speed);
    }
    // TODO: a way to check if the weel is in touch with the roulette itself?

    public void stop(){
        components.getMasterMotor().set(0);
    }

}
