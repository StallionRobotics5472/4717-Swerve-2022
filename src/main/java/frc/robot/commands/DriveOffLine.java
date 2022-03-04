// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.AutoSub;

public class DriveOffLine extends CommandBase {
  private AutoSub autoSub; 
  private boolean finish = false; 
  Timer timer; 
  /** Creates a new DriveOffLine. */
  public DriveOffLine(AutoSub s) {
    s = autoSub; 
    addRequirements(autoSub);
    timer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    timer.reset();
    timer.start();
    //please work im in pain 
    while(timer.get() < 3){
      autoSub.driveOffLine(-Constants.autoDriveSpeed);
    }

    finish = true; 

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    autoSub.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
