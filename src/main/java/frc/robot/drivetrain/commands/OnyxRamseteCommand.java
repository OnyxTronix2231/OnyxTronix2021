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
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.drivetrain.DriveTrain;

import java.util.function.BiConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;

public class OnyxRamseteCommand extends CommandBase {
  public final Timer timer = new Timer();
  private final Supplier<Trajectory> trajectorySupplier;
  private final Supplier<Pose2d> pose2dSupplier;
  private final BiConsumer<Double, Double> outputVoltage;
  private final RamseteController controller;
  private final DifferentialDriveKinematics kinematics;
  private final Supplier<DifferentialDriveWheelSpeeds> wheelSpeeds;
  private Supplier<PIDController> leftPIDControllerSupplier;
  private Supplier<PIDController> rightPIDControllerSupplier;
  private final SimpleMotorFeedforward feedforward;
  private double prevTime;
  private Trajectory trajectory;
  private DifferentialDriveWheelSpeeds prevSpeeds;
  private PIDController leftPIDController;
  private PIDController rightPIDController;
  private double lastTime;

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
    leftPIDControllerSupplier = () -> DriveTrain.pidController;
    rightPIDControllerSupplier = () -> DriveTrain.pidController;
  addRequirements(requirements);
  }

  @Override
  public void initialize() {
    System.out.println("start");
    try {
      trajectory = trajectorySupplier.get();
      leftPIDController = leftPIDControllerSupplier.get();
      rightPIDController = rightPIDControllerSupplier.get();
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
    controller.setEnabled(false);
//    System.out.println("left " + leftPIDController.getSetpoint());
//    System.out.println("'right " + rightPIDController.getSetpoint());
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
    System.out.println(timer.get());
    lastTime = timer.get();
    //System.out.println("left " + leftPIDController.getSetpoint());
    //System.out.println("right " + rightPIDController.getSetpoint());
  }

  @Override
  public void end(boolean interrupted) {
    timer.stop();
    outputVoltage.accept(0.0, 0.0);
    System.out.println("end " + lastTime);
  }

  @Override
  public boolean isFinished() {
    return timer.hasPeriodPassed(trajectory.getTotalTimeSeconds());
  }
}
