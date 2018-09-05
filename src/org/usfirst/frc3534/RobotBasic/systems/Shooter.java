package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter {
	
	private WPI_TalonSRX shooter = RobotMap.shooter;
	
	public String state = "stop";
	
	public String autonState = "stop";
	
	private double shooterPower = 0;
	
	private String[] buttonNames = {"shoot", "intake"};
	
	private double[] buttonPowers = {0.5, -0.5};
	
	private double[] buttonTimes = {0.5, 0.75};
	
	ButtonProcess shooterButton;
	
	public Shooter() {
		
		shooterButton = new ButtonProcess(buttonNames, buttonTimes, "stop", Robot.designatedLoopPeriod / 1000);
		
	}
	
	public void process() {
		
		if(Robot.teleop && Robot.enabled) {
			
			boolean[] stateButtons = {Robot.oi.getController1().getAButtonPressed(), Robot.oi.getController1().getBButtonPressed()};
			
			switch(shooterButton.process(stateButtons)) {
			
			/*
			 * for each case, type "caseName" from buttonNames in order, preferably
			 * 
			 * KEEP STOP CASE
			 */
			case "shoot":
				
				shooterPower = buttonPowers[0];
				
				break;
				
			case "intake":
				
				shooterPower = buttonPowers[1];
				
				break;
				
			case "stop":
				
				shooterPower = 0;
				
				break;
			
			default:
				
				shooterPower = 0;
			
			}
			
		}else if(Robot.autonomous) {
			
			switch(autonState) {
			
			/*
			 * for each case, type "caseName" from buttonNames in order, preferably
			 * 
			 * KEEP STOP CASE
			 */
			case "shoot":
				
				shooterPower = buttonPowers[0];
				
				break;
				
			case "intake":
				
				shooterPower = buttonPowers[1];
				
				break;
				
			case "stop":
				
				shooterPower = 0;
				
				break;
			
			default:
				
				shooterPower = 0;
			
			}
			
		}else {
			
			shooterPower = 0;
			
		}
		
		shooter.set(shooterPower);
		
	}
	
	public void setShooterPower(String state) {
		
		autonState = state;
		
	}
	
	public String getShooterPower() {
		
		return autonState;
		
	}
}