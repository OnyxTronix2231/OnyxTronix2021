package frc.robot.drivetrain.commands;

import static frc.robot.drivetrain.DriveTrainConstants.InfiniteRechargePaths.TEST_TURN;

import frc.robot.drivetrain.DriveTrain;

public class TestTurn extends MoveByPath{

    public TestTurn(DriveTrain driveTrain) {
        super(driveTrain, TEST_TURN);
    }
}
