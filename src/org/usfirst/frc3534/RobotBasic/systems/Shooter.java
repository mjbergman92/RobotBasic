package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.Robot;
import org.usfirst.frc3534.RobotBasic.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter {
	
	private WPI_TalonSRX shooter = RobotMap.shooter;
	
	public String state = "stop";
	
	private double shooterPower = 0;
	
	private double shoot = 0.5, intake = -0.5, stop = 0;
	
	private double shootTime = 0.5, intakeTime = .75; //in seconds
	
	private String[] buttonNames = {"shoot", "intake"};
	
	private double[] stateSeconds = {shootTime, intakeTime};
	
	ButtonProcess shooterButton;
	
	public Shooter() {
		
		shooterButton = new ButtonProcess(buttonNames, stateSeconds, "stop", Robot.designatedLoopPeriod / 1000);
		
	}
	
	public void process() {
		
		if(Robot.teleop && Robot.enabled) {
			
			boolean[] buttons = {Robot.oi.getController1().getAButtonPressed(), Robot.oi.getController1().getBButtonPressed()};
			
			switch(shooterButton.process(buttons)) {
			
			case "shoot":
				
				shooterPower = shoot;
				
				break;
				
			case "intake":
				
				shooterPower = intake;
				
				break;
				
			case "stop":
				
				shooterPower = 0;
				
				break;
				
			default:
					
				state = "stop";
				
				break;
			
			}
			
		}else if(Robot.autonomous) {
			
		}else {
			
			shooterPower = 0;
			
		}
		
		shooter.set(shooterPower);
		
	}
	
	public void setShooterPower(String state) {
		
		if(state == "shoot") {
			
			shooterPower = shoot;
			state = "shoot";
			
		}else if(state == "intake") {
			
			shooterPower = intake;
			state = "intake";
			
		}else {
			
			shooterPower = stop;
			state = "stop";
			
		}
	}
}