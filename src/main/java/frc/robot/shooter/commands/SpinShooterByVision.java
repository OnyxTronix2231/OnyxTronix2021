package frc.robot.shooter.commands;

import edu.wpi.cscore.VideoSource;
import frc.robot.shooter.Shooter;
import frc.robot.vision.commands.ActByVision;
import frc.robot.vision.visionMainChallenge.Vision;

public class SpinShooterByVision extends ActByVision {

    Vision vision;
    Shooter shooter;

    public SpinShooterByVision(Shooter shooter, Vision vision) {
        super(new SpinShooterByDistance(shooter, () -> vision.getChosenTarget() == null ?
               0 : vision.getChosenTarget().getAirDistanceTurretToTarget()), vision);
        this.shooter = shooter;
        this.vision = vision;
    }

    int count = 0;

    @Override
    public void execute() {
        if (vision.getChosenTarget() != null &&  count == 0) {
            System.out.println("Distance to Target: " +  vision.getChosenTarget().getAirDistanceTurretToTarget()
                    + " \nShooter speed: " + shooter.distanceMetersToRPM(vision.getChosenTarget().getAirDistanceTurretToTarget()));
            System.out.println("Real shooter speed: " + shooter.getRPM());
            count = 10;
        }
        count--;
    }

    @Override
    public boolean isFinished() {
        if (super.isFinished()) {
            initialize();

        }
        return false;
    }
}
