package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.CoralIntakeSubsystem;

public class CoralIntakeCommand extends Command{

    private final CoralIntakeSubsystem subsystem = new CoralIntakeSubsystem();

    //called when the command is initialized
    @Override
    public void initialize(){}
    //called every time the command is scheduled
    @Override
    public void execute(){subsystem.startIntake();}
 
    @Override
    public void end(boolean interrupted){subsystem.stopIntake();}
 
    @Override
    public boolean isFinished(){return false;}
 }
 
