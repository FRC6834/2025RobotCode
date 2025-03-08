package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.commands.DriveCommand;
import frc.robot.AprilTagHeightDB;
import frc.robot.Constants;

public class LimelightSubsystem extends SubsystemBase{

    private static final double LIMELIGHT_MOUNT_ANGLE = Constants.LimelightConstants.MOUNT_ANGLE;
    private static final double LIMELIGHT_HEIGHT = Constants.LimelightConstants.MOUNT_HEIGHT;
    
    
    public static void align(DriveSubsystem drive){
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        final double distance = getDistance();
                System.out.println("TAG DETECTED WITH ID "+table.getEntry("tid"));
                System.out.println("DISTANCE TO APRILTAG IN INCHES: "+distance);
                SmartDashboard.putNumber("Distance to nearest AprilTag (INCHES)", distance); //untested
                //final double distanceMeters = distance/39.37;
                final double angle = getHorizontalAngleRadians();
              new DriveCommand(drive, distance, 1,1,angle);
            }

            public static double getHorizontalAngleRadians(){
                final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
                final double angle = table.getEntry("tx").getDouble(0);
                return angle*(Math.PI/180);
            }
                
            //uses trig to trianglulate distance
            public static double getDistance(){       
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        final double targetAngleOffset = table.getEntry("ty").getDouble(0.0);

        final double tagHeight = getAprilTagHeight();
                
        final double targetHeightOffset = tagHeight-LIMELIGHT_HEIGHT;
                
        final double totalAngleDegrees = LIMELIGHT_MOUNT_ANGLE+targetAngleOffset;
        final double totalAngleRadians = totalAngleDegrees*(3.14159/180.0);
        
        return targetHeightOffset/Math.tan(totalAngleRadians);
    }
        
    private static double getAprilTagHeight(){
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        final double ID = table.getEntry("tid").getDouble(0);
        return AprilTagHeightDB.getHeight(ID);
    }
    
  /*  
//terrible jank code as a placeholder for testing
    public static void alignAngle(){
        double angle = getHorizontalAngleRadians();
        new DriveCommand(drive, 0, 1,1,angle);
    }
         */
}