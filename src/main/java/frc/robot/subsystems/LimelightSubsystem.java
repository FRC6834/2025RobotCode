package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.commands.DriveCommand;
import frc.robot.HelperMethods;

public class LimelightSubsystem extends SubsystemBase{

    final private static HelperMethods HELPER = new HelperMethods(); //u have to make an instance of the helper method class bc these command methods r nonstatic
    final private static DriveSubsystem drive = new DriveSubsystem();
    
    
        public static void alignDistance(){
            System.out.println("DISTANCE TO APRILTAG IN INCHES: "+HELPER.getDistance());
            new DriveCommand(drive, 0, 0, 0, 5); //test
            System.out.println("WHAT if it was all a dream..");
        //final double distance = HELPER.getDistance();
        //new DriveCommand(drive, distance, 0, 0, 0); //test
    }

    public void alignAngle(){
        System.out.println(HELPER.getRotation());
        //final double angle = HELPER.getRotation();
        //new DriveCommand(drive, 0, 0, 0, 0); //test
    }

}