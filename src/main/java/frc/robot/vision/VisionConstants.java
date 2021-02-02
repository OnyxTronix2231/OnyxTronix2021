package frc.robot.vision;

public class VisionConstants {
    static final double DEFAULT_HORIZONTAL_ANGLE = 0;
    static final double TARGET_ANGLE_TOLERANCE = 2; //TODO:check and change
    static final double DEFAULT_TARGET_AREA = 0;    //TODO:check and change

    static final double RED_AREA_PERCENTAGE = 0;    //TODO:check and change
    static final double BLUE_AREA_PERCENTAGE = 0;   //TODO:check and change

    static final double LIMELIGHT_ANGLE_TO_HORIZON = 34.08; //TODO: check and change // mechanical parameter
    static final double LIMELIGHT_HEIGHT_TO_FLOOR = 632.82; //TODO: check and change // mechanical parameter

    static final double TARGET_HEIGHT = 0; //TODO: check and change

    public static final double LIMELIGHT_TURRET_CENTER_CM = 18.79; //TODO: check and change // mechanical parameter
    public static final Vector2dEx VECTOR_LIMELIGHT_TURRET_CENTER = new Vector2dEx(LIMELIGHT_TURRET_CENTER_CM, 0);

    public static final double HEIGHT_OFFSET_INNER_OUTER_CENTER = 0; //TODO: check and change // mechanical parameter
    public static final double DISTANCE_BETWEEN_OUTER_INNER_TARGET = 29.25; //TODO: check and change // mechanical parameter

    public static final Vector2dEx VECTOR_OUTER_INNER_TARGET =
            new Vector2dEx(DISTANCE_BETWEEN_OUTER_INNER_TARGET, 0);

    public static final double TARGET_HEIGHT_DIFFERENCE = TARGET_HEIGHT - LIMELIGHT_HEIGHT_TO_FLOOR
            + HEIGHT_OFFSET_INNER_OUTER_CENTER;

    public static final double CIRCLE_ANGLES = 360;
    public static final double ROBOT_CENTER_TURRET_DISTANCE = 0; // TODO: Check and change

    public static final double MAX_AIR_DISTANCE_OUTER = 0; // TODO: Calculate and change

    public static final double MIN_AIR_DISTANCE_OUTER = 0; // TODO: Calculate and change

    public static final double MAX_ABS_OFFSET_TARGET_TO_FIELD = 0; // TODO: Calculate and change
}
