package frc.robot;

//you need to have the height of the apriltag off the ground IN METERS in order to properly calculate distance and angle since it requires trig

public class AprilTagHeightDB{
    //PLACEHOLDER VALUES
        public static double getHeight(double IDDouble){
        final int ID = (int)IDDouble;
         return switch(ID){
            case 1 -> 5;
            case 2 -> 5;
            case 3 -> 1.17;
            case 4 -> 1.78;
            case 5 -> 1.78;
            case 6 -> 0.17;
            case 7 -> 0.17;
            case 8 -> 0.17;
            case 9 -> 0.17;
            case 10 -> 0.17;
            case 11 -> 0.17;
            case 12 -> 5;
            case 13 -> 5;
            case 14 -> 1.78;
            case 15 -> 1.78;
            case 16 -> 1.17;
            case 17 -> 0.17;
            case 18 -> 0.17;
            case 19 -> 0.17;
            case 20 -> 0.17;
            case 21 -> 11.625; //this is placeholder for testing. equal to 11 5/8 inches. | actual height is 0.17
            case 22 -> 0.17;
            default -> -1;
        };
    }
}