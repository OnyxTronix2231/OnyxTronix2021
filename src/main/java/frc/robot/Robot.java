// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.crossPlatform.ShootWhileDrivingCalc;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.DriveTrainComponents;
import frc.robot.arc.Arc;
import frc.robot.arc.ArcComponents;
import frc.robot.arc.ArcComponentsA;
import frc.robot.ballTrigger.BallTrigger;
import frc.robot.ballTrigger.BallTriggerComponents;
import frc.robot.ballTrigger.BallTriggerComponentsA;
import frc.robot.drivetrain.*;
import frc.robot.revolver.Revolver;
import frc.robot.revolver.RevolverComponents;
import frc.robot.revolver.RevolverComponentsA;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.ShooterComponents;
import frc.robot.shooter.ShooterComponentsA;
import frc.robot.turret.TurretComponents;
import frc.robot.turret.TurretComponentsA;
import frc.robot.yawControll.YawControl;

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

    private HttpCamera limeLightFeed;
    DriveTrain driveTrain;
    Shooter shooter;
    Arc arc;
    Revolver revolver;
    BallTrigger ballTrigger;
    YawControl yawControl;
    ShootWhileDrivingCalc shootWhileDrivingCalc;
    Command enemyTrenchAutonomous;
    Command ourTrenchAutonomous;
    Command moveFromLineAndShoot;
    SendableChooser<Command> autonomousChooser = new SendableChooser<>();
    Command selectedAutonomousCommand;


    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        limeLightFeed = new HttpCamera("limelight", "http://limelight.local:5800/stream.mjpg");

        LiveWindow.disableAllTelemetry();
        DriveTrainComponents driveTrainComponents;
        ShooterComponents shooterComponents;
        ArcComponents arcComponents;
        RevolverComponents revolverComponents;
        BallTriggerComponents ballTriggerComponents;
        TurretComponents turretComponents;

        if (ROBOT_TYPE == RobotType.A) {
            driveTrainComponents = new DriveTrainComponentsBase();
            shooterComponents = new ShooterComponentsA();
            arcComponents = new ArcComponentsA();
            revolverComponents = new RevolverComponentsA();
            ballTriggerComponents = new BallTriggerComponentsA();
            turretComponents = new TurretComponentsA();
        } else {
            driveTrainComponents = null;
            shooterComponents = null;
            revolverComponents = null;
            ballTriggerComponents = null;
            turretComponents = null;
            arcComponents = null;
        }

        driveTrain = new DriveTrain(driveTrainComponents);
        shooter = new Shooter(shooterComponents);
        arc= new Arc(arcComponents);
        revolver = new Revolver(revolverComponents);
        ballTrigger = new BallTrigger(ballTriggerComponents);
        yawControl = new YawControl(turretComponents, driveTrain);
        shootWhileDrivingCalc = new ShootWhileDrivingCalc(driveTrain, shooter, arc, yawControl);
        arc.setShootWhileDrivingCalc(shootWhileDrivingCalc);
        yawControl.setShootWhileDrivingCalc(shootWhileDrivingCalc);
        DriverOI driverOI = new DriverOI();
        DeputyOI deputyOI = new DeputyOI();

        driverOI.withDriveTrainOi(driveTrain)
                .withCrossPlatformOi(ballTrigger, revolver, arc, yawControl, shooter, shootWhileDrivingCalc)
                .withArc(arc)
                .withBallTrigger(ballTrigger);

        deputyOI.withRevolver(revolver)
                .withArc(arc)
                .withTurret(yawControl)
                .withCrossPlatform(ballTrigger, shooter);
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

    @Override
    public void disabledPeriodic() {
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
