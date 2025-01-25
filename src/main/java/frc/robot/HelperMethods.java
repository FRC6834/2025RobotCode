package frc.robot;
//contains miscellanious global methods


 public class HelperMethods {

    private static final int LIMELIGHT_MOUNT_ANGLE = Constants.LimelightConstants.MOUNT_ANGLE;
    private static final int LIMELIGHT_HEIGHT = Constants.LimelightConstants.MOUNT_HEIGHT;


    //uses law of sines to trianglulate distance
    public double getDistance(){
        final int TARGET_HEIGHT = 0; //placeholder. make a list of IDS and their height later
        final int TARGET_OFFSET = Math.abs(TARGET_HEIGHT-LIMELIGHT_HEIGHT);
        final int APRILTAG_ANGLE = Math.abs(90-LIMELIGHT_MOUNT_ANGLE);
        return (TARGET_OFFSET*(Math.sin(APRILTAG_ANGLE)))/Math.sin(LIMELIGHT_MOUNT_ANGLE);
    }

    //returns the angle needed to turn to face the apriltag, in radians
    //i stole this code from https://deep-blue-training.readthedocs.io/en/latest/section-7/limelight/
    //may or may not be a placeholder
    public double getRotation(){
        double cameraLensHorizontalOffset = LimelightHelpers.getTX("limelight")/getDistance();
        double realHorizontalOffset = Math.atan(cameraLensHorizontalOffset / getDistance());
        double rotationError = Math.atan(realHorizontalOffset / getDistance());
        return rotationError;
    }
}