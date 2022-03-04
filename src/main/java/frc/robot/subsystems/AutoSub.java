// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AutoSub extends SubsystemBase {

  private WPI_TalonFX frontLeft, frontRight, backLeft, backRight; 
  MotorControllerGroup leftMotors, rightMotors;

  

  /** Creates a new AutoSub. */
  public AutoSub() {

    frontLeft = new WPI_TalonFX(Constants.frontLeft);  
    frontLeft.setNeutralMode(NeutralMode.Brake);
    frontLeft.configClosedloopRamp(0.5);
    frontLeft.configOpenloopRamp(0.5);
    frontLeft.setInverted(true);
    
    frontRight = new WPI_TalonFX(Constants.frontRight); 
    frontRight.setNeutralMode(NeutralMode.Brake);
    frontRight.configClosedloopRamp(0.5);
    frontRight.configOpenloopRamp(0.5);
    frontRight.setInverted(true);
    
    backLeft = new WPI_TalonFX(Constants.backLeft);
    backLeft.setNeutralMode(NeutralMode.Brake);
    backLeft.configClosedloopRamp(0.5);
    backLeft.configOpenloopRamp(0.5);
    backLeft.setInverted(false);
    
    backRight = new WPI_TalonFX(Constants.backRight);
    backRight.setNeutralMode(NeutralMode.Brake);
    backRight.configClosedloopRamp(0.5);
    backRight.configOpenloopRamp(0.5);
    backRight.setInverted(false);

    leftMotors = new MotorControllerGroup(frontLeft, backLeft); 
    rightMotors = new MotorControllerGroup(frontRight, backRight); 


  }

  public void driveOffLine(double speed){
    leftMotors.set(speed);
    rightMotors.set(speed);
  }

  public void stop(){
    leftMotors.set(0);
    rightMotors.set(0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
