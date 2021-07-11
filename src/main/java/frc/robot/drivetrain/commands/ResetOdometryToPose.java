package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.drivetrain.DriveTrain;

public class ResetOdometryToPose extends InstantCommand {

    public ResetOdometryToPose(DriveTrain driveTrain, Pose2d pose2d){
        super(() -> driveTrain.resetOdometryToPose(pose2d));
    }
}
