package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends Command{
    private final DriveSubsystem m_DriveSubsystem;
    private final double distanceMeters;
    private final double x_speed;
    private final double y_speed;
    private final double rot;
    public DriveCommand(DriveSubsystem subsystem, double meters, double xspeed, double yspeed, double rotation){
        m_DriveSubsystem = subsystem;
        distanceMeters = meters;
        x_speed = xspeed;
        y_speed = yspeed;
        rot = rotation;
    }
     //called when the command is initialized
     @Override
     public void initialize(){
       m_DriveSubsystem.resetEncoders();
       m_DriveSubsystem.drive(x_speed, y_speed,rot, true, true);
     }
     //called every time the command is scheduled
     @Override
     public void execute(){
        m_DriveSubsystem.drive(x_speed, y_speed,rot, true, true);
     }
 
     @Override
     public void end(boolean interrupted){
       m_DriveSubsystem.drive(0, 0,0, false, false);
     }
 
     @Override
     public boolean isFinished(){
         return m_DriveSubsystem.getPose().getX() >= distanceMeters;
     }
 }
 
