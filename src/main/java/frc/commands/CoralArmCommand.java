package frc.commands;

import com.revrobotics.spark.SparkBase.ControlType;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.CoralArmSubsystem;

public class CoralArmCommand extends Command {
    final private CoralArmSubsystem subsystem = new CoralArmSubsystem();

    private boolean stowWhenIdle = true;
    private boolean wasReset = false;
    private boolean isMoving = false;
    private double targetAngle = 0;

    public void initialize() {
        isMoving = subsystem.isMoving;
        targetAngle = subsystem.targetAngle;
    }

    public void execute() {
        subsystem.moveToPosition(targetAngle);
    }
}
