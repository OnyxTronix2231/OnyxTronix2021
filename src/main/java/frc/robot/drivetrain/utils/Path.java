package frc.robot.drivetrain.utils;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.TrajectoryConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.commands.MoveByPath;

import java.util.Arrays;
import java.util.List;

import static frc.robot.drivetrain.DriveTrainConstants.TrajectoryConstants.*;

public class Path {
    private final Pose2d startPose;
    private final List<Translation2d> middlePoints;
    private final Pose2d endPose;
    private final TrajectoryConfig config;

    public Path(Pose2d startPose, List<Translation2d> middlePoints, Pose2d endPose,
                TrajectoryConstraint... constraints) {
        this.startPose = startPose;
        this.middlePoints = middlePoints;
        this.endPose = endPose;
        this.config = new TrajectoryConfig(MAX_SPEED_METERS_PER_SECOND, MAX_ACCELERATION_METERS_PER_SECOND_SQUARED)
                .setKinematics(DRIVE_KINEMATICS)
                .addConstraints(Arrays.asList(constraints));
    }

    public Pose2d getStartPose() {
        return startPose;
    }

    public Pose2d getEndPose() {
        return endPose;
    }

    public Path setReversed() {
        config.setReversed(true);
        return this;
    }

    public Trajectory toTrajectory(Pose2d startPose) {
        return TrajectoryGenerator.generateTrajectory(startPose, middlePoints, endPose, config);
    }

    public Trajectory toTrajectory() {
        return toTrajectory(startPose);
    }
}
