package frc.robot.drivetrain.Skills;

import edu.wpi.first.wpilibj.util.Units;

public class Waypoint {

    private final double x;
    private final double y;

    public Waypoint(double x, double y){
        this.x = Units.inchesToMeters(x);
        this.y = Units.inchesToMeters(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
