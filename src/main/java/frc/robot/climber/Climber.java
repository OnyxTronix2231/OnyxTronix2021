package frc.robot.climber;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

   private ClimberComponents components;

   public Climber(ClimberComponents components) {this.components = components;}

   public void moveBySpeed(double moveBySpeed){
      components.getMasterMotor().set(moveBySpeed);
   }

   public void stopMotor(){
      moveBySpeed(0);
   }




}
