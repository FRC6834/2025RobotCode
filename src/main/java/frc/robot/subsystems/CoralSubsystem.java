package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;

import frc.robot.Constants.CoralConstants;

public class CoralSubsystem extends SubsystemBase{
    private static final SparkMax kArmFlipSparkMax = new SparkMax(CoralConstants.kCoralArm, MotorType.kBrushless);
    private static SparkClosedLoopController armController = kArmFlipSparkMax.getClosedLoopController();
    
    private static RelativeEncoder armEncoder = kArmFlipSparkMax.getEncoder();
    private static final SparkMax kIntakeSystem = new SparkMax(CoralConstants.kCoralIntake, MotorType.kBrushless);
    
  public static void startCoralIntake(){
    kIntakeSystem.set(1);
  }

  public static void stopCoralIntake(){
    kIntakeSystem.set(0);
  }

  public static void shootCoralIntake(){
    kIntakeSystem.set(-1);
  }
    
    
  public static double targetAngle = 0;
  public static boolean isMoving;
  
    public void armMove() {
      kArmFlipSparkMax.set(.3);
    }
  
    public void armHouse() {
      kArmFlipSparkMax.set(-.3);
    }
  
    public void armStop() {
      kArmFlipSparkMax.set(0);
    }
  
    public static void setConstants(double position, boolean isGoing){
      targetAngle = position;
      isMoving = isGoing;
  }

  //Gets the target angle and minuses the current and uses that value to move to that position
  public static void moveToPosition(double position){
    double movementError = targetAngle - armEncoder.getPosition();
    
    armController.setReference(movementError, ControlType.kMAXMotionPositionControl);
    if(armEncoder.getPosition() == targetAngle){
      if(targetAngle == CoralConstants.CoralArmSetpoints.kFeederStation){
        startCoralIntake();
      } else {
        shootCoralIntake();
      }
      
    }
  }
}
