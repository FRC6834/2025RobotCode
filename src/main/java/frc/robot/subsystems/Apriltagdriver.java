package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Apriltagdriver {
    final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tagID = table.getEntry("tid");
}


