// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase { // elevator build notes: ~90 inches tall, two motors (one for each side)
  
  private final SparkMax kElevatorSubsystem = new SparkMax(ElevatorConstants.kElevatorSubsystem, MotorType.kBrushless);
    public double targetHeight;
    public boolean isGoingUp;
  
    // 2/1/2025 intitializing the pid controller (constants are TBD) and the encoder that links to motors
    PIDController elevatorPID = new PIDController(1.0, 0.0, 0.0); // 2/1/2025 - idk what the constants here should be, will figure out soon
     private RelativeEncoder elevatorEncoder = kElevatorSubsystem.getEncoder();
  
  // 2-1/2025 using the pid controller + encoder to determine how the motor should move according to the elevator's current position according to the coral level it wants to go to
  public void moveToSetpoint(double coralLevel){
    double pidDifference = elevatorPID.calculate(elevatorEncoder.getPosition(), coralLevel); 
    kElevatorSubsystem.set(pidDifference); 
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
  
      
      public void setConstants(double level, boolean isUp){
        targetHeight = level;
        isGoingUp = isUp;
      }
      
    }