// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.commands.CoralIntakeInCommand;
import frc.commands.IntakeShootCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.CoralIntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
    public static boolean dUp;
    // The robot's subsystems
    private final DriveSubsystem m_robotDrive = new DriveSubsystem();
    private final CoralIntakeSubsystem m_CoralIntakeSubsystem = new CoralIntakeSubsystem();
    private final ElevatorSubsystem m_ElevatorSubsystem = new ElevatorSubsystem();
    private final LimelightSubsystem m_limelight = new LimelightSubsystem();
    private final SendableChooser<Command> autoChooser;
    int buttonAClicks = -1;
      
      // The driver's controller
    XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
    
      /**
       * The container for the robot. Contains subsystems, OI devices, and commands.
       */
      
    public RobotContainer() {
            // ...
    
      // Build an auto chooser. This will use Commands.none() as the default option.
      autoChooser = AutoBuilder.buildAutoChooser();
  
      // Another option that allows you to specify the default auto by its name
      // autoChooser = AutoBuilder.buildAutoChooser("My Default Auto");
  
      SmartDashboard.putData("Auto Chooser", autoChooser);
  
      //named and registered commands for path planner
      NamedCommands.registerCommand("intakein", new CoralIntakeInCommand());
      NamedCommands.registerCommand("intakeshoot", new IntakeShootCommand());


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

    /* 2/6/2025 the code below sets the A button on the xbox controller to determine the level of the reef the elevator heads to. each
    time the a button is clicked, it cycles to a different level, and loops each time the highest level is reached. the 
    buttonAClicks variable manages the number of times the A button was clicked, and a switch within the whileTrue determines this. an if 
    statement manages the value of the buttonAClicks variable and keeps it applicable within a certain range */
        //int buttonAClicks = -1;
        new JoystickButton(m_driverController, Button.kA.value) 
        .whileTrue(new RunCommand(() ->
        { 
         switch(buttonAClicks) {
           case -1:
             m_ElevatorSubsystem.setConstants(Constants.ReefLevels.level1, true);
             break;
           case 0:
             m_ElevatorSubsystem.setConstants(Constants.ReefLevels.level2, true);
             break;
           case 1:
             m_ElevatorSubsystem.setConstants(Constants.ReefLevels.level3, true);
             break;
           case 2:
             m_ElevatorSubsystem.setConstants(Constants.ReefLevels.level4, true);
             break; 
           }
           if (buttonAClicks != 2) {
             buttonAClicks++;
           } else {
             buttonAClicks = -1;
           }
        }, m_ElevatorSubsystem))
       .whileFalse(new RunCommand(() -> m_ElevatorSubsystem.stopElevator(), m_ElevatorSubsystem));
       
    //How are we making the elevator go down? - George
  
    //B Button: Intake
    //Which intake is this? - George
    //The coral intake. Looks like maryam changed the subsystem name but not the code names. I changed it 2/8/25 - Sabina Sinchuri
    new JoystickButton(m_driverController, Button.kB.value)
      .whileTrue(new RunCommand(() -> m_CoralIntakeSubsystem.startIntake(), m_CoralIntakeSubsystem))
      .whileFalse(new RunCommand(() -> m_CoralIntakeSubsystem.stopIntake(), m_CoralIntakeSubsystem));

    //How are we making the intake go the opposite directions? We need to be able to intake it and spit it out. - George

    //X Button: Test Limelight Distance estimation
    new JoystickButton(m_driverController, Button.kX.value)
      .whileTrue(new RunCommand(() -> LimelightSubsystem.align(), m_limelight));
}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
