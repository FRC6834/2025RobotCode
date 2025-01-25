package frc.robot;

//you need to have the height of the apriltag off the ground IN METERS in order to properly calculate distance and angle since it requires trig

public class AprilTagHeightDB{
    //PLACEHOLDER VALUES
        public static double getHeight(double IDDouble){
        final int ID = (int)IDDouble;
         return switch(ID){
            case 1 -> 5;
            default -> -1;
        };
    }
}