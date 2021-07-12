package frc.robot.yawControll;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.turret.Turret;
import frc.robot.turret.TurretComponents;
import frc.robot.turret.commands.MoveTurretToAngleAndKeep;

public class YawControl extends Turret {

    private final DriveTrain driveTrain;
    private TurretState turretState;
    public YawControl(TurretComponents turretComponents, DriveTrain driveTrain) {
        super(turretComponents);
        this.driveTrain = driveTrain;
        turretState = TurretState.RTR;
//        Shuffleboard.getTab("Turret").addNumber("Turret Angle RTR",()-> getAngleRTR());
//        Shuffleboard.getTab("Turret").addNumber("Encoder units",()-> turretComponents.getEncoder().getCount());
        Shuffleboard.getTab("Turret").addNumber("Turret Angle RTF",()-> getTurretAngleRTF());
//        Shuffleboard.getTab("Turret").addString("Turret State",()-> turretState.toString());

    }

    public double getTurretAngleRTF() {
        return Math.abs(getRobotAngle() - getAngleRTR());
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
}
