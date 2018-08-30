package org.usfirst.frc3534.RobotBasic.systems;

import java.io.File;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class Drive {

	private SpeedControllerGroup rightSide = RobotMap.rightSideMotors;
	private SpeedControllerGroup leftSide = RobotMap.leftSideMotors;
	private WPI_TalonSRX frontRight = RobotMap.frontRightMotor;
	private WPI_TalonSRX frontLeft = RobotMap.frontLeftMotor;
	private DifferentialDrive drive;
	
	private Trajectory trajectory, rightTraj, leftTraj;
	
	private EncoderFollower rightFollower, leftFollower;
	
	private int step, posTraj;
	
	private boolean enabled, autonomous, teleop;
	
	private double rightPower, leftPower;
	
	public Drive() {
		
		drive = new DifferentialDrive(leftSide,rightSide);
    	drive.setSafetyEnabled(true);
    	drive.setMaxOutput(1.0);
		
	}
	
	public void process() {
		
		if(teleop && enabled) {
		
			drive.arcadeDrive(Robot.oi.getController1().getY(Hand.kRight), Robot.oi.getController1().getX(Hand.kRight));
		
		}else if(autonomous) {
			
			drive.tankDrive(leftPower, rightPower);
			
		}
	}

	public void RobotState(String state) {
		
		switch(state){
		
		case "teleop enabled":
			autonomous = false;
			teleop = true;
			enabled = true;
			break;
		case "teleop disabled":
			autonomous = false;
			teleop = true;
			enabled = false;
			break;
		case "autonomous enabled":
			autonomous = true;
			teleop = false;
			enabled = true;
			break;
		case "autonomous disabled":
			autonomous = true;
			teleop = false;
			enabled = false;
			break;
		
		}
		
	}
	
	public void setRightPower(double power) {
		
		rightPower = power;
		
	} 
	
	public void setLeftPower(double power) {
		
		leftPower = power;
		
	}

}
