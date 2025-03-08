// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.AlgaeConstants;
//import frc.commands.IntakeShootCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.AlgaeSubsystem;
import frc.robot.subsystems.Apriltagdriver;
import frc.robot.subsystems.CoralSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
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
    private final AlgaeSubsystem m_AlgaeSubsystem = new AlgaeSubsystem();
    private final CoralSubsystem m_CoralSubsystem = new CoralSubsystem();
    private final DriveSubsystem m_robotDrive = new DriveSubsystem();
    private final ElevatorSubsystem m_ElevatorSubsystem = new ElevatorSubsystem();
    private final LimelightSubsystem m_limelight = new LimelightSubsystem();
    //private final SendableChooser<Command> autoChooser;
    int buttonYClicks = 0;
    int bumperClicks = 0;
      
      // The driver's controller
    XboxController XboxController1 = new XboxController(OIConstants.kDriverControllerPort);
    CommandXboxController CommandXboxController1 = new CommandXboxController(OIConstants.kDriverControllerPort);

      /**
       * The container for the robot. Contains subsystems, OI devices, and commands.
       */
      
    public RobotContainer() {
            // ...
    
    /*  // Build an auto chooser. This will use Commands.none() as the default option.
      autoChooser = AutoBuilder.buildAutoChooser(); */
  
      // Another option that allows you to specify the default auto by its name
      // autoChooser = AutoBuilder.buildAutoChooser("My Default Auto");
  
      //SmartDashboard.putData("Auto Chooser", autoChooser);
  
      //named and registered commands for path planner
      //NamedCommands.registerCommand("intakein", new CoralCommand());
      //NamedCommands.registerCommand("intakeshoot", new IntakeShootCommand());

      // Configure the button bindings
      configureButtonBindings();

      int dPad = XboxController1.getPOV(); //scans to see which directional arrow is being pushed
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
                -MathUtil.applyDeadband(XboxController1.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(XboxController1.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(XboxController1.getRightX(), OIConstants.kDriveDeadband),
                true),
            m_robotDrive));
            
  }

   
  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController1}), and then calling
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
    new JoystickButton(XboxController1, Button.kX.value).whileTrue(new RunCommand(() -> m_robotDrive.setX(),m_robotDrive));

    // as the left bumper is clicked, the elevator increments upwards to reef levels. the coralArm also adjusts 
    // accordingly to the angle it should be (an angle for scoring in the reef or an angle for picking up from the feeder)
    new JoystickButton(XboxController1, Button.kLeftBumper.value) 
    .whileTrue(new RunCommand(() ->
    { 
      if (bumperClicks>=0 && bumperClicks<4) {     
        bumperClicks++;
        switch (bumperClicks) {
         case 0: 
          ElevatorSubsystem.setConstants(Constants.ElevatorConstants.Levels.home);
          CoralSubsystem.setConstants(Constants.CoralConstants.CoralArmSetpoints.kFeederStation, true);
          CoralSubsystem.moveToPosition(CoralSubsystem.targetAngle);
          ElevatorSubsystem.moveToSetpoint(ElevatorSubsystem.targetHeight);
          break;
          case 1:
          ElevatorSubsystem.setConstants(Constants.ElevatorConstants.Levels.level1);
          CoralSubsystem.setConstants(Constants.CoralConstants.CoralArmSetpoints.kScore_L1_to_L4, true);
          CoralSubsystem.moveToPosition(CoralSubsystem.targetAngle);
          ElevatorSubsystem.moveToSetpoint(ElevatorSubsystem.targetHeight);
          break;
          case 2: 
          ElevatorSubsystem.setConstants(Constants.ElevatorConstants.Levels.level2);
          CoralSubsystem.setConstants(Constants.CoralConstants.CoralArmSetpoints.kScore_L1_to_L4, true); 
          CoralSubsystem.moveToPosition(CoralSubsystem.targetAngle);
          ElevatorSubsystem.moveToSetpoint(ElevatorSubsystem.targetHeight);
          break;
          case 3:
          ElevatorSubsystem.setConstants(Constants.ElevatorConstants.Levels.level3);
          CoralSubsystem.setConstants(Constants.CoralConstants.CoralArmSetpoints.kScore_L1_to_L4, true);
          CoralSubsystem.moveToPosition(CoralSubsystem.targetAngle);
          ElevatorSubsystem.moveToSetpoint(ElevatorSubsystem.targetHeight);
          break;
          case 4:
          ElevatorSubsystem.setConstants(Constants.ElevatorConstants.Levels.level4);
          CoralSubsystem.setConstants(Constants.CoralConstants.CoralArmSetpoints.kScore_L1_to_L4, true);
          CoralSubsystem.moveToPosition(CoralSubsystem.targetAngle);
          ElevatorSubsystem.moveToSetpoint(ElevatorSubsystem.targetHeight);
          break;       
        }
      }
    }, m_ElevatorSubsystem))
    .whileFalse(new RunCommand(() -> ElevatorSubsystem.stopElevator(), m_ElevatorSubsystem));

    // as the right bumper is clicked, the elevator increments downwards to reef levels/the feeder station. the coralArm also adjusts 
    // accordingly to the angle it should be (an angle for scoring in the reef or an angle for picking up from the feeder)
    new JoystickButton(XboxController1, Button.kRightBumper.value) 
    .whileTrue(new RunCommand(() -> 
    { 
      if (bumperClicks<0 && bumperClicks>=4) {     
        bumperClicks--;
        switch (bumperClicks) {
          case 0:
          ElevatorSubsystem.setConstants(Constants.ElevatorConstants.Levels.home);
          CoralSubsystem.setConstants(Constants.CoralConstants.CoralArmSetpoints.kFeederStation, true);
          CoralSubsystem.moveToPosition(CoralSubsystem.targetAngle);
          ElevatorSubsystem.moveToSetpoint(ElevatorSubsystem.targetHeight);
          break;
          case 1:
          ElevatorSubsystem.setConstants(Constants.ElevatorConstants.Levels.level1);
          CoralSubsystem.setConstants(Constants.CoralConstants.CoralArmSetpoints.kScore_L1_to_L4, true);
          CoralSubsystem.moveToPosition(CoralSubsystem.targetAngle);
          ElevatorSubsystem.moveToSetpoint(ElevatorSubsystem.targetHeight);
          break;
          case 2: 
          ElevatorSubsystem.setConstants(Constants.ElevatorConstants.Levels.level2);
          CoralSubsystem.setConstants(Constants.CoralConstants.CoralArmSetpoints.kScore_L1_to_L4, true); 
          CoralSubsystem.moveToPosition(CoralSubsystem.targetAngle);
          ElevatorSubsystem.moveToSetpoint(ElevatorSubsystem.targetHeight);
          break;
          case 3:
          ElevatorSubsystem.setConstants(Constants.ElevatorConstants.Levels.level3);
          CoralSubsystem.setConstants(Constants.CoralConstants.CoralArmSetpoints.kScore_L1_to_L4, true);
          CoralSubsystem.moveToPosition(CoralSubsystem.targetAngle);
          ElevatorSubsystem.moveToSetpoint(ElevatorSubsystem.targetHeight);
          break;
          case 4:
          ElevatorSubsystem.setConstants(Constants.ElevatorConstants.Levels.level4);
          CoralSubsystem.setConstants(Constants.CoralConstants.CoralArmSetpoints.kScore_L1_to_L4, true);
          CoralSubsystem.moveToPosition(CoralSubsystem.targetAngle);
          ElevatorSubsystem.moveToSetpoint(ElevatorSubsystem.targetHeight);
          break;       
        }
      }
    }, m_ElevatorSubsystem))
    .whileFalse(new RunCommand(() -> ElevatorSubsystem.stopElevator(), m_ElevatorSubsystem));
    
    // when the left/right triggers are clicked the elevator goes up/down manually
    CommandXboxController1.leftTrigger().whileTrue(new RunCommand(() -> ElevatorSubsystem.elevatorUp(), m_ElevatorSubsystem));
    CommandXboxController1.leftTrigger().whileFalse(new RunCommand(() -> ElevatorSubsystem.stopElevator(), m_ElevatorSubsystem));
    CommandXboxController1.rightTrigger().whileTrue(new RunCommand(() -> ElevatorSubsystem.elevatorDown(), m_ElevatorSubsystem));
    CommandXboxController1.rightTrigger().whileFalse(new RunCommand(() -> ElevatorSubsystem.stopElevator(), m_ElevatorSubsystem));

    // when the back button is clicked the limelight is activated as a visual indicator for distance tracking and the april tag is set up
    // for better estimation
    new JoystickButton(XboxController1, Button.kBack.value)
    .whileTrue(new RunCommand(() -> LimelightSubsystem.align(m_robotDrive), m_limelight));
    new JoystickButton(XboxController1, Button.kBack.value)
    .whileTrue(new RunCommand(() -> Apriltagdriver.LedLightup())); 
    
    // when the x button is clicked the algae intakes in
    new JoystickButton(XboxController1, Button.kY.value)
    .whileTrue(new RunCommand(() -> AlgaeSubsystem.algaeIntakeSwallow(AlgaeConstants.AlgaeArmSetpoints.kMove), m_AlgaeSubsystem))
    .whileFalse(new RunCommand(() -> AlgaeSubsystem.stopAlgaeIntake(), m_AlgaeSubsystem));
    
    // when the y button is clicked the algae intakes out
    new JoystickButton(XboxController1, Button.kY.value)
    .whileTrue(new RunCommand(() -> AlgaeSubsystem.algaeIntakeSpit(AlgaeConstants.AlgaeArmSetpoints.kMove), m_AlgaeSubsystem))
    .whileFalse(new RunCommand(() -> AlgaeSubsystem.stopAlgaeIntake(), m_AlgaeSubsystem));

    // when the a button is clicked the coral intakes in
    new JoystickButton(XboxController1, Button.kA.value)
    .whileTrue(new RunCommand(() -> CoralSubsystem.startCoralIntake(), m_CoralSubsystem))
    .whileFalse(new RunCommand(() -> CoralSubsystem.stopCoralIntake(), m_CoralSubsystem));

    // when the b button is clicked the coral intakes out
    new JoystickButton(XboxController1, Button.kB.value)
    .whileTrue(new RunCommand(() -> CoralSubsystem.shootCoralIntake(), m_CoralSubsystem))
    .whileFalse(new RunCommand(() -> CoralSubsystem.stopCoralIntake(), m_CoralSubsystem));

    //How are we making the intake go the opposite directions? We need to be able to intake it and spit it out. - George

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

   /* commented out bcs it doesn't work - n2s to fix later
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
  */
  
  }
}

