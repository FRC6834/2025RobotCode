package frc.robot;
//contains miscellanious global methods

import edu.wpi.first.networktables.*;

public class HelperMethods {

    private static final double LIMELIGHT_MOUNT_ANGLE = Constants.LimelightConstants.MOUNT_ANGLE;
    private static final double LIMELIGHT_HEIGHT = Constants.LimelightConstants.MOUNT_HEIGHT;


    //uses law of sines to trianglulate distance
    public double getDistance(){
        final double TARGET_HEIGHT = getAprilTagHeight(); //placeholder. make a list of IDS and their height later
        final double TARGET_OFFSET = Math.abs(TARGET_HEIGHT-LIMELIGHT_HEIGHT);
        final double APRILTAG_ANGLE = Math.abs(90.0-LIMELIGHT_MOUNT_ANGLE);
        return (TARGET_OFFSET*(Math.sin(APRILTAG_ANGLE)))/Math.sin(LIMELIGHT_MOUNT_ANGLE);
    }

    //returns the angle needed to turn to face the apriltag, in radians
    //i stole this code from https://deep-blue-training.readthedocs.io/en/latest/section-7/limelight/
    //may or may not be a placeholder cause i dont wanna use stolen code
    public double getRotation(){
        final double cameraLensHorizontalOffset = LimelightHelpers.getTX("limelight")/getDistance();
        final double realHorizontalOffset = Math.atan(cameraLensHorizontalOffset/getDistance());
        final double rotationError = Math.atan(realHorizontalOffset / getDistance());
        return rotationError;
    }

    public double getAprilTagHeight(){
        final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        final double ID = table.getEntry("tid").getDouble(0);
        return AprilTagHeightDB.getHeight(ID);
    }
}