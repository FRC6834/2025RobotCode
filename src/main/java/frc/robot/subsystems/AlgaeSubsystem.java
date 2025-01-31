// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.sound.sampled.SourceDataLine;

import com.ctre.phoenix.motorcontrol.IFollower;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants.AlgaeConstants;

public class AlgaeSubsystem extends SubsystemBase {
  // Initialize arm SPARK. We will use MAXMotion position control for the arm, so we also need to initialize the closed loop controller and encoder.
  private SparkMax armMotor = new SparkMax(AlgaeConstants.kAlgaePivot, MotorType.kBrushless);
  private SparkClosedLoopController armController = armMotor.getClosedLoopController();
  private RelativeEncoder armEncoder = armMotor.getEncoder();

  // Initialize intake SPARK. We will use open loop control for this so we don't need a closed loop controller like above.
  private SparkMax intakeMotor = new SparkMax(AlgaeConstants.kAlgaeIntake, MotorType.kBrushless);

  // Member variables for subsystem state management
  private boolean stowWhenIdle = true;
  private boolean wasReset = false;
  

  public AlgaeSubsystem() {
    /*
     * Apply the configuration to the SPARKs.
     *
     * kResetSafeParameters is used to get the SPARK to a known state. This
     * is useful in case the SPARK is replaced.
     *
     * kPersistParameters is used to ensure the configuration is not lost when
     * the SPARK loses power. This is useful for power cycles that may occur
     * mid-operation.
     */
    intakeMotor.configure(
        Configs.AlgaeSubsystem.intakeConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
    armMotor.configure(
        Configs.AlgaeSubsystem.armConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);

    // Zero arm encoder on initialization
    armEncoder.setPosition(0);
  }

  /** Zero the arm encoder when the user button is pressed on the roboRIO */
  private void zeroOnUserButton() {
    if (!wasReset && RobotController.getUserButton()) {
      // Zero the encoder only when button switches from "unpressed" to "pressed" to prevent
      // constant zeroing while pressed
      wasReset = true;
      armEncoder.setPosition(0);
    } else if (!RobotController.getUserButton()) {
      wasReset = false;
    }
  }

  /**
   * Command to run the algae intake. This will extend the arm to its "down" position and run the
   * motor at its "forward" power to intake the ball.
   *
   * <p>This will also update the idle state to hold onto the ball when this command is not running.
   */
  public Command runIntakeCommand() {
    return this.run(
        () -> {
          stowWhenIdle = false;
          setIntakePower(AlgaeConstants.IntakeSetpoints.kForward);
          setIntakePosition(AlgaeConstants.ArmSetpoints.kDown);
        });
  }

  /**
   * Command to run the algae intake in reverse. This will extend the arm to its "hold" position and
   * run the motor at its "reverse" power to eject the ball.
   *
   * <p>This will also update the idle state to stow the arm when this command is not running.
   */
  public Command reverseIntakeCommand() {
    return this.run(
        () -> {
          stowWhenIdle = true;
          setIntakePower(AlgaeConstants.IntakeSetpoints.kReverse);
          setIntakePosition(AlgaeConstants.ArmSetpoints.kHold);
        });
  }

  /** Command to force the subsystem into its "stow" state. */
  public Command stowCommand() {
    return this.runOnce(
        () -> {
          stowWhenIdle = true;
        });
  }

  /**
   * Command to run when the intake is not actively running. When in the "hold" state, the intake
   * will stay in the "hold" position and run the motor at its "hold" power to hold onto the ball.
   * When in the "stow" state, the intake will stow the arm in the "stow" position and stop the
   * motor.
   */
  public Command idleCommand() {
    return this.run(
        () -> {
          if (stowWhenIdle) {
            setIntakePower(0.0);
            setIntakePosition(AlgaeConstants.ArmSetpoints.kStow);
          } else {
            setIntakePower(AlgaeConstants.IntakeSetpoints.kHold);
            setIntakePosition(AlgaeConstants.ArmSetpoints.kHold);
          }
        });
  }

  /** Set the intake motor power in the range of [-1, 1]. */
  private void setIntakePower(double power) {
    intakeMotor.set(power);
  }

  /** Set the arm motor position. This will use closed loop position control. */
  private void setIntakePosition(double position) {
    armController.setReference(position, ControlType.kPosition);
  }

  @Override
  public void periodic() {
    zeroOnUserButton();

    // Display subsystem values
    SmartDashboard.putNumber("Algae/Arm/Position", armEncoder.getPosition());
    SmartDashboard.putNumber("Algae/Intake/Applied Output", intakeMotor.getAppliedOutput());

  }

}