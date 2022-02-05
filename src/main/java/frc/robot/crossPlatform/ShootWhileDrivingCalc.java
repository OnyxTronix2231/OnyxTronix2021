package frc.robot.crossPlatform;

import edu.wpi.first.wpilibj.drive.Vector2d;
import frc.robot.arc.Arc;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.shooter.Shooter;
import frc.robot.yawControll.YawControl;

public class ShootWhileDrivingCalc {

    private final DriveTrain drivetrain;
    private final Shooter shooter;
    private final Arc arc;
    private final YawControl yawControl;

    public ShootWhileDrivingCalc
            (DriveTrain drivetrain, Shooter shooter, Arc arc, YawControl yawControl) {
        this.drivetrain = drivetrain;
        this.shooter = shooter;
        this.arc = arc;
        this.yawControl = yawControl;
    }

    public Vector2d getShooterOnly2DVector() {
        /**the vector that represents the first movement of the ball in the air (from the side)

        robot   ^             hub
               /            \     /
           /\ /              \_ _/
        __|_|                |   |
        \---/                |   |
        ______________________________floor

        * size -> speed of the shooting system (m/s)
        * angle -> arc's direction (deg)*/

        double size = shooter.getSpeedShooterMPS(drivetrain.getDistanceToTarget());
        double angle = arc.distanceMetersToAngle(drivetrain.getDistanceToTarget());
        double x = size * Math.cos(Math.toRadians(angle));
        double y = size * Math.sin(Math.toRadians(angle));
        return new Vector2d(x, y);
    }

    public Vector2d getXZ2DVector() {
        /**the vector that represents the horizontal movement of the ball (from the top)

        robot                 hub

        /---\                _---_
        |()-+--->           {     }
        \---/                -___-

        * size -> horizontal speed of shooting system (m/s)
        * angle -> turret's direction*/

        double size = getShooterOnly2DVector().x;
        double angle = yawControl.getAngleToTargetByPose();
        double x = size * Math.cos(Math.toRadians(angle));
        double y = size * Math.sin(Math.toRadians(angle));
        return new Vector2d(x, y);
    }

    public Vector2d getMovementVector() {
        /**the vector that represents the movement of the robot (from the top)

        robot                   hub
                _->
        /---\_--               _---_
        |() |                 {     }
        \---/                  -___-

        * size -> speed of the robot (m/s)
        * angle -> robot's direction*/

        double size = drivetrain.getTotalRobotSpeedMPS();
        double angle = drivetrain.getHeading();
        double x = size * Math.cos(Math.toRadians(angle));
        double y = size * Math.sin(Math.toRadians(angle));
        return new Vector2d(x, y);
    }

    public Vector2d getCorrectTrajectory() {
        /**the vector that represents the fixed horizontal movement of the ball (from the top)
        it is fixed according to the movement of the whole robot

        robot   ^               hub
               /
        /---\/                 _---_
        |()-+                 {     }
        \---/                  -___-

        * size -> new & fixed horizontal speed of shooting system (m/s)
        * angle -> new & fixed turret's direction RTF*/

        Vector2d turretVector = getXZ2DVector();
        Vector2d movementVector = getMovementVector();
        return new Vector2d(turretVector.x - movementVector.x, turretVector.y - movementVector.y);
    }

    public Vector2d getFixedShootingVector() {
        /**the vector that represents the fixed movement of the ball in the air (from the side)
        it is fixed by the changed horizontal vector

        robot   ^             hub
               /            \     /
           /\ /              \_ _/
        __|_|                |   |
        \---/                |   |
        ______________________________floor

        * size -> fixed speed of the shooting system (m/s)
        * angle -> fixed arc's direction (deg)*/
        double x = getCorrectTrajectory().magnitude();
        double y = getShooterOnly2DVector().y;
        return new Vector2d(x, y);
    }

    public double getTurretFixedAngle() {
        Vector2d correctVector = getCorrectTrajectory();
        return Math.toDegrees(Math.atan(correctVector.y / correctVector.x));
    }

    public double getShooterFixedSpeedMPS() {
        return getFixedShootingVector().magnitude();
    }

    public double getArcFixedAngle() {
        Vector2d correctVector = getFixedShootingVector();
        return Math.toDegrees(Math.atan(correctVector.y / correctVector.x));
    }
}
