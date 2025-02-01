package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeInCommand extends Command{
  private final IntakeSubsystem subsystem = new IntakeSubsystem();

  //called once when the command is initialized
  @Override
  public void initialize(){}
  
  //called every time the command is scheduled
  @Override
  public void execute(){
    subsystem.intakeIn();
  }
 
  //called once when the isFinished command returns true
  @Override
  public void end(boolean interrupted){}
 
  //ends the command when it returns true
  @Override
  public boolean isFinished(){return false;}
 }
 
