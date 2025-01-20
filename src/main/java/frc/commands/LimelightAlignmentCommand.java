package frc.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;

public class LimelightAlignmentCommand extends Command{
    //called when the command is initialized
    @Override
    public void initialize(){

    }
    //called every time the command is scheduled
    @Override
    public void execute(){
        final DriveSubsystem drive = new DriveSubsystem(); //gets drivesubsystem to run drivecommand
        double x = getX(); //gets distance from the center of the limelight's view
        final int turnDirection = getTurnDirection(x); //sees if bot should go left or right
        
        //the reason u wanna run inbetween +-1 instead of only stopping at 0 is for margin of error
        while(x>1&&x<-1){ //only runs if X is between -1 and 1
            x = getX();
            new DriveCommand(drive, 0,0,0,turnDirection); //rotates
        }
    }

    @Override
    public void end(boolean interrupted){

    }

    @Override
    public boolean isFinished(){
        return false;
    }

    //gets X-distance from center of limelight
    private double getX(){
        //initializes table
        //limelight sends data of anything it's scanned to the robot network every 100 ms which is why its called networkTable
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        return table.getEntry("tx").getDouble(0.0);
    }

    //checks if the bot should turn left or right
    private int getTurnDirection(double x){
        if(x>0){return -5;
        }else{return 5;}
    }
}
