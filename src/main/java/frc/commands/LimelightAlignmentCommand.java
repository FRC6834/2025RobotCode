package frc.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class LimelightAlignmentCommand extends Command{
    //called when the command is initialized
    final private DriveSubsystem drive = new DriveSubsystem();
    final private LimelightSubsystem subsystem = new LimelightSubsystem();
    private double driveDistance;

    @Override
    public void initialize(){
        double distance = subsystem.getDistance()/39.37; //convert to meters
        new DriveCommand(drive, distance, 1, 1, 0);
    }
    //called every time the command is scheduled
    @Override
    public void execute(){
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        driveDistance = table.getEntry("ty").getDouble(-1);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished(){
        return (driveDistance>-1&&driveDistance<1); //ends when ty is between -1 and 1 instead of only ending if ty is 0 in case the ty is 0.01 instead of 0 or smth - alexander 2/22
    }
}