package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeSubsystem;

public class AlgaeCommand extends Command{
    private final AlgaeSubsystem subsystem = new AlgaeSubsystem();

    //called when the command is initialized
    @Override
    public void initialize() {

    }

    //called every time the command is scheduled
    @Override
    public void execute() {
        subsystem.algaeArmMove();
        subsystem.startAlgaeIntake();
    }
 
    @Override
    public void end(boolean interrupted) {
        subsystem.algaeArmStop();
        subsystem.stopAlgaeIntake();
    }
 
    @Override
    public boolean isFinished() {
        return false;
    }
}
