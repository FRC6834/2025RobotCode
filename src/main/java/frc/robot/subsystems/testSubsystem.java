package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SubsystemConstants;

public class testSubsystem extends SubsystemBase {

  public void testLimelight(){
    //initializes table
    //limelight sends data of anything it's scanned to the robot network every 100 ms which is why its called networkTable
    final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    
    //gets values of the apriltag from the entry that the limelight is dumping
    //tx and ty are how far away the target is from the middle of the screen
    //list of everything you can retrieve from the table: https://docs.limelightvision.io/docs/docs-limelight/apis/complete-networktables-api
    final double x = table.getEntry("tx").getDouble(0.0);
    final double y = table.getEntry("ty").getDouble(0.0);

    //output (debug and testing)
    System.out.println("[DEBUG PROJECT] X VALUE"+x);
    System.out.println("[DEBUG PROJECT] Y VALUE"+y+"\n");
  }
}
