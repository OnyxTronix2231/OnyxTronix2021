package frc.robot.climber;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

import static frc.robot.climber.ClimberConstants.CLOSE_PISTONS;
import static frc.robot.climber.ClimberConstants.OPEN_PISTONS;

public class Climber extends SubsystemBase {

   private ClimberComponents components;

   public Climber(ClimberComponents components) {
      this.components = components;
   }

   public void moveBySpeed(double speed){
      components.getMasterMotor().set(speed);
   }

   public void initClimbToDistance(double distance) {
      components.getController().setSetpoint(distance); // Todo: convert to encoder units
      components.getController().enable();
   }

   public void updateClimbToDistance(double distance) {
      components.getController().update(distance); // Todo: convert to encoder units
   }

   public boolean isOnTarget() {
     return components.getController().isOnTarget(0.2); // Todo: convert to encoder units, put variable
   }

   public void openPiston() {
      components.getSolenoid().set(OPEN_PISTONS);
   }

   public void closePiston() {
      components.getSolenoid().set(CLOSE_PISTONS);
   }

   public void stopMotor() {
      components.getController().disable();
      moveBySpeed(0);
   }
}

