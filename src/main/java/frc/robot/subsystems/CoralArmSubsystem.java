package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;

import frc.robot.Constants.ArmConstants;

public class CoralArmSubsystem extends SubsystemBase{
    private final SparkMax kArmFlipSparkMax = new SparkMax(ArmConstants.kArmSystem, MotorType.kBrushless);
    private SparkClosedLoopController armController = kArmFlipSparkMax.getClosedLoopController();
    private RelativeEncoder armEncoder = kArmFlipSparkMax.getEncoder();
    
    public double targetAngle = 0;
    public boolean isMoving;

  public void armMove() {
    kArmFlipSparkMax.set(.3);
  }

  public void armHouse() {
    kArmFlipSparkMax.set(-.3);
  }

  public void armStop() {
    kArmFlipSparkMax.set(0);
  }

  public void setConstants(double position, boolean isGoing){
    targetAngle = position;
    isMoving = isGoing;
  }

  //Gets the target angle and minuses the current and uses that value to move to that position
  public void moveToPosition(double position){
    double movementError = targetAngle - armEncoder.getPosition();
    armController.setReference(movementError, ControlType.kMAXMotionPositionControl);
  }
}
