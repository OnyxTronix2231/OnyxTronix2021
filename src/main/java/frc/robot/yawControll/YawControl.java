package frc.robot.yawControll;

import frc.robot.drivetrain.DriveTrain;
import frc.robot.turret.Turret;
import frc.robot.turret.TurretComponents;
import frc.robot.turret.commands.MoveTurretToAngleAndKeep;

public class YawControl extends Turret {
    private DriveTrain driveTrain;
    public enum TurretState{
        RTR,
        RTF,
        HOMING
    }

    public YawControl(TurretComponents turretComponents, DriveTrain driveTrain) {
        super(turretComponents);
        this.driveTrain = driveTrain;
    }

    public double getTurretAngleRTF() {
        return getRobotAngle() + getAngleRTR();
    }

    public void setTurretState( TurretState turretState){
        if(getDefaultCommand() != null){
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
    }


    public double getRobotAngle(){
        return 0;
    }
}
