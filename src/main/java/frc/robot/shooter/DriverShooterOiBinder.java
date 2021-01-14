package frc.robot.shooter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.shooter.commands.ChangeAngleByPosition;
import frc.robot.shooter.commands.ShootByRPM;
import onyxTronix.UniqueButtonCache;

public class DriverShooterOiBinder {

    public DriverShooterOiBinder(final Shooter shooter, final Trigger shootByRPM, final Trigger changeAngle ) {

        shootByRPM.whileActiveContinuous(new ShootByRPM(shooter, () -> 17000));

        NetworkTableEntry entry =Shuffleboard.getTab("Shooter").add("angle", 0).getEntry();
        changeAngle.whenActive(new ChangeAngleByPosition(shooter,()-> entry.getDouble(0)));
    }
}
