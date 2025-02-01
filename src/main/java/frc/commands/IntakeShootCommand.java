package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeShootCommand extends Command{

  private final IntakeSubsystem subsystem = new IntakeSubsystem();
    //called when the command is initialized
    @Override
    public void initialize(){}
    
    //called every time the command is scheduled
    @Override
    public void execute(){subsystem.shootIntake();}
 
    @Override
    public void end(boolean interrupted){subsystem.stopIntake();}
 
    @Override
    public boolean isFinished(){return false;}
 }
 
