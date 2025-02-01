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
  
    // initializing pid controller and encoder for elevator
    PIDController elevatorPID = new PIDController(1.0, 0.0, 0.0); // 2/1/2025 - idk what the constants here should be, will figure out soon
     private RelativeEncoder elevatorEncoder = kElevatorSubsystem.getEncoder();
  
  // 2-1/2025 moving the elevator accordingly based on encoder + pid information
  public void moveToSetpoint(double coralLevel){ // 2/1/2025 - setting the speed to move to the setpoint
    double pidDifference = elevatorPID.calculate(elevatorEncoder.getPosition(), coralLevel); // 2/1/2025 - using the pid controller to determine how the motor should move according to the elevator's current position (which is calculated by the encoder) and the goal current level 
    kElevatorSubsystem.set(pidDifference); // 2/1/2025 - using the pidDifference to move the motor accordingly 
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
public class ElevatorSubsystem extends SubsystemBase { // elevator build notes: ~90 inches tall, two motors (one for each side)
  
  private final SparkMax kElevatorSubsystem = new SparkMax(ElevatorConstants.kElevatorSubsystem, MotorType.kBrushless);
    public double targetHeight;
    public boolean isGoingUp;
  
    // initializing pid controller and encoder for elevator
    PIDController elevatorPID = new PIDController(1.0, 0.0, 0.0); // 2/1/2025 - idk what the constants here should be, will figure out soon
     private RelativeEncoder elevatorEncoder = kElevatorSubsystem.getEncoder();
  
  // 2-1/2025 moving the elevator accordingly based on encoder + pid information
  public void moveToSetpoint(double coralLevel){ // 2/1/2025 - setting the speed to move to the setpoint
    double pidDifference = elevatorPID.calculate(elevatorEncoder.getPosition(), coralLevel); // 2/1/2025 - using the pid controller to determine how the motor should move according to the elevator's current position (which is calculated by the encoder) and the goal current level 
    kElevatorSubsystem.set(pidDifference); // 2/1/2025 - using the pidDifference to move the motor accordingly 
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
}
  /** Creates a new DriveSubsystem. */
  