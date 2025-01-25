package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

//WHAT IS THE PROBLEM WITH THIS CODE
public class Apriltagdriver{
    public static void main(){
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        double tagID =  table.getEntry("tid").getDouble(0); //stupid syntax error that makes no sense; ill fix later

        if (tagID == 21) {
        // Turn on light 
        table.getEntry("ledMode").setNumber(3);  // 3 means turn on LEDs
        } else {
        // Turn off light
        table.getEntry("ledMode").setNumber(1);  // 1 means turn off LEDs
        }
    }
}