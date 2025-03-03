package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeArmSubsystem;

public class AlgaeArmCommand extends Command{
    private final AlgaeArmSubsystem subsystem = new AlgaeArmSubsystem();

    //called when the command is initialized
    @Override
    public void initialize() {}

    //called every time the command is scheduled
    @Override
    public void execute() {
        subsystem.algaeArmMove();
    }
 
    @Override
    public void end(boolean interrupted) {
        subsystem.algaeArmStop();
    }
 
    @Override
    public boolean isFinished() {return false;}
}
