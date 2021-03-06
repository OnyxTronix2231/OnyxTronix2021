package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.drivetrain.DriveTrain;

import java.io.PrintWriter;

public class KAPrints extends CommandBase {
    private final DriveTrain driveTrain;
    private PrintWriter leftWriter;
    private PrintWriter rightWriter;

    public KAPrints(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
    }

    @Override
    public void initialize() {
        try {
            leftWriter = new PrintWriter("/home/lvuser/Leftoutput.csv");
            rightWriter = new PrintWriter("/home/lvuser/Rightoutput.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute() {
        driveTrain.kaPrints(leftWriter, rightWriter);
    }

    @Override
    public void end(boolean interrupted) {
        leftWriter.close();
        rightWriter.close();
    }
}
