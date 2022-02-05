package frc.robot.yawControll;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.arc.commands.MoveArcToAngle;
import frc.robot.crossPlatform.ShootWhileDrivingCalc;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.turret.Turret;
import frc.robot.turret.TurretComponents;
import frc.robot.turret.commands.MoveTurretToAngleAndKeep;
import frc.robot.yawControll.commands.MoveTurretToAngleAndKeepRTF;


public class YawControl extends Turret {

    private static final Pose2d TARGET_POS = new Pose2d(1,1, new Rotation2d());
    private final DriveTrain driveTrain;
    private TurretState turretState;
    private ShootWhileDrivingCalc shootWhileDrivingCalc;

    public YawControl(TurretComponents turretComponents, DriveTrain driveTrain) {
        super(turretComponents);
        this.driveTrain = driveTrain;
        turretState = TurretState.RTR;
//        Shuffleboard.getTab("Turret").addNumber("Turret Angle RTR",()-> getAngleRTR());
//        Shuffleboard.getTab("Turret").addNumber("Encoder units",()-> turretComponents.getEncoder().getCount());
        Shuffleboard.getTab("Turret").addNumber("Turret Angle RTF", this::getTurretAngleRTF);
//        Shuffleboard.getTab("Turret").addString("Turret State",()-> turretState.toString());
        Shuffleboard.getTab("Turret").addNumber("Angle", this::getAngleToTargetByPose);
        Shuffleboard.getTab("Turret").addNumber("Robot angle", driveTrain::getHeading);
        Shuffleboard.getTab("Turret").addNumber("x", ()->driveTrain.getPose().getX());
        Shuffleboard.getTab("Turret").addNumber("y", ()->driveTrain.getPose().getY());
    }

    public double getTurretAngleRTF() {
        return -getRobotAngle() + getAngleRTR();
    }

    public void setTurretState(TurretState turretState) {
        if (getDefaultCommand() != null) {
            getDefaultCommand().cancel();
        }
        switch (turretState) {
            case RTR:
                setDefaultCommand(new MoveTurretToAngleAndKeep(this, this::getAngleRTR));
                break;
            case RTF:
                double initialTurretAngle = getAngleRTR();
                double initialRobotAngle = getRobotAngle();
                setDefaultCommand(new MoveTurretToAngleAndKeep(this,
                        () -> initialTurretAngle - getRobotAngle() + initialRobotAngle));
                break;
            case HOMING:
                setDefaultCommand(new MoveTurretToAngleAndKeep(this, () -> 0));
                break;
        }
        this.turretState = turretState;
    }

    public double angleToAngleRTF( double angle){
        return angle + getRobotAngle();
    }

    public double getRobotAngle() {
        return driveTrain.getHeading();
    }

    public enum TurretState {
        RTR,
        RTF,
        HOMING
    }

    public double getAngleToTargetByPose() {
        Pose2d currentPos = driveTrain.getPose();
        double angle = Math.toDegrees(Math.atan(-(currentPos.getY() - TARGET_POS.getY())/
                (currentPos.getX() - TARGET_POS.getX())));
        if (currentPos.getX() > TARGET_POS.getX())
            angle += 180;
        return angle;
    }

    public void setShootWhileDrivingCalc(ShootWhileDrivingCalc shootWhileDrivingCalc) {
        this.shootWhileDrivingCalc = shootWhileDrivingCalc;
        setDefaultCommand(new MoveTurretToAngleAndKeepRTF(this, ()-> this.getFixedAngle(this.shootWhileDrivingCalc)));
    }

    private double getFixedAngle(ShootWhileDrivingCalc shootWhileDrivingCalc) {
        return shootWhileDrivingCalc.getTurretFixedAngle();
    }
}
