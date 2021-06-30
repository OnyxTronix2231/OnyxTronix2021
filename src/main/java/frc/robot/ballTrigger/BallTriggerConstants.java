package frc.robot.ballTrigger;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public final class BallTriggerConstants {

    static final int DECISECOND_IN_MIN = 600;
    static final double BALL_TRIGGER_CONVERSION = 0.8;
    static final double ENCODER_UNITS_PER_ROTATION = 4096 * BALL_TRIGGER_CONVERSION;
    static final DoubleSolenoid.Value OPEN_PISTON = DoubleSolenoid.Value.kForward;
    static final DoubleSolenoid.Value CLOSE_PISTON = DoubleSolenoid.Value.kReverse;

    public static final class BallTriggerConstantsA {

        static final int MASTER_MOTOR_ID = 10;
        static final int SLAVE_MOTOR_ID = 12;
        static final int FORWARD_CHANNEL = 3;
        static final int REVERSE_CHANNEL = 0;
        static final int PEAK_AMP = 0; //TODO: check value
        static final int PEAK_AMP_DURATION = 0; //TODO: check value
        static final int CONTINUOUS_CURRENT_LIMIT = 20; //TODO: check value
        static final int OPEN_LOOP_RAMP = 0; //TODO: check value
        static final int CLOSED_LOOP_RAMP = 0; //TODO: check value
        static final int TOLERANCE_IN_RPM = 100;
        static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
        static final double MAX_VELOCITY = 14900 * 1.25; //TODO: check value
        static final double VELOCITY_P = 0; //TODO: check value
        static final double VELOCITY_I = 0; //TODO: check value
        static final double VELOCITY_D = 8; //TODO: check value
        static final double VELOCITY_F = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        static final boolean CURRENT_LIMIT_ENABLED = false;
        static final boolean INVERTED = false;
        static final boolean SENSOR_PHASE = true;
    }
}
