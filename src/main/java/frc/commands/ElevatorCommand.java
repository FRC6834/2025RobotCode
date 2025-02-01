package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends Command{
  private boolean goingUp = false;
  private double level = -1; //the height the elevator needs to go up to
  private double currentHeight = -1; //the height that the elevator is currently at use encoders to update this
  private ElevatorSubsystem subsystem; //use this to call methods from the elevator subsystem

  //!!- yo whoever on elevator team make it stop at each coral level too -!!
    

  public ElevatorCommand(boolean isUp, double level){
    this.level = level;
    if(isUp){goingUp=true;
    }else{goingUp=false;}
  }
  
  //called when the command is initialized
  @Override
  public void initialize(){
    subsystem = new ElevatorSubsystem(level);
  }
  
  //called every time the command is scheduled
  @Override
  public void execute(){
    if(goingUp){subsystem.elevatorUp();
    }else{subsystem.elevatorDown();}
  }
 
  @Override
  public void end(boolean interrupted){subsystem.stopElevator();}
  
 
  //command end condition. returning true ends the command so u can choose when it stops if u want
  @Override
  public boolean isFinished(){return false;} //returns false as a placeholder
 }
 
