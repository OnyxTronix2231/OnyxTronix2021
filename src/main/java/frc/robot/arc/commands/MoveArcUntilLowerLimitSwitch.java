package frc.robot.arc.commands;

import static frc.robot.arc.ArcConstants.DISABLE_SENSORS;
import static frc.robot.arc.ArcConstants.ENABLE_SENSORS;
import static frc.robot.arc.ArcConstants.MOVE_ARC_TO_SWITCH_LIMIT_SPEED;

import frc.robot.arc.Arc;

public class MoveArcUntilLowerLimitSwitch extends MoveArcBySpeed {

    public MoveArcUntilLowerLimitSwitch(Arc arc) {
        super(arc, () -> MOVE_ARC_TO_SWITCH_LIMIT_SPEED);
    }

    @Override
    public void initialize() {
        arc.configReverseSoftLimitEnable(DISABLE_SENSORS);
    }

    @Override
    public boolean isFinished() {
        return arc.hasHitReverseLimit();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        arc.resetEncoderByAbsoluteValue();
        arc.configReverseSoftLimitEnable(ENABLE_SENSORS);
    }
}
