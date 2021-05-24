package frc.robot.collector;

public class CollectorConstants {

    static final boolean OPEN_PISTON = true;
    static final boolean CLOSE_PISTON = false;

    public static final class BallCollectorConstantsA {

        public static final double COLLECTION_SPEED = 0.8;
        static final int MASTER_MOTOR_ID = 7;
        static final int SOLENOID_CHANNEL = 0;
        static final int PEAK_AMP = 0; //TODO: check value
        static final int PEAK_AMP_DURATION = 0; //TODO: check value
        static final int CONTINUOUS_CURRENT_LIMIT = 0; //TODO: check value
        static final double OPEN_LOOP_RAMP = 0; //TODO: check value
        static final double CLOSED_LOOP_RAMP = 0; //TODO: check value
        static final boolean CURRENT_LIMIT_ENABLED = false;
    }
}
