// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.subsystems.*;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();
  private final ElevatorSubsystem m_ElevatorSubsystem = new ElevatorSubsystem();
  private final LimelightSubsystem m_limelight = new LimelightSubsystem();

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true),
            m_robotDrive));
            
  }

   
  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {
    /*
     * A Button - Button.kA.value
     * B Button - Button.kB.value
     * X Button - Button.kX.value
     * Y Button - Button.kY.value
     * Right Bumper - Button.kRightBumper.value
     * Left Bumper - Button.kLeftBumper.value
     */
    
    //resets wheels
    new JoystickButton(m_driverController, Button.kX.value).whileTrue(new RunCommand(() -> m_robotDrive.setX(),m_robotDrive));

    //A Button: Elevator
    new JoystickButton(m_driverController, Button.kA.value)
    .whileTrue(new RunCommand(() -> m_ElevatorSubsystem.elevatorUp(), m_ElevatorSubsystem))
    .whileFalse(new RunCommand(() -> m_ElevatorSubsystem.stopElevator(), m_ElevatorSubsystem));
  
    //B Button: Intake
    new JoystickButton(m_driverController, Button.kB.value)
      .whileTrue(new RunCommand(() -> m_IntakeSubsystem.startIntake(), m_IntakeSubsystem))
      .whileFalse(new RunCommand(() -> m_IntakeSubsystem.stopIntake(), m_IntakeSubsystem));


    //X Button: Test Limelight Distance estimation
    new JoystickButton(m_driverController, Button.kX.value)
      .whileTrue(new RunCommand(() -> m_limelight.alignDistance(), m_limelight));
}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(){
    return null; //pathplanner goes here
  }
}
