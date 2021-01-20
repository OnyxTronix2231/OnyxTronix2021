package frc.robot.shooter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.shooter.commands.MoveAngularByAngle;
import frc.robot.shooter.commands.MoveShooterBySpeed;
import onyxTronix.JoystickAxis;

public class DriverShooterOiBinder {

    public DriverShooterOiBinder(Shooter shooter, Trigger changeAngle, JoystickAxis shootByPresentOutput ) {
        NetworkTableEntry entry = Shuffleboard.getTab("Shooter").add("angle", 0).getEntry();
        changeAngle.whenActive(new MoveAngularByAngle(shooter,()-> entry.getDouble(0)));
        shootByPresentOutput.whileActiveContinuous(new MoveShooterBySpeed(shooter, shootByPresentOutput::getRawAxis));
    }
}
