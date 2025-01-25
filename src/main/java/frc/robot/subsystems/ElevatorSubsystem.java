// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//imports for navX

public class ElevatorSubsystem extends SubsystemBase {
   private final SparkMax kElevatorSubsystem = new SparkMax(ElevatorConstants.kElevatorSubsystem, MotorType.kBrushless);

   public void elevatorUp(){
     kElevatorSubsystem.set(-.75);
   }

   public void elevatorDown() {
    kElevatorSubsystem.set(.75);
   }

   public void stopElevator(){
    kElevatorSubsystem.set(0);
  }

  
    }
  /** Creates a new DriveSubsystem. */
  