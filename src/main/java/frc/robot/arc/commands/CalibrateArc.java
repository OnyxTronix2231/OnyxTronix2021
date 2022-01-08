package frc.robot.arc.commands;

import static frc.robot.arc.ArcConstants.DELAY_CALIBRATE_TIME;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.arc.Arc;

public class CalibrateArc extends SequentialCommandGroup {

    public CalibrateArc(Arc arc) {
        super(new MoveArcUntilLowerLimitSwitch(arc),
                new WaitCommand(DELAY_CALIBRATE_TIME),
                new InstantCommand(arc::resetEncoderByAbsoluteValue));
    }
}
