package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import frc.robot.Constants.AlgaeConstants;

public class AlgaeSubsystem extends SubsystemBase {
    
    //ID for intake part
    
    private final SparkMax kAlgaeIntake = new SparkMax(AlgaeConstants.kAlgaeIntake, MotorType.kBrushless);

    //Algae intake motor speeds
    public void startAlgaeIntake(){
        kAlgaeIntake.set(.75);
    }
    
    public void stopAlgaeIntake(){
        kAlgaeIntake.set(0);
    }
    
    public void shootAlgaeIntake(){
        kAlgaeIntake.set(-.75);
    }

    private final SparkMax kAlgaeArmSparkMax = new SparkMax(AlgaeConstants.kAlgaePivot, MotorType.kBrushless);
    private SparkClosedLoopController algaeController = kAlgaeArmSparkMax.getClosedLoopController();
    private RelativeEncoder algaeEncoder = kAlgaeArmSparkMax.getEncoder();

    public double algaeTargetAngle = 0.0;

    public void algaeArmMove(){
        kAlgaeArmSparkMax.set(.3);
    }
      
    public void algaeArmStop(){
        kAlgaeArmSparkMax.set(0);
    }
      
    public void algaeArmHouse(){
        kAlgaeArmSparkMax.set(-.3);
    }

    public void moveToPosition(double position){ // Do you need position?
        double algaeMovementError = algaeTargetAngle - algaeEncoder.getPosition();
        algaeController.setReference(algaeMovementError, ControlType.kMAXMotionPositionControl);
    }
}
