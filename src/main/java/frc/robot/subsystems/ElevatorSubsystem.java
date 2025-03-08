// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
  
  // initializions
  private static final SparkMax kElevatorSubsystemMain = new SparkMax(ElevatorConstants.kElevatorSubsystemMain, MotorType.kBrushless);
  private static final SparkMax kElevatorSubsystemFollower = new SparkMax(ElevatorConstants.kElevatorSubsystemFollower, MotorType.kBrushless);
  public static double elevatorSprocketCircumference = 2* Math. PI * 1.5; // 2/15/2025 this is in inches. also the 1.5 is just a placeholder number for the radius of the sprocket, this will need to be remeasured
  public static double targetHeight;

  //PID controller
  private static PIDController elevatorPID = new PIDController(0.1, 0.0, 0.0); // 2/1/2025 need 2 be determined with testing
  private static RelativeEncoder elevatorEncoder = kElevatorSubsystemMain.getEncoder();

  // moving elevator to setpoint
  public static void moveToSetpoint(double targetHeight){
    double setpoint = targetHeight/(2 * Math.PI * elevatorSprocketCircumference); // 2/22/2025 dividing the height of the reefLevel we want to go to by the circumfrence of the elevator sprocket to figure out what the target number of rotations is, which is the setPoint
    double pidDifference = elevatorPID.calculate(elevatorEncoder.getPosition(), setpoint); 
    kElevatorSubsystemMain.set(pidDifference); 
    kElevatorSubsystemFollower.set(pidDifference); 
  }
  
 public static void setConstants(double reefLevel){
     targetHeight = reefLevel;
    }

     public static void elevatorUp(){
      kElevatorSubsystemMain.set(.75);
      kElevatorSubsystemFollower.set(-.75);
     }
  
     public static void elevatorDown() {
      kElevatorSubsystemMain.set(-.75);
      kElevatorSubsystemFollower.set(.75);
     }

     public static void stopElevator(){
      kElevatorSubsystemMain.set(0);
      kElevatorSubsystemFollower.set(0);
    }

}