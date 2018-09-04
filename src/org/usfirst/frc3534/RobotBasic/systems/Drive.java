package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive {

	private SpeedControllerGroup rightSide = RobotMap.rightSideMotors;
	private SpeedControllerGroup leftSide = RobotMap.leftSideMotors;
	private DifferentialDrive drive;
	
	private double rightPower, leftPower;
	
	public Drive() {
		
		drive = new DifferentialDrive(leftSide,rightSide);
    	drive.setSafetyEnabled(true);
    	drive.setMaxOutput(1.0);
		
	}
	
	public void process() {
		
		if(Robot.teleop && Robot.enabled) {
		
			drive.arcadeDrive(Robot.oi.getController1().getY(Hand.kRight), Robot.oi.getController1().getX(Hand.kRight));
		
		}else if(Robot.autonomous) {
			
			drive.tankDrive(leftPower, rightPower);
			
		}
	}
	
	public void setRightPower(double power) {
		
		rightPower = power;
		
	} 
	
	public void setLeftPower(double power) {
		
		leftPower = power;
		
	}
}