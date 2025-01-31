// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.subsystems.*;
import frc.robot.Constants.OIConstants;
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
    NamedCommands.registerCommand("AlignWheelsCommand", AlignWheelsCommand());
            // Configure the button bindings
            configureButtonBindings();
        
             int dPad = m_driverController.getPOV(); //scans to see which directional arrow is being pushed
             boolean dUp = false;
             boolean dDown = false;
             boolean dRight = false;
             boolean dLeft = false;
        
             if (dPad == 0){
              dUp = true;
             }
             if (dPad == 90) {
              dRight = true;
             }
             if (dPad == 180) {
              dDown = true;
             }
             if (dPad == 270) {
              dLeft = true;
             }    
        
        
            //!!! MOVING WITH THE STICKS HAS BEEN TEMPORARILY DISABLED FOR TESTING because these controllers have stickdrift and its annoying. re-enable if necessary !!!
            //You can adjust the kDriveDeadband to fix this. It's set at 0.05 right now - try 0.1 and see if the issue still persists. -George
        
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
        
           
          private Command AlignWheelsCommand() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'AlignWheelsCommand'");
      }
    
    
          private Command autoBalanceCommand() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'autoBalanceCommand'");
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
    /*This is how you reference the main buttons on the controllers - George
     * A Button - Button.kA.value
     * B Button - Button.kB.value
     * X Button - Button.kX.value
     * Y Button - Button.kY.value
     * Right Bumper - Button.kRightBumper.value
     * Left Bumper - Button.kLeftBumper.value
     * Start Button - Button.KStart.value
     */


    //We should consider using the D-pad for different scoring heights - it's not ideal, but it will allow us to get all of the subsystems working on one controller
    //For example, left (L1), down (L2), right (L3), up (L4)
    
    //resets wheels
    new JoystickButton(m_driverController, Button.kX.value).whileTrue(new RunCommand(() -> m_robotDrive.setX(),m_robotDrive));

    //A Button: Elevator Up
    new JoystickButton(m_driverController, Button.kA.value)
    .whileTrue(new RunCommand(() -> m_ElevatorSubsystem.elevatorUp(), m_ElevatorSubsystem))
    .whileFalse(new RunCommand(() -> m_ElevatorSubsystem.stopElevator(), m_ElevatorSubsystem));

    //How are we making the elevator go down? - George
  
    //B Button: Intake
    //Which intake is this? - George
    new JoystickButton(m_driverController, Button.kB.value)
      .whileTrue(new RunCommand(() -> m_IntakeSubsystem.startIntake(), m_IntakeSubsystem))
      .whileFalse(new RunCommand(() -> m_IntakeSubsystem.stopIntake(), m_IntakeSubsystem));

    //How are we making the intake go the opposite directions? We need to be able to intake it and spit it out. - George

    //X Button: Test Limelight Distance estimation
    new JoystickButton(m_driverController, Button.kX.value)
      .whileTrue(new RunCommand(() -> m_limelight.alignDistance(), m_limelight));
}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
