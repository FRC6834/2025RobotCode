package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LimelightSubsystem;

public class LimelightAlignmentCommand extends Command{
    //called when the command is initialized
//final private DriveSubsystem drive = new DriveSubsystem();

    @Override
    public void initialize(){

        //note to self test the distance before u test angle
        LimelightSubsystem.align();
        //final double distance = HELPER.getDistance();
    }
    //called every time the command is scheduled
    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){

    }

    @Override
    public boolean isFinished(){
        return false;
    }
}