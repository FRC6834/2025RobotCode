package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;

import frc.robot.Constants.ArmConstants;

public class AlgaeSubsystem extends SubsystemBase {
  private final SparkMax kAlgaeIntake = new SparkMax(AlgaeConstants.kAlgaeIntake, MotorType.kBrushless);
}
