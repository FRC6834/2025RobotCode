package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.HelperMethods;
import frc.robot.subsystems.*;

public class LimelightAlignmentCommand extends Command{
    //called when the command is initialized
    final private static HelperMethods HELPER = new HelperMethods(); //u have to make an instance of the helper method class bc these command methods r nonstatic
    final private DriveSubsystem drive = new DriveSubsystem();
    
    @Override
    public void initialize(){
        //note to self test the distance before u test angle
        final double distance = HELPER.getDistance();
        new DriveCommand(drive, distance, 0, 0, 0); //test
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
