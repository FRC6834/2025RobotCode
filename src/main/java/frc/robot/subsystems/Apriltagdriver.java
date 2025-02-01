package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.Constants.ElevatorConstants;


public class Apriltagdriver{
    public static void Lightupsketchers(){
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        double tagID =  table.getEntry("tid").getDouble(0); //stupid syntax error that makes no sense; ill fix later

        if(tagID == 21){
        // Turn on light 
        table.getEntry("ledMode").setNumber(3);  // 3 means turn on LEDs
        }else{
        // Turn off light
        table.getEntry("ledMode").setNumber(1);  // 1 means turn off LEDs
        }
    }
    public static void levatate(){
        
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        double tagID =  table.getEntry("tid").getDouble(0);
        final SparkMax kElevatorSubsystem = new SparkMax(ElevatorConstants.kElevatorSubsystem, MotorType.kBrushless);

        if(tagID == 7){
        kElevatorSubsystem.set(-.75);
        }else{
        kElevatorSubsystem.set(.75);
        }
    }
}

