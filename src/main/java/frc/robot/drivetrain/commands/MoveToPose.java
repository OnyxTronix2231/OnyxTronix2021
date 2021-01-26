package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.RamseteController;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.Pose;

import java.util.List;

import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainConstantsA.TrajectoryParams.RAMSETE_B;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainConstantsA.TrajectoryParams.RAMSETE_ZETA;

public class MoveToPose extends OnyxRamseteCommand {
  public MoveToPose(DriveTrain driveTrain,
                    Pose finishPose) {
    super(() -> driveTrain.getTrajectoryGenerator().getTrajectoryFromPoseList(List.of(driveTrain.getPose(),
        finishPose.getPose2d()), finishPose.isForward()),
        driveTrain::getPose,
        new RamseteController(RAMSETE_B, RAMSETE_ZETA),
        driveTrain.getKinematics(),
        driveTrain::getSimWheelSpeeds,
        driveTrain::moveByVoltage,
        driveTrain.getFeedForward(),
        driveTrain);
  }

  @Override
  public void initialize() {
    super.initialize();
    System.out.println("Start");
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    System.out.println("End");
  }
}
