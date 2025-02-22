
package frc.robot.subsystems;

import frc.robot.Constants.ArmConstants;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ArmSubsystem extends SubsystemBase{
  private final SparkMax kArmFlipSparkMax = new SparkMax(ArmConstants.kArmSystem, MotorType.kBrushless);
    private SparkClosedLoopController armController = kArmFlipSparkMax.getClosedLoopController();
    private RelativeEncoder armEncoder = kArmFlipSparkMax.getEncoder();
    
    public boolean stowWhenIdle = true;
    public boolean wasReset = false;
    public double targetAngle = 0;

  public void armMove() {
    kArmFlipSparkMax.set(.3);
  }

  public void armHouse() {
    kArmFlipSparkMax.set(-.3);
  }

  public void armStop() {
    kArmFlipSparkMax.set(0);
  }

  public void setConstants(double position){
    targetAngle = position;
  }

  //Gets the target angle and minuses the current and uses that value to move to that position
  public void moveToPosition(double position){
    double movementError = targetAngle - armEncoder.getPosition();
    armController.setReference(movementError, ControlType.kMAXMotionPositionControl);
  }
}

