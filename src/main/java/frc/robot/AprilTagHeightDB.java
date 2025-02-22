package frc.robot;

//you need to have the height of the apriltag off the ground IN METERS in order to properly calculate distance and angle since it requires trig

public class AprilTagHeightDB{
    //PLACEHOLDER VALUES
        public static double getHeight(double IDDouble){
        final int ID = (int)IDDouble;
         return switch(ID){
            case 1 -> 5; //placeholder
            case 2 -> 5; //placeholder
            case 3 -> 46.063;
            case 4 -> 70.08;
            case 5 -> 70.08;
            case 6 -> 70.08;
            case 7 -> 70.08;
            case 8 -> 70.08;
            case 9 -> 70.08;
            case 10 -> 70.08;
            case 11 -> 70.08;
            case 12 -> 5; //placeholder
            case 13 -> 5; //placeholder
            case 14 -> 70.08;
            case 15 -> 70.08;
            case 16 -> 46.063;
            case 17 -> 70.08;
            case 18 -> 70.08;
            case 19 -> 70.08;
            case 20 -> 70.08;
            case 21 -> 11.625; //this is placeholder for testing. equal to 11 5/8 inches. | actual height is 70.08
            case 22 -> 70.08;
            default -> -1;
        };
    }
}