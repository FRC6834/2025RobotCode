package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AlgaeConstants;
import frc.robot.subsystems.AlgaeSubsystem;

public class AlgaeCommand extends Command{

    //called when the command is initialized
    @Override
    public void initialize() {
        AlgaeSubsystem.moveToPosition(AlgaeConstants.AlgaeArmSetpoints.kHome);
    }

    //called every time the command is scheduled
    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
        AlgaeSubsystem.stopAlgaeIntake();
    }
 
    @Override
    public boolean isFinished() {
        return false;
    }
}
