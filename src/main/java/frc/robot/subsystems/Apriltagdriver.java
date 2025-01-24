package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.subsystems.*;


public class Apriltagdriver {
    final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tagid = table.getEntry("tid");
    
    
}


