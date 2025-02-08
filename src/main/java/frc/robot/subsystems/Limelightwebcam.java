package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelightwebcam {
    public static void webcam1(){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(1);
    }
    
}
