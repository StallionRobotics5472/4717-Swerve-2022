// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

/** Add your docs here. */
public class SwerveModule {
    private final CANSparkMax angleMotor;
    private final TalonFX driveMotor; 

    private final RelativeEncoder angleEncoder; 
    private final AnalogOutput magEncoder;  


    private final PIDController turningPidController; 

    private final boolean encoderInverted; 
    private final double encoderOffsetRad; 

    public SwerveModule(int driveMotorID, int angleMotorID, boolean driveMotorReverse, boolean angleMotorReverse, 
        int magEncoderID, double encoderOffsetRad, boolean encoderInverted){

        this.encoderOffsetRad = encoderOffsetRad; 
        this.encoderInverted = encoderInverted; 
        
        magEncoder = new AnalogOutput(magEncoderID); 
    
        

        driveMotor = new TalonFX(driveMotorID);  
        angleMotor = new CANSparkMax(angleMotorID, MotorType.kBrushless); 

        angleMotor.setInverted(angleMotorReverse); 
        driveMotor.setInverted(driveMotorReverse); 


        
        angleEncoder = angleMotor.getEncoder();  

        angleEncoder.setPositionConversionFactor(Constants.angleRot2Rad); 
        angleEncoder.setVelocityConversionFactor(Constants.angleRPM2RPS); 
        
        turningPidController = new PIDController(Constants.kPangle, 0, 0); 
        turningPidController.enableContinuousInput(-Math.PI, Math.PI);

        
        driveMotor.configOpenloopRamp(0.75);
        driveMotor.configClosedloopRamp(0.75); 
        driveMotor.setNeutralMode(NeutralMode.Brake);
        angleMotor.setIdleMode(IdleMode.kBrake); 
        

        resetEncoders();

    }

    public double getDrivePos(){
        return driveMotor.getSelectedSensorPosition(); 
    }

    public double getAnglePos(){
        return angleEncoder.getPosition(); 
    }

    public double getDriveVel(){
        return driveMotor.getSelectedSensorVelocity(); 
    }

    public double getAngleVel(){
        return angleEncoder.getVelocity(); 
    }

    public double getMagEncoder(){
        double angle = magEncoder.getVoltage() / RobotController.getVoltage5V(); 
        angle *= 2 * Math.PI; 
        angle -= encoderOffsetRad; 
        return angle * (encoderInverted ? -1.0 : 1.0); 
    }

    public void resetEncoders(){
        driveMotor.setSelectedSensorPosition(0); 
        angleEncoder.setPosition(getMagEncoder());
    }

    public SwerveModuleState getState(){
        return new SwerveModuleState(getDriveVel(), new Rotation2d(getAnglePos())); 
    }

    public void setDesiredState(SwerveModuleState state){

        if(Math.abs(state.speedMetersPerSecond) < 0.001){
            stop();
            return;
        }

        state = SwerveModuleState.optimize(state, getState().angle); 
        driveMotor.set(ControlMode.PercentOutput, state.speedMetersPerSecond / Constants.MAXDriveSpeed); 
        angleMotor.set(turningPidController.calculate(getAnglePos(), state.angle.getRadians()));
        SmartDashboard.putString("Swerve["+ magEncoder.getChannel() + "] State", state.toString()); 
    }


    public void stop(){
        angleMotor.set(0);
        driveMotor.set(ControlMode.PercentOutput, 0);
    }


}
