// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final double wheelDiameterMeters = Units.inchesToMeters(3.1); 
    public static final double driveMotorGearRatio = 1 / 5.25; 
    public static final double angleMotorGearRatio = 1 / 53.3; 
    public static final double driveRot2Meter = driveMotorGearRatio * Math.PI * wheelDiameterMeters; 
    public static final double angleRot2Rad =   Math.PI * 2 * angleMotorGearRatio; 
    public static final double driveRPM2MPS = driveRot2Meter / 60; 
    public static final double angleRPM2RPS = angleRot2Rad / 60; 
    public static final double kPangle = 0.6; 
    public static final double kIangle = 0; 
    public static final double kDangle = 0; 
    //the max drive speed is small just for testing purposes. 
    public static final double MAXDriveSpeed = 1; 
    public static final double autoDriveSpeed = 0.2; 
    public static final double Deadband = 0.2; 
    
    //left and right distance  
    public static final double trackWidth = Units.inchesToMeters(20); 
    //front and back distance
    public static final double wheelBase = Units.inchesToMeters(20);
    //
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
        new Translation2d(wheelBase / 2, -trackWidth /2),
        new Translation2d(wheelBase / 2, trackWidth /2), 
        new Translation2d(-wheelBase /2, -trackWidth /2),
        new Translation2d(-wheelBase /2, trackWidth /2)); 

    //module constants 
    public static final int frontRight = 3;
    public static final int frontLeft = 0;
    public static final int backRight = 2;
    public static final int backLeft = 1;

    public static final int a_frontRight = 7;
    public static final int a_frontLeft = 4;
    public static final int a_backRight = 6;
    public static final int a_backLeft = 5;

    public static final boolean driveMotorReverse = true; 
    public static final boolean driveMotorForward = false;
    
    public static final boolean angleMotorReverse = true; 
    public static final boolean angleMotorFoward = false; 

    public static final double encoderOffsetRad = 4.376396;

}
