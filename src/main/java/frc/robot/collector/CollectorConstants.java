package frc.robot.collector;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class CollectorConstants {

    static final DoubleSolenoid.Value OPEN_PISTONS = DoubleSolenoid.Value.kForward;
    static final DoubleSolenoid.Value CLOSE_PISTONS = DoubleSolenoid.Value.kReverse;

    public static final class BallCollectorConstantsA {

        public static final double COLLECTION_EJECT_SPEED = -0.8;
        static final int MASTER_MOTOR_ID = 9;
        static final int FORWARD_CHANNEL = 1;
        static final int REVERSE_CHANNEL = 2;
        static final int PEAK_AMP = 0; //TODO: check value
        static final int PEAK_AMP_DURATION = 0; //TODO: check value
        static final int CONTINUOUS_CURRENT_LIMIT = 40; //TODO: check value
        static final double OPEN_LOOP_RAMP = 0; //TODO: check value
        static final double CLOSED_LOOP_RAMP = 0; //TODO: check value
        static final boolean CURRENT_LIMIT_ENABLED = true;
    }
}
