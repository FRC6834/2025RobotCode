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
import java.lang.Math;

public class ElevatorSubsystem extends SubsystemBase { // elevator build notes: ~90 inches tall, two motors (one for each side)

  private final SparkMax kElevatorSubsystemMain = new SparkMax(ElevatorConstants.kElevatorSubsystemMain, MotorType.kBrushless);
    private final SparkMax kElevatorSubsystemFollower = new SparkMax(ElevatorConstants.kElevatorSubsystemFollower, MotorType.kBrushless);
    public double targetHeight;
    public boolean isGoingUp;
    public double elevatorSprocketCircumference = 2* Math. PI * 1.5; // 2/15/2025 this is in inches. also the 1.5 is just a placeholder number for the radius of the sprocket, this will need to be remeasured

    // 2/1/2025 intitializing the pid controller (constants are TBD) and the encoder that links to motors
    PIDController elevatorPID = new PIDController(0.0000001, 0.0, 0.0); // 2/1/2025 need 2 be determined with testing
    private RelativeEncoder elevatorEncoder = kElevatorSubsystemMain.getEncoder();

    public void moveToSetpoint(double reefLevel){
    double setpoint = reefLevel/(2 * Math.PI * elevatorSprocketCircumference); // 2/22/2025 dividing the height of the reefLevel we want to go to by the circumfrence of the elevator sprocket to figure out what the target number of rotations is, which is the setPoint
    double pidDifference = elevatorPID.calculate(elevatorEncoder.getPosition(), setpoint); 
    kElevatorSubsystemMain.set(pidDifference); 
    kElevatorSubsystemFollower.set(pidDifference); 
   }
  
     public void elevatorUp(){
       kElevatorSubsystemMain.set(-.75);
     }
  
     public void elevatorDown() {
      kElevatorSubsystemMain.set(.75);
     }

     public void stopElevator(){
      kElevatorSubsystemMain.set(0);
    }
  
 
      public void setConstants(double level, boolean isUp){
        targetHeight = level;
        isGoingUp = isUp;
      }
      
    }