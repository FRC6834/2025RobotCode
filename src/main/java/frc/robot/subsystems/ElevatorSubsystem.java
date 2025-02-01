// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants.CoralLevels;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkClosedLoopController;

//imports for navX

public class ElevatorSubsystem extends SubsystemBase {
   private final SparkMax kElevatorSubsystem = new SparkMax(ElevatorConstants.kElevatorSubsystem, MotorType.kBrushless);
   
   PIDController elevatorPID = new PIDController(1.0, 0.0, 0.0); // 2/1/2025 - idk what the constants here should be, will figure out soon
   private RelativeEncoder elevatorEncoder = kElevatorSubsystem.getEncoder();

   

// moving the elevator accordingly based on encoder + pid information
void moveToSetpoint(double coralLevel){ // setting the speed to move to the setpoint
  double pidDifference = elevatorPID.calculate(elevatorEncoder.getPosition(), coralLevel); // using the pid controller to determine how the motor should move according to the elevator's current position (which is calculated by the encoder) and the goal current level 
  kElevatorSubsystem.set(pidDifference); // using the pidDifference to move the motor accordingly 
 }

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
  