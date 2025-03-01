package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import frc.robot.Constants.AlgaeConstants;

// 2/22/2025 this is just basic code for the algae intake taken from the same frame as coral of just taking a scoring element in/out and stopping. ideal speed needs to be configured

public class AlgaeIntakeSubsystem extends SubsystemBase {
  //ID for intake part
  private final SparkMax kAlgaeIntake = new SparkMax(AlgaeConstants.kAlgaeIntake, MotorType.kBrushless);

  //Algae intake motor speeds
  public void startAlgaeIntake(){
    kAlgaeIntake.set(1);
  }
  
  public void stopAlgaeIntake(){
    kAlgaeIntake.set(0);
  }
  
  public void shootAlgaeIntake(){
    kAlgaeIntake.set(-1);
  }
}