package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive {

	private SpeedControllerGroup right = RobotMap.rightSideMotors;
	private SpeedControllerGroup left = RobotMap.leftSideMotors;
	private WPI_TalonSRX frontRight = RobotMap.frontRightMotor;
	private WPI_TalonSRX frontLeft = RobotMap.frontLeftMotor;
	private DifferentialDrive drive;
	
	private double encoderCountsRight, encoderCountsLeft, inchesRight, inchesLeft;
	
	private boolean enabled, autonomous, teleop;
	
	public Drive() {
		
		drive = new DifferentialDrive(left,right);
    	drive.setSafetyEnabled(true);
    	drive.setMaxOutput(1.0);
		
	}
	
	public void process() {
		
		if(teleop && enabled) {
		
			drive.arcadeDrive(Robot.oi.getController1().getY(Hand.kRight), Robot.oi.getController1().getX(Hand.kRight));
		
		}else if(autonomous) {
			
			encoderCountsRight = frontRight.getSensorCollection().getQuadraturePosition();
			encoderCountsRight = frontLeft.getSensorCollection().getQuadraturePosition();
			
			inchesRight = encoderCountsRight * RobotMap.inchesPerCountMultiplier;
			
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

}
