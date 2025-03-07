package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends Command{

  final private ElevatorSubsystem subsystem = new ElevatorSubsystem();



  //called when the command is initialized
  @Override
  public void initialize(){ 
  }
  
  //called every time the command is scheduled
  @Override
  public void execute(){
    subsystem.moveToSetpoint(Constants.ElevatorConstants.targetHeight);
  }
 
  @Override
  public void end(boolean interrupted){
    subsystem.stopElevator();
  }
  
  //command end condition. returning true ends the command so u can choose when it stops if u want
  @Override
  public boolean isFinished(){
    return false;
  } //returns false as a placeholder
 }
 
