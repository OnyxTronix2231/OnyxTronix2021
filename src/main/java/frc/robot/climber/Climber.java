package frc.robot.climber;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.climber.ClimberConstants.ClimberConstantsA.TOLERANCE;

public class Climber extends SubsystemBase {

   private final ClimberComponents components;
   private final NetworkTableEntry kP;
   private final NetworkTableEntry kI;
   private final NetworkTableEntry kD;
   private final NetworkTableEntry kF;

   public Climber(ClimberComponents components) {
      this.components = components;
      kP = Shuffleboard.getTab("Climber").add("kP",
              components.getController().getPIDFTerms().getKp()).getEntry();
      kI = Shuffleboard.getTab("Climber").add("kI",
              components.getController().getPIDFTerms().getKi()).getEntry();
      kD = Shuffleboard.getTab("Climber").add("kD",
              components.getController().getPIDFTerms().getKd()).getEntry();
      kF = Shuffleboard.getTab("Climber").add("kF",
              components.getController().getPIDFTerms().getKf()).getEntry();
   }

   @Override
   public void periodic() {
      components.getController().setPIDFTerms(kP.getDouble(components.getController().getPIDFTerms().getKp()),
              kI.getDouble(components.getController().getPIDFTerms().getKi()),
              kD.getDouble(components.getController().getPIDFTerms().getKd()),
              kF.getDouble(components.getController().getPIDFTerms().getKf()));
   }

   public void moveBySpeed(double speed) {
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
     return components.getController().isOnTarget(TOLERANCE); // Todo: convert to encoder units, put variable
   }

   public void stopMotor() {
      components.getController().disable();
      moveBySpeed(0);
   }
}

