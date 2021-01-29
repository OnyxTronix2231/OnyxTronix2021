package frc.robot.drivetrain.skills;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Units;

public class Waypoint extends Translation2d {

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
