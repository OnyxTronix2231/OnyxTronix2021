package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainConstantsA.TRAJECTORY_P;

public class OnyxRamseteCommand extends CommandBase {
  public final Timer timer = new Timer();
  private final Supplier<Trajectory> trajectorySupplier;
  private final Supplier<Pose2d> pose2dSupplier;
  private final BiConsumer<Double, Double> outputVoltage;
  private final RamseteController controller;
  private final DifferentialDriveKinematics kinematics;
  private final Supplier<DifferentialDriveWheelSpeeds> wheelSpeeds;
  private final SimpleMotorFeedforward feedforward;
  private final PIDController leftPIDController;
  private final PIDController rightPIDController;
  private double prevTime;
  private Trajectory trajectory;
  private DifferentialDriveWheelSpeeds prevSpeeds;

  public OnyxRamseteCommand(Supplier<Trajectory> trajectorySupplier,
                            Supplier<Pose2d> pose2dSupplier,
                            RamseteController controller,
                            DifferentialDriveKinematics kinematics,
                            Supplier<DifferentialDriveWheelSpeeds> wheelSpeeds,
                            BiConsumer<Double, Double> outputVoltage,
                            SimpleMotorFeedforward feedforward,
                            Subsystem... requirements) {
    this.trajectorySupplier = requireNonNullParam(trajectorySupplier, "trajectory", "RamseteCommand");
    this.pose2dSupplier = requireNonNullParam(pose2dSupplier, "pose", "RamseteCommand");
    this.controller = requireNonNullParam(controller, "controller", "RamseteCommand");
    this.kinematics = requireNonNullParam(kinematics, "kinematics", "RamseteCommand");
    this.wheelSpeeds = requireNonNullParam(wheelSpeeds, "wheelSpeeds", "RamseteCommand");
    this.outputVoltage = requireNonNullParam(outputVoltage, "outputVolts", "RamseteCommand");
    this.feedforward = feedforward;
    leftPIDController = new PIDController(TRAJECTORY_P, 0, 0);
    rightPIDController = new PIDController(TRAJECTORY_P, 0, 0);
    addRequirements(requirements);
  }

  @Override
  public void initialize() {
    try {
      trajectory = trajectorySupplier.get();
    } catch (Exception e) {
      System.out.println(e);
      this.cancel();
    }
    prevTime = -1;
    var initialState = trajectory.sample(0);
    prevSpeeds = kinematics.toWheelSpeeds(
        new ChassisSpeeds(initialState.velocityMetersPerSecond, 0,
            initialState.curvatureRadPerMeter * initialState.velocityMetersPerSecond));
    timer.reset();
    timer.start();
    leftPIDController.reset();
    rightPIDController.reset();
    controller.setEnabled(true);
  }

  @Override
  public void execute() {
    final double currentTime = timer.get();
    final double deltaTime = currentTime - prevTime;

    final DifferentialDriveWheelSpeeds targetWheelSpeeds = kinematics.toWheelSpeeds(
        controller.calculate(pose2dSupplier.get(), trajectory.sample(currentTime)));

    final double leftSpeedSetpoint = targetWheelSpeeds.leftMetersPerSecond;
    final double rightSpeedSetpoint = targetWheelSpeeds.rightMetersPerSecond;

    final double leftFeedforward =
        feedforward.calculate(leftSpeedSetpoint,
            (leftSpeedSetpoint - prevSpeeds.leftMetersPerSecond) / deltaTime);

    final double rightFeedforward =
        feedforward.calculate(rightSpeedSetpoint,
            (rightSpeedSetpoint - prevSpeeds.rightMetersPerSecond) / deltaTime);

    final double leftOutput = leftFeedforward
        + leftPIDController.calculate(wheelSpeeds.get().leftMetersPerSecond,
        leftSpeedSetpoint);

    final double rightOutput = rightFeedforward
        + rightPIDController.calculate(wheelSpeeds.get().rightMetersPerSecond,
        rightSpeedSetpoint);

    outputVoltage.accept(leftOutput, rightOutput);

    prevTime = currentTime;
    prevSpeeds = targetWheelSpeeds;
  }

  @Override
  public void end(boolean interrupted) {
    timer.stop();
    outputVoltage.accept(0.0, 0.0);
  }

  @Override
  public boolean isFinished() {
    return timer.hasPeriodPassed(trajectory.getTotalTimeSeconds());
  }
}
