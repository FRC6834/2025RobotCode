package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Apriltagdriver{
    public static void LedLightup(){
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        double tagID =  table.getEntry("tid").getDouble(0); //stupid syntax error that makes no sense; ill fix later
        
        
        if(tagID > 0 ){
        // Turn on light 
        table.getEntry("ledMode").setNumber(3);  // 3 means turn on LEDs
        }else{
        // Turn off light
        table.getEntry("ledMode").setNumber(1);  // 1 means turn off LEDs
        }
    }

    public static void Apriltagfound(){
        
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        double TagFound =  table.getEntry("tv").getDouble(0);
        

        if(TagFound == 1){
        SmartDashboard.putString("Apriltag found?", "Yes!");
        }else{
        SmartDashboard.putString("Apriltag found?", "No.");
        }
    }
}

