package org.usfirst.frc3534.RobotBasic.systems;

import org.usfirst.frc3534.RobotBasic.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter {
	
	private WPI_TalonSRX shooter = RobotMap.shooter;

	public Shooter() {
		
	}
	
	public void process() {
		
		shooter.set(1.0);
		
	}
	
}
