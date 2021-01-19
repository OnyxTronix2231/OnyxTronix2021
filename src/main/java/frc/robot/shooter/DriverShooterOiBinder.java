package frc.robot.shooter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.shooter.commands.ChangeAngleByPosition;
import frc.robot.shooter.commands.ShootBySpeed;
import onyxTronix.JoystickAxis;

public class DriverShooterOiBinder {

    public DriverShooterOiBinder(Shooter shooter, Trigger changeAngle, JoystickAxis shootByPresentOutput ) {

        NetworkTableEntry entry = Shuffleboard.getTab("Shooter").add("angle", 0).getEntry();
        changeAngle.whenActive(new ChangeAngleByPosition(shooter,()-> entry.getDouble(0)));

        shootByPresentOutput.whileActiveContinuous(new ShootBySpeed(shooter, () -> shootByPresentOutput.getRawAxis()));

    }
}
