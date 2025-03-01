// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.Constants.IntakeConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralIntakeSubsystem extends SubsystemBase {
  private final SparkMax kIntakeSystem = new SparkMax(IntakeConstants.kIntakeSystem, MotorType.kBrushless);

  public void startIntake(){
    kIntakeSystem.set(1);
  }

  public void stopIntake(){
    kIntakeSystem.set(0);
  }

  public void shootIntake(){
    kIntakeSystem.set(-1);
  }
}
