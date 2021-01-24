// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.drivetrain.*;
import frc.robot.drivetrain.commands.MoveToPose;

import java.util.Timer;
import java.util.TimerTask;

import static frc.robot.RobotConstants.ROBOT_TYPE;
import static frc.robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.*;
import static frc.robot.drivetrain.DriveTrainConstants.PERIMETER_METER;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  DriveTrain driveTrain;
  DriveTrainComponents driveTrainComponents;
  DriveTrainVirtualComponents driveTrainVirtualComponents;
  SimulationDriveTrainComponents simulationDriveTrainComponents;
  private edu.wpi.first.wpilibj.Timer timer;
  private double prevVel;
  private double prevTime;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    if (ROBOT_TYPE == RobotType.A) {
      driveTrainComponents = new BasicDriveTrainComponentsA();
      simulationDriveTrainComponents = new SimulationDriveTrainComponentsA();
      driveTrainVirtualComponents = new DriveTrainVirtualComponentsA(driveTrainComponents, simulationDriveTrainComponents);
    } else {
      driveTrainComponents = null;
      driveTrainVirtualComponents = null;
      simulationDriveTrainComponents = null;
    }

    driveTrain = new DriveTrain(driveTrainComponents, driveTrainVirtualComponents, simulationDriveTrainComponents);

    new DriverOI(driveTrain);
    new DeputyOI();

    timer = new edu.wpi.first.wpilibj.Timer();
    prevVel = 0;
    prevTime = 0;
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        driveTrain.setNeutralModeToCoast();
      }
    }, 3000);
  }

  @Override
  public void disabledPeriodic() {
  }


  @Override
  public void autonomousInit() {

    CommandScheduler.getInstance().schedule(new MoveToPose(driveTrain, new Pose(4, 2.2, 0)));
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    driveTrain.setNeutralModeToBrake();
    timer.reset();
    timer.start();
    // To make ka testing csv file, simply copy all subsequent prints to a text file and change extension to .csv
    System.out.println("Voltage, Time, Calculated ka"); // Headers for csv file.
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    double currVel = simulationDriveTrainComponents.getLeftMasterMotor().getSelectedSensorVelocity();
    double deltaV = currVel - prevVel;
    prevVel = currVel;


    double currTime = timer.get();
    double deltaT = currTime - prevTime;
    prevTime = currTime;

    double voltage = simulationDriveTrainComponents.getLeftMasterMotor().getMotorOutputVoltage();

    double ka = (deltaT / deltaV) * (voltage - VOLTS - VOLT_SECONDS_PER_METER * encoderUnitsToMeter(currVel)); // needs optimization

    if (currVel < 1) ka = 0;

    System.out.println(currVel + "," + currTime + "," + ka); // Input for csv file.
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  private double encoderUnitsToMeter(double encoder) {
    return encoder / ENCODER_CPR * PERIMETER_METER;
  }
}
