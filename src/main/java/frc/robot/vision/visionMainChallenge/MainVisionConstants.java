package frc.robot.vision.visionMainChallenge;

import frc.robot.vision.Vector2dEx;

public final class MainVisionConstants {

    public static final double CM_IN_METER = 100;
    public static final double CIRCLE_ANGLES = 360;

    // mechanical parameters
    public static final double LIMELIGHT_ANGLE_TO_HORIZON_DEG = 16.8;  // 15.8
    public static final double LIMELIGHT_HEIGHT_TO_FLOOR_CM = 59.3;

    public static final double LIMELIGHT_TO_TURRET_CM = 14;
    public static final double DISTANCE_OUTER_TO_FIELD_ZERO_CM = 580.6186;
    public static final double DISTANCE_BETWEEN_OUTER_INNER_TARGET_CM = 75.5;
    public static final double OUTER_TARGET_HEIGHT_CM = 224;  // 225
    public static final double INNER_TARGET_HEIGHT_CM = 242;
    public static final double ROBOT_TO_TURRET_DISTANCE_CM = 10.511;

    // default vectors
    public static final Vector2dEx VECTOR_TURRET_TO_LIMELIGHT = new Vector2dEx(LIMELIGHT_TO_TURRET_CM, 0);
    public static final Vector2dEx VECTOR_OUTER_INNER_TARGET =
            new Vector2dEx(DISTANCE_BETWEEN_OUTER_INNER_TARGET_CM, 0);
    public static final Vector2dEx VECTOR_FIELD_ZERO_TO_OUTER = new Vector2dEx(0,
            DISTANCE_OUTER_TO_FIELD_ZERO_CM);

    // choosing parameters
    public static final double MAX_AIR_DISTANCE_OUTER_CM = 700;
    public static final double MIN_AIR_DISTANCE_OUTER_CM = 290;
    public static final double MAX_ABS_ANGLE_TARGET_TO_FIELD_DEG = 18;

    public static final double MIN_TARGET_WIDTH = 10;
}
