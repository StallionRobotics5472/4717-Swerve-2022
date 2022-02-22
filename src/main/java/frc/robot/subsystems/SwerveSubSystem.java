// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SwerveSubSystem extends SubsystemBase {

    private final SwerveModule frontLeft = new SwerveModule(
        Constants.frontLeft, 
        Constants.a_frontLeft, 
        Constants.driveMotorReverse, 
        Constants.angleMotorFoward, 
        Constants.encoderOffsetRad, false); 

    private final SwerveModule frontRight = new SwerveModule(
        Constants.frontRight, 
        Constants.a_frontRight, 
        Constants.driveMotorReverse, 
        Constants.angleMotorFoward, 
        Constants.encoderOffsetRad, false); 

    private final SwerveModule backLeft = new SwerveModule(
        Constants.backLeft, 
        Constants.a_backLeft, 
        Constants.driveMotorReverse, 
        Constants.angleMotorFoward, 
        Constants.encoderOffsetRad, false); 
        
    private final SwerveModule backRight = new SwerveModule(
        Constants.backRight, 
        Constants.a_backRight, 
        Constants.driveMotorReverse, 
        Constants.angleMotorFoward, 
        Constants.encoderOffsetRad, false); 

    public void stopModules(){
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();
    }

    public void setModuleState(SwerveModuleState[] desiredStates){
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.MAXDriveSpeed);
        frontLeft.setDesiredState(desiredStates[0]); 
        frontRight.setDesiredState(desiredStates[1]);
        backLeft.setDesiredState(desiredStates[2]);
        backRight.setDesiredState(desiredStates[3]);
    }





  
}
