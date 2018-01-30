package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.hardware.bosch.BNO055IMU;
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
	  - Back Left: bl (0)
	  - Back Right: br (1)
	  - Front Left: fl (2)
	  - Front Right: fr (3)
	  - Glyph Motor: glym
	  - Intake Left: il
	  - Intake Right: ir

	Servos:
	  - Glyph Left: glyl
	  - Glyph Right: glyr

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
	public DcMotor intakeLeft;
	public DcMotor intakeRight;
	public DcMotor glyph;

	//Servos
	public Servo glyph1;
	public Servo glyph2;

	//Sensors
	//public PixyCam pixyCam = new PixyCam();;
	//OpticalDistanceSensor distanceSensor;

	//IMU
	public BNO055IMU imu;



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
		intakeLeft = hardwareMap.dcMotor.get("il"); //TODO: IMPORTANT: FIXME: FAILING  // just enable
		intakeRight = hardwareMap.dcMotor.get("ir"); //TODO: IMPORTANT: FIXME: FAILING // just enable
		glyph = hardwareMap.dcMotor.get("gly"); //TODO: enable me

		//Assuming that the right motors spin the opposite way
		frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
		backRight.setDirection(DcMotorSimple.Direction.REVERSE);

		//Servos

//		glyph1 = hardwareMap.servo.get("gly1");
//		glyph1.setDirection(Servo.Direction.REVERSE);
//		glyph1.setPosition(0.0);
//
//		glyph2 = hardwareMap.servo.get("gly2");
//		glyph2.setDirection(Servo.Direction.FORWARD);
//		glyph2.setPosition(0.0);

		//glyph = hardwareMap.servo.get("gly");//TODO: IMPORTANT: FIXME: FAILING  // just enable
		//glyph.setDirection(Servo.Direction.FORWARD);//TODO: IMPORTANT: FIXME: FAILING  // just enable
		//glyph.setPosition(0.0); //TODO: IMPORTANT: FIXME: FAILING  // just enable

		//Sensors
		//pixyCam.initialize(hardwareMap.get(com.qualcomm.robotcore.hardware.I2cDeviceSynch.class, "pixy"));
		//distanceSensor = hardwareMap.opticalDistanceSensor.get("distance");
		//IMU
		BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
		parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
		parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
		parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
		parameters.loggingEnabled      = true;
		parameters.loggingTag          = "IMU";

		// Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
		// on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
		// and named "imu".
		imu = hardwareMap.get(BNO055IMU.class, "imu");
		imu.initialize(parameters);

		//Call at the end
		opMode.telemetry.addData(CHASSIS_TELEMENTRY_IDENTIFIER, "Initialized everything!");
		updateTelementry();
	}

	/**
	 * ! CALL AT THE END OF LOOP() !
	 * This will automatically update telemetry
	 */
	public void loop(){
		//pixyCam.update();
		updateTelementry();
	}

	private void updateTelementry(){opMode.telemetry.update();}

}
