package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import frc.robot.Constants.AlgaeConstants;

public class AlgaeSubsystem extends SubsystemBase {
    
    private static final SparkMax kAlgaeIntake = new SparkMax(AlgaeConstants.kAlgaeIntake, MotorType.kBrushless);

    private static final SparkMax kAlgaeArmSparkMax = new SparkMax(AlgaeConstants.kAlgaeArm, MotorType.kBrushless);
    private static SparkClosedLoopController algaeController = kAlgaeArmSparkMax.getClosedLoopController();
    private static RelativeEncoder algaeEncoder = kAlgaeArmSparkMax.getEncoder();

    public double algaeTargetAngle = 0.0;

    // taking in algae and dropping algaeArm down
    public static void algaeIntakeSwallow(double position){
        kAlgaeIntake.set(.75);
        double algaeMovementError = position - algaeEncoder.getPosition();
        algaeController.setReference(algaeMovementError, ControlType.kMAXMotionPositionControl);
    }
    
    // holding algae in place
    public static void algaeIntakeStatic(double position){
        kAlgaeIntake.set(-0.1);
        double algaeMovementError = position - algaeEncoder.getPosition();
        algaeController.setReference(algaeMovementError, ControlType.kMAXMotionPositionControl);
    }
    
    // shooting out algae and dropping algaeArm down
    public static void algaeIntakeSpit(double position){
        kAlgaeIntake.set(-.75);
        double algaeMovementError = position - algaeEncoder.getPosition();
        algaeController.setReference(algaeMovementError, ControlType.kMAXMotionPositionControl);
    }

    // fully stopping algae intake
    public static void stopAlgaeIntake(){
        kAlgaeIntake.set(0);
    }

    // for AlgaeCommand initialization and moving the arm to it's home position
    public static void moveToPosition(double position){ 
        double algaeMovementError = position - algaeEncoder.getPosition();
        algaeController.setReference(algaeMovementError, ControlType.kMAXMotionPositionControl);
    }

  /*public static void algaeArmMove(){
        kAlgaeArmSparkMax.set(.3);
    }
      
    public static void algaeArmStop(){
        kAlgaeArmSparkMax.set(0);
    }
      
    public void algaeArmHouse(){
        kAlgaeArmSparkMax.set(-.3);
    }
    */
}
