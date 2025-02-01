package frc.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.DriveSubsystem;

public class AlignWheelsCommand extends Command{
    private final XboxController m_driverController = new XboxController(0);
    private final DriveSubsystem m_robotDrive = new DriveSubsystem();

    @Override
    public void execute(){new JoystickButton(m_driverController, Button.kX.value).whileTrue(new RunCommand(() -> m_robotDrive.setX(),m_robotDrive));}
    
}