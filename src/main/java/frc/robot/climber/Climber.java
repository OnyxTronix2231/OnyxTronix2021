package frc.robot.climber;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

   private ClimberComponents components;

   public Climber(ClimberComponents components) {
      this.components = components;
   }

   public void moveRightMotorBySpeed(double speed) {
      components.getRightMotor().set(speed);
   }

   public void moveLeftMotorBySpeed(double speed) {
      components.getLeftMotor().set(speed);
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

   public void stopMotor() {
      components.getController().disable();
      moveRightMotorBySpeed(0);
      moveLeftMotorBySpeed(0);
   }
}

