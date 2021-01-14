package frc.robot.Roulette;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Roulette.RouletteConstants.*;

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

    public void initMoveByRotation(double rotations){
        this.components.getController().setSetpoint(rotationToEncoderUnits(rotations));
        this.components.getController().enable();
    }

    public void updateMoveByRotations(double rotations){
        this.components.getController().update(rotationToEncoderUnits(rotations));
    }

    public double rotationToEncoderUnits(double rotations){
        return rotations * ENCODER_UNITS_PER_ROUND * RATIO_ROULETTE_TO_WEEL;
    }

    public double encoderUnitsToRotation(double encoderUnits){
        return  encoderUnits / ENCODER_UNITS_PER_ROUND / RATIO_ROULETTE_TO_WEEL;
    }

    public void reset(){
        components.getEncoder().reset();
    }

    public void stop(){
        components.getMasterMotor().set(0);
    }

}
