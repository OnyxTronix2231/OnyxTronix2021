package frc.robot.crossPlatform.pathCommands;

import static frc.robot.crossPlatform.CrossPlatformConstants.CollectorConstantsA.TESTING_SPEED_COLLECTOR;
import static frc.robot.crossPlatform.CrossPlatformConstants.ConveyorConstantsA.REVOLVER_RPM_WHILE_COLLECTING;
import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.ONE_METER_FORWARD;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.collector.Collector;
import frc.robot.collector.commands.OpenAndCollect;
import frc.robot.crossPlatform.CollectAndSpinRevolver;
import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.commands.MoveByPath;
import frc.robot.revolver.Revolver;

public class DriveOneMeter extends ParallelCommandGroup {
    public DriveOneMeter(DriveTrain driveTrain, Collector collector, Revolver revolver) {
        super(new MoveByPath(driveTrain, ONE_METER_FORWARD),
                new CollectAndSpinRevolver(collector, revolver, () -> REVOLVER_RPM_WHILE_COLLECTING,
                        () -> TESTING_SPEED_COLLECTOR));
    }
}
