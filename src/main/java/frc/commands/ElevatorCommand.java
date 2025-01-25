package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends Command{
  
  private final ElevatorSubsystem subsystem = new ElevatorSubsystem(); //use this to call methods from the elevator subsystem

  //!!- yo whoever on elevator team make it stop at each coral level too -!!
    
  //called when the command is initialized
  @Override
  public void initialize(){}
  //called every time the command is scheduled
  @Override
  public void execute(){}
 
  @Override
  public void end(boolean interrupted){}
 
  @Override
  public boolean isFinished(){return true;} //return true is a placeholder
 }
 
