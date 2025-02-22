package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeIntakeSubsystem;

public class AlgaeIntakeCommand extends Command{

    private final AlgaeIntakeSubsystem subsystem = new AlgaeIntakeSubsystem();

    //called when the command is initialized
    @Override
    public void initialize(){}
    //called every time the command is scheduled
    @Override
    public void execute(){subsystem.startAlgaeIntake();}
 
    @Override
    public void end(boolean interrupted){subsystem.stopAlgaeIntake();}
 
    @Override
    public boolean isFinished(){return false;}
 }
 
