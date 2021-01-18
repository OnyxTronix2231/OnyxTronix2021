package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.RamseteController;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.Path;

import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.RAMSETE_B;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.RAMSETE_ZETA;

public class MoveByPath extends OnyxRamseteCommand {
  public MoveByPath(final DriveTrain driveTrain,
                    final Path path) {
    super(() -> driveTrain.getTrajectoryGenerator().getTrajectoryFromPoseList(path.getPoses(), path.isReversed()),
        driveTrain::getPose,
        new RamseteController(RAMSETE_B, RAMSETE_ZETA),
        driveTrain.getKinematics(),
        driveTrain::getSimWheelSpeeds,
        driveTrain::driveTrainVelocity,
        driveTrain.getFeedForward(),
        driveTrain);
  }
}
