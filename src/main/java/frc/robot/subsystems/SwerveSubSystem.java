// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SwerveSubSystem extends SubsystemBase {
    private final SwerveModule frontLeft = new SwerveModule(4, 6, true, false, 0, 0, false); 
    private final SwerveModule frontRight = new SwerveModule(1, 1, true, true, 1, 0, false); 
    private final SwerveModule backLeft = new SwerveModule(2, 5, true, false, 2, 0, false); 
    private final SwerveModule backRight = new SwerveModule(0, 2, false, true, 3, 0, false); 




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
