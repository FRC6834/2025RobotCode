package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralSubsystem;
import frc.robot.Constants.CoralConstants;

public class CoralCommand extends Command{

    //called when the command is initialized
    @Override
    public void initialize() {
        CoralSubsystem.moveToPosition(CoralConstants.CoralArmSetpoints.kHome);
    }

    //called every time the command is scheduled
    @Override
    public void execute() {
    }
 
    //
    @Override
    public void end(boolean interrupted) {
        CoralSubsystem.stopCoralIntake();
    }
 
    @Override
    public boolean isFinished() {
        return false;
    }
 }
 
