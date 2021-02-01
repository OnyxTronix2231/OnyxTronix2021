// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.drivetrain.*;
import frc.robot.drivetrain.commands.MoveByPath;
import frc.robot.drivetrain.skills.GSCOption;
import frc.robot.drivetrain.utils.Path;
import vision.limelight.enums.LimelightLedMode;

import java.util.Timer;
import java.util.TimerTask;

import static frc.robot.RobotConstants.ROBOT_TYPE;
import static frc.robot.drivetrain.skills.SkillsConstants.Paths.GALACTIC_SEARCH_BLUE_FIRST;
import static frc.robot.drivetrain.skills.SkillsConstants.Paths.GALACTIC_SEARCH_BLUE_SECOND;
import static frc.robot.drivetrain.skills.SkillsConstants.Paths.GALACTIC_SEARCH_RED_FIRST;
import static frc.robot.drivetrain.skills.SkillsConstants.Paths.GALACTIC_SEARCH_RED_SECOND;
import static frc.robot.drivetrain.skills.SkillsConstants.StartingPositions.GS_BLUE_FIRST_START;
import static frc.robot.drivetrain.skills.SkillsConstants.StartingPositions.GS_BLUE_SECOND_START;
import static frc.robot.drivetrain.skills.SkillsConstants.StartingPositions.GS_RED_FIRST_START;
import static frc.robot.drivetrain.skills.SkillsConstants.StartingPositions.GS_RED_SECOND_START;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    DriveTrain driveTrain;

    private Path chosenAutonomousPath;

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        DriveTrainComponents driveTrainComponents;
        SimulationDriveTrainComponents simulationDriveTrainComponents;
        DriveTrainVirtualComponents driveTrainVirtualComponents;

        if (ROBOT_TYPE == RobotType.A) {
            driveTrainComponents = new DriveTrainComponentsA();
            simulationDriveTrainComponents = new SimulationDriveTrainComponentsA();
            driveTrainVirtualComponents = new DriveTrainVirtualComponentsA(driveTrainComponents, simulationDriveTrainComponents);
        } else {
            driveTrainComponents = null;
            simulationDriveTrainComponents = null;
            driveTrainVirtualComponents = null;
        }

        driveTrain = new DriveTrain(driveTrainComponents, simulationDriveTrainComponents, driveTrainVirtualComponents);

        new DriverOI(driveTrain);
        new DeputyOI();
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
        GSCOption option = GSCOption.BLUE_FIRST;
        if (option == GSCOption.BLUE_FIRST){
            driveTrain.resetSimOdometryToPose(GS_BLUE_FIRST_START);
            chosenAutonomousPath = GALACTIC_SEARCH_BLUE_FIRST;
        }
        else {
            if (option == GSCOption.RED_FIRST) {
                driveTrain.resetSimOdometryToPose(GS_RED_FIRST_START);
                chosenAutonomousPath = GALACTIC_SEARCH_RED_FIRST;
            }
            else {
                if (option == GSCOption.BLUE_SECOND) {
                    driveTrain.resetSimOdometryToPose(GS_BLUE_SECOND_START);
                    chosenAutonomousPath = GALACTIC_SEARCH_BLUE_SECOND;
                }
                else {
                    driveTrain.resetSimOdometryToPose(GS_RED_SECOND_START);
                    chosenAutonomousPath = GALACTIC_SEARCH_RED_SECOND;
                }
            }
        }
        new MoveByPath(driveTrain, chosenAutonomousPath).schedule();
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
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
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
}
