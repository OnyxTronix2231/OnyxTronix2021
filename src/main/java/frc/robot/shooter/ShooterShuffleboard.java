package frc.robot.shooter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import static frc.robot.shooter.ShooterConstants.ShooterCalculation.*;

public class ShooterShuffleboard {

    private final ShooterComponents components;
    private final NetworkTableEntry kP;
    private final NetworkTableEntry kI;
    private final NetworkTableEntry kD;
    private final NetworkTableEntry kF;
    private final NetworkTableEntry rpm;

    public ShooterShuffleboard(ShooterComponents components){
        this.components = components;

        Shuffleboard.getTab("Shooter").addNumber("PID Error",
                () -> encoderUnitsInDecisecondToRPM(components.getMasterMotor().getClosedLoopError()));
        Shuffleboard.getTab("Shooter").addNumber("Current Shooter Motor RPM",
                () -> encoderUnitsInDecisecondToRPM(components.getEncoder().getRate()));
        Shuffleboard.getTab("Shooter").addNumber("Current Shooter Motor ENC",
                () -> components.getEncoder().getRate());

        kP = Shuffleboard.getTab("Shooter").add("kP",
                components.getController().getPIDFTerms().getKp()).getEntry();
        kI = Shuffleboard.getTab("Shooter").add("kI",
                components.getController().getPIDFTerms().getKi()).getEntry();
        kD = Shuffleboard.getTab("Shooter").add("kD",
                components.getController().getPIDFTerms().getKd()).getEntry();
        kF = Shuffleboard.getTab("Shooter").add("kF",
                components.getController().getPIDFTerms().getKf()).getEntry();
        rpm = Shuffleboard.getTab("Shooter").add("rpm", 4000).getEntry();

    }

    public void update(){
                components.getController().setPIDFTerms(
                kP.getDouble(components.getController().getPIDFTerms().getKp()),
                kI.getDouble(components.getController().getPIDFTerms().getKi()),
                kD.getDouble(components.getController().getPIDFTerms().getKd()),
                kF.getDouble(components.getController().getPIDFTerms().getKf()));
    }
}
