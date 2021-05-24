// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.BallTriggerComponents;
import frc.robot.ballTrigger.BallTriggerComponentsA;
import frc.robot.collector.Collector;
import frc.robot.collector.CollectorComponents;
import frc.robot.collector.CollectorComponentsA;
import frc.robot.drivetrain.*;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.RevolverComponents;
import frc.robot.revolver.RevolverComponentsA;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.ShooterComponents;
import frc.robot.shooter.ShooterComponentsA;
import frc.robot.turret.Turret;
import frc.robot.turret.TurretComponents;
import frc.robot.turret.TurretComponentsA;
import frc.robot.vision.visionMainChallenge.Vision;
import vision.configuration.VisionConfiguration;

import java.util.Timer;
import java.util.TimerTask;

import static frc.robot.RobotConstants.ROBOT_TYPE;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    DriveTrain driveTrain;
    Shooter shooter;
    Collector collector;
    Revolver revolver;
    BallTrigger ballTrigger;
    Turret turret;
    Vision vision;

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        DriveTrainComponents driveTrainComponents;
        SimulationDriveTrainComponents simulationDriveTrainComponents;
        DriveTrainVirtualComponents driveTrainVirtualComponents;
        ShooterComponents shooterComponents;
        CollectorComponents collectorComponents;
        RevolverComponents revolverComponents;
        BallTriggerComponents ballTriggerComponents;
        TurretComponents turretComponents;

        if (ROBOT_TYPE == RobotType.A) {
            driveTrainComponents = new DriveTrainComponentsA();
            simulationDriveTrainComponents = new SimulationDriveTrainComponentsA();
            if (Robot.isReal()) {
                driveTrainVirtualComponents = new DriveTrainVirtualComponentsA(driveTrainComponents);
            }
            else {
                driveTrainVirtualComponents = new DriveTrainVirtualComponentsA(simulationDriveTrainComponents);
            }
            shooterComponents = new ShooterComponentsA();
            collectorComponents = new CollectorComponentsA();
            revolverComponents = new RevolverComponentsA();
            ballTriggerComponents = new BallTriggerComponentsA();
            turretComponents = new TurretComponentsA();
        } else {
            driveTrainComponents = null;
            simulationDriveTrainComponents = null;
            driveTrainVirtualComponents = null;
            shooterComponents = null;
            collectorComponents = null;
            revolverComponents = null;
            ballTriggerComponents = null;
            turretComponents = null;

        }

        driveTrain = new DriveTrain(driveTrainComponents, simulationDriveTrainComponents, driveTrainVirtualComponents);
        shooter = new Shooter(shooterComponents);
        collector = new Collector(collectorComponents);
        revolver = new Revolver(revolverComponents);
        ballTrigger = new BallTrigger(ballTriggerComponents);
        turret = new Turret(turretComponents);
        vision = new Vision(() -> driveTrain.getHeading(), () -> turret.getAngleRTR());

        new DriverOI(driveTrain, shooter, collector, revolver, ballTrigger, turret ,vision);
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
                if (isDisabled()) driveTrain.setNeutralModeToCoast();
            }
        }, 3000);
    }

    @Override
    public void disabledPeriodic() {
    }


    @Override
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
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