package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;

/**
 * This class sets up the hardware map and registers all the motors, servos, etc.
 *
 * Created by Eric Golde on 9/12/2017.
 */

/*
	Note on how to config
	Motors:
	  - Back Left: bl
	  - Back Right: br
	  - Front Left: fl
	  - Front Right: fr

	Servos:
	  - Glyph Left: gl
	  - Glyph Right: gr

	Sensors:
	  - PixyCam: pixy
	  - Optical Distance: distance
 */
public class Chassis {

	private OpMode opMode;
	private HardwareMap hardwareMap;
	private static final String CHASSIS_TELEMENTRY_IDENTIFIER = "CHASSIS: ";

	//Motors
	public DcMotor frontLeft;
	public DcMotor frontRight;
	public DcMotor backLeft;
	public DcMotor backRight;

	//Servos
	public Servo glyphLeft;
	public Servo glyphRight;

	//Sensors
	public PixyCam pixyCam = new PixyCam();;
	//OpticalDistanceSensor distanceSensor;



	public Chassis(OpMode opMode){
		this.opMode = opMode;

	}
	/**
	 * ! CALL AT THE BEGINNING OF INIT() !
	 * This will init the chassis class. It registers everything to the hardware map, sets up all the motors etc.
	 */
	public void init(){
		//Here is where everything gets initialised
		opMode.telemetry.addData(CHASSIS_TELEMENTRY_IDENTIFIER, "Starting to Initialize everything...");
		updateTelementry();

		hardwareMap = opMode.hardwareMap;

		//Motors
		frontLeft = hardwareMap.dcMotor.get("fl");
		frontRight = hardwareMap.dcMotor.get("fr");
		backLeft = hardwareMap.dcMotor.get("bl");
		backRight = hardwareMap.dcMotor.get("br");

		//Assuming that the right motors spin the oppisite way
		frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
		backRight.setDirection(DcMotorSimple.Direction.REVERSE);

		//Servos
		glyphLeft = hardwareMap.servo.get("gl");
		glyphRight = hardwareMap.servo.get("gr");
		glyphRight.setDirection(Servo.Direction.REVERSE);

		//Sensors
		pixyCam.initialize(hardwareMap.get(com.qualcomm.robotcore.hardware.I2cDeviceSynch.class, "pixy"));
		//distanceSensor = hardwareMap.opticalDistanceSensor.get("distance");

		//Call at the end
		opMode.telemetry.addData(CHASSIS_TELEMENTRY_IDENTIFIER, "Initialized everything!");
		updateTelementry();
	}

	/**
	 * ! CALL AT THE END OF LOOP() !
	 * This will automatically update telemetry
	 */
	public void loop(){
		pixyCam.update();
		updateTelementry();
	}

	private void updateTelementry(){opMode.telemetry.update();}

}
