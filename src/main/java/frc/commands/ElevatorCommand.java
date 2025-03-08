package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorCommand extends Command{

  //called when the command is initialized
  @Override
  public void initialize(){ 
    ElevatorSubsystem.moveToSetpoint(ElevatorConstants.Levels.home);
  }
  
  //called every time the command is scheduled
  @Override
  public void execute(){

  }
 
  @Override
  public void end(boolean interrupted){
    ElevatorSubsystem.stopElevator();
  }
  
  //command end condition. returning true ends the command so u can choose when it stops if u want
  @Override
  public boolean isFinished(){
    return false;
  } //returns false as a placeholder
 }
 
