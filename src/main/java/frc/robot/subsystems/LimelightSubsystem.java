package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.commands.DriveCommand;
import frc.robot.AprilTagHeightDB;
import frc.robot.Constants;
import frc.robot.HelperMethods;

public class LimelightSubsystem extends SubsystemBase{

    final private static DriveSubsystem drive = new DriveSubsystem();
    private static final double LIMELIGHT_MOUNT_ANGLE = Constants.LimelightConstants.MOUNT_ANGLE;
    private static final double LIMELIGHT_HEIGHT = Constants.LimelightConstants.MOUNT_HEIGHT;
    
    
    public static void align(){
        final double distance = getDistance();
        System.out.println("DISTANCE TO APRILTAG IN INCHES: "+distance);
        SmartDashboard.putNumber("Distance to nearest AprilTag (INCHES)", distance); //untested
        final double distanceMeters = distance/39.37;
        final double angle = getAngle();
        new DriveCommand(drive, distanceMeters, 1,1,angle);
    }
                        
    public static double getAngle(){
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        final double angle = table.getEntry("tx").getDouble(0);
        return angle;
    }
        
    //uses trig to trianglulate distance
    private static double getDistance(){       
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        final double targetAngleOffset = table.getEntry("ty").getDouble(0.0);

        final double tagHeight = getAprilTagHeight();
                
        final double targetHeightOffset = tagHeight-LIMELIGHT_HEIGHT;
                
        final double totalAngleDegrees = LIMELIGHT_MOUNT_ANGLE+targetAngleOffset;
        final double totalAngleRadians = totalAngleDegrees*(3.14159/180.0);
        
        System.out.println("apriltag ID: "+table.getEntry("tid").getDouble(-1.0));
        return targetHeightOffset/Math.tan(totalAngleRadians);
    }
        
    private static double getAprilTagHeight(){
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        final double ID = table.getEntry("tid").getDouble(0);
        return AprilTagHeightDB.getHeight(ID);
    }

}