package frc.robot.crossPlatform.pathCommands;

import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.TEST_TURN;

import frc.robot.drivetrain.DriveTrain;
import frc.robot.drivetrain.commands.MoveByPath;

public class TestTurn extends MoveByPath {

    public TestTurn(DriveTrain driveTrain) {
        super(driveTrain, TEST_TURN);
    }
}
