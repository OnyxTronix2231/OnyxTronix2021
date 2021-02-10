package frc.robot.vision;

public final class VisionConstants {

    public static final class VisionGSCConstants {
        // GSC Constants

        public static final double TARGET_ANGLE_TOLERANCE = 2; //TODO:check and change
        public static final double RED_AREA_PERCENTAGE = 0;    //TODO:check and change
        public static final double BLUE_AREA_PERCENTAGE = 0;   //TODO:check and change
    }

// ------------------------------------------------------------------------------------------------------

    // Main Challenge Constants

    // mechanical parameters
    public static final double CM_IN_METER = 100;
    public static final double CIRCLE_ANGLES = 360;
    public static final double LIMELIGHT_ANGLE_TO_HORIZON_DEG = 38.080000;
    public static final double LIMELIGHT_HEIGHT_TO_FLOOR_CM = 58.649;
    public static final double LIMELIGHT_TO_TURRET_CM = 15.18800;

    public static final double DISTANCE_OUTER_TO_FIELD_ZERO_CM = 580.618600;
    public static final double DISTANCE_BETWEEN_OUTER_INNER_TARGET_CM = 74.29500;
    public static final double OUTER_TARGET_HEIGHT_CM = 211.45501;
    public static final double INNER_TARGET_HEIGHT_CM = 233.045005;
    public static final double ROBOT_TO_TURRET_DISTANCE_CM = 10.511000;

    // default vectors
    public static final Vector2dEx VECTOR_LIMELIGHT_TO_TURRET = new Vector2dEx(LIMELIGHT_TO_TURRET_CM, 0);
    public static final Vector2dEx VECTOR_OUTER_INNER_TARGET =
            new Vector2dEx(DISTANCE_BETWEEN_OUTER_INNER_TARGET_CM, 0);
    public static final Vector2dEx VECTOR_FIELD_ZERO_TO_OUTER = new Vector2dEx(0,
            DISTANCE_OUTER_TO_FIELD_ZERO_CM);

    // choosing parameters
    public static final double MAX_AIR_DISTANCE_OUTER_CM = 0; // TODO: Calculate and change
    public static final double MIN_AIR_DISTANCE_OUTER_CM = 39.914000;
    public static final double MAX_ABS_ANGLE_TARGET_TO_FIELD_DEG = 0; // TODO: Calculate and change
}
