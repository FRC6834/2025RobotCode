package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends Command{
  private double targetHeight; // 2/1/2025 the target height is what is passed through as the coral level that they want
  private boolean isGoingUp; // 2/1/2025 this is used to managed 
  final private ElevatorSubsystem subsystem = new ElevatorSubsystem();

  //called when the command is initialized
  @Override
  public void initialize(){
    isGoingUp = subsystem.isGoingUp;
    targetHeight = subsystem.targetHeight;
    
    isGoingUp = subsystem.isGoingUp;
    targetHeight = subsystem.targetHeight;
    
  }
  
  //called every time the command is scheduled
  @Override
  public void execute(){
    subsystem.moveToSetpoint(targetHeight);
    subsystem.moveToSetpoint(targetHeight);
  }
 
  @Override
  public void end(boolean interrupted){subsystem.stopElevator();}
  
 
  //command end condition. returning true ends the command so u can choose when it stops if u want
  @Override
  public boolean isFinished(){return false;} //returns false as a placeholder
 }
 
