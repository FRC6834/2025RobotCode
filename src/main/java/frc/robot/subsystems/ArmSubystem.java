
package frc.robot.subsystems;
import frc.robot.Constants.ArmConstants;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ArmSubystem {
    private SparkMax karmSystem = new SparkMax(ArmConstants.kArmSystem, MotorType.kBrushless);
    private SparkClosedLoopController armController = karmSystem.getClosedLoopController();
    private RelativeEncoder armEncoder = karmSystem.getEncoder();


    public void moveToSetpoint(){
        
    }
}
