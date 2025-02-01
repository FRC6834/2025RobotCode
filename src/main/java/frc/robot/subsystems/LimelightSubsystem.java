package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperMethods;

public class LimelightSubsystem extends SubsystemBase{

    final private static HelperMethods HELPER = new HelperMethods(); //u have to make an instance of the helper method class bc these command methods r nonstatic
    final private static DriveSubsystem drive = new DriveSubsystem();
    
    
    public static void alignDistance(){
        final double distance = HELPER.getDistance();
        System.out.println("DISTANCE TO APRILTAG IN INCHES: "+distance);
        SmartDashboard.putNumber("Distance to nearest AprilTag (INCHES)", distance); //untested
        
        //new DriveCommand(drive, 0, 0, 0, 5); //test
    }

    public void alignAngle(){
        System.out.println(HELPER.getRotation());
        //final double angle = HELPER.getRotation();
        //new DriveCommand(drive, 0, 0, 0, 0); //test
    }

}