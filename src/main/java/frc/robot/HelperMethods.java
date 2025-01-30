package frc.robot;
//contains miscellanious global methods

import edu.wpi.first.networktables.*;

public class HelperMethods {

    private static final double LIMELIGHT_MOUNT_ANGLE = Constants.LimelightConstants.MOUNT_ANGLE;
    private static final double LIMELIGHT_HEIGHT = Constants.LimelightConstants.MOUNT_HEIGHT;


    //uses trig to trianglulate distance
    public double getDistance(){       
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        final double targetAngleOffset = table.getEntry("ty").getDouble(0.0);

        final double tagHeight = getAprilTagHeight();
        
        final double targetHeightOffset = tagHeight-LIMELIGHT_HEIGHT;
        
        final double totalAngleDegrees = LIMELIGHT_MOUNT_ANGLE+targetAngleOffset;
        final double totalAngleRadians = totalAngleDegrees*(3.14159/180.0);

        System.out.println("apriltag ID "+table.getEntry("tid"));
        System.out.println("test");
        System.out.println("id "+targetAngleOffset);
        return targetHeightOffset/Math.tan(totalAngleRadians);
    }

    //returns the angle needed to turn to face the apriltag, in radians
    //i stole this code from https://deep-blue-training.readthedocs.io/en/latest/section-7/limelight/
    //may or may not be a placeholder cause i dont wanna use stolen code
    public double getRotation(){
        final double cameraLensHorizontalOffset = LimelightHelpers.getTX("limelight")/getDistance();
        final double realHorizontalOffset = Math.atan(cameraLensHorizontalOffset/getDistance());
        final double rotationError = Math.atan(realHorizontalOffset / getDistance());
        System.out.println("test");
        return rotationError;
    }

    public double getAprilTagHeight(){
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        final double ID = table.getEntry("tid").getDouble(0);
        return AprilTagHeightDB.getHeight(ID);
    }
}