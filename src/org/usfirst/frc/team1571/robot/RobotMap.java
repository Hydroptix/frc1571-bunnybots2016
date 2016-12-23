package org.usfirst.frc.team1571.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class RobotMap {	
	//Declare all components on the robot. RobotBuilder should have done this for me, but it didn't work. Don't try to use RobotBuilder to dynamically alter code, because the old code won't get removed.

	//aimSystem components and variables
		public static CANTalon aimTalon;
			public static double aimSpeed = -1;
	
    //driveSystem components and variables
		public static CANTalon steeringTalon;
			public static int steeringMinCounts = 0;
			public static int steeringMaxCounts = 8;
			public static int steeringCountsRange = steeringMaxCounts - steeringMinCounts;
		
		public static CANTalon driveTalonLeftMaster;
			public static double driveSpeed = 1;
		
		public static CANTalon driveTalonLeftSlave; //All slave talons will later be set to the follower control mode
		public static CANTalon driveTalonRightSlave_1; //right talons will be set to reverse output later
		public static CANTalon driveTalonRightSlave_2;
		
		public static DigitalInput driveLimit;
		
	//flywheels components and variables
		public static CANTalon flywheelTalonMaster;
			public static double flywheelSpeed = 1;
		
		public static CANTalon flywheelTalonSlave;
		
	//gearbox components and variables
		public static Solenoid gearboxSolenoid_Gear1;
		public static Solenoid gearboxSolenoid_Gear2;
			public static double gearChangeDelaySeconds = 0;
			
		public static Solenoid gearboxSolenoidGears[] = {gearboxSolenoid_Gear1,gearboxSolenoid_Gear2};
		
	//shooter components and variables
		public static DoubleSolenoid shooterSolenoid;
			public static double extendTime = .05;
			public static double fireDelay = .05;
    

    public static void init() {

    	//instantiate all components to pass to Robot.java and add them to LiveWindow
        
    	//aimSystem components
    	aimTalon = new CANTalon(3);
    		LiveWindow.addActuator("aimSystem", "Aiming Talon", aimTalon);
    		aimTalon.reverseOutput(false);
    		aimTalon.setExpiration(.1);
        
        //driveSystem components
        steeringTalon = new CANTalon(4);
			LiveWindow.addActuator("driveSystem", "Steering Talon", steeringTalon);
			steeringTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
			steeringTalon.changeControlMode(CANTalon.TalonControlMode.Position);
			steeringTalon.configEncoderCodesPerRev(28);
			steeringTalon.setSafetyEnabled(true);
			steeringTalon.setExpiration(.1);
			steeringTalon.setAllowableClosedLoopErr(1);
			
		driveTalonLeftMaster = new CANTalon(6);
			LiveWindow.addActuator("driveSystem", "Left Master Drive Talon", driveTalonLeftMaster);
			driveTalonLeftMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			driveTalonLeftMaster.reverseOutput(false);
			
		driveTalonLeftSlave = new CANTalon(7);
			LiveWindow.addActuator("driveSystem", "Left Slave Drive Talon", driveTalonLeftSlave);
			driveTalonLeftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
			driveTalonLeftSlave.set(driveTalonLeftMaster.getDeviceID());
			driveTalonLeftSlave.reverseOutput(false);
			
		driveTalonRightSlave_1 = new CANTalon(0);
			LiveWindow.addActuator("driveSystem", "Right Slave Drive Talon 1", driveTalonRightSlave_1);
			driveTalonRightSlave_1.changeControlMode(CANTalon.TalonControlMode.Follower);
			driveTalonRightSlave_1.set(driveTalonLeftMaster.getDeviceID());
			driveTalonRightSlave_1.reverseOutput(true);
			
		driveTalonRightSlave_2 = new CANTalon(1);
			LiveWindow.addActuator("driveSystem", "Right Slave Drive Talon 2", driveTalonRightSlave_2);
			driveTalonRightSlave_2.changeControlMode(CANTalon.TalonControlMode.Follower);
			driveTalonRightSlave_2.set(driveTalonLeftMaster.getDeviceID());
			driveTalonRightSlave_2.reverseOutput(true);
			
		driveLimit = new DigitalInput(0);
			LiveWindow.addSensor("driveSystem", "Steering Limit", driveLimit);
        	
        //flywheels componenets
		flywheelTalonMaster = new CANTalon(2);
			LiveWindow.addActuator("flywheels", "Master flywheel (Right)", flywheelTalonMaster);
			flywheelTalonMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			flywheelTalonMaster.reverseOutput(false);
			
		flywheelTalonSlave = new CANTalon(5);
			LiveWindow.addActuator("flywheels", "Slave flywheel (Left)", flywheelTalonSlave);
			flywheelTalonSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
			flywheelTalonSlave.set(flywheelTalonMaster.getDeviceID());
			flywheelTalonSlave.reverseOutput(true);
        	
        //gearbox components
		gearboxSolenoid_Gear1 = new Solenoid(0);
			LiveWindow.addActuator("gearbox", "Gear 1 Solenoid", gearboxSolenoid_Gear1);
		gearboxSolenoid_Gear2 = new Solenoid(1);
			LiveWindow.addActuator("gearbox", "Gear 2 Solenoid", gearboxSolenoid_Gear2);
		
        //shooter components
		shooterSolenoid = new DoubleSolenoid(2,3);
			LiveWindow.addActuator("shooter", "Shooter Solenoid", shooterSolenoid);
    }
}
