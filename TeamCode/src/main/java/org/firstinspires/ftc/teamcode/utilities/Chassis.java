package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;

/**
 * This class sets up the hardware map and registers all the motors, servos, etc.
 *
 * Created by Eric Golde on 9/12/2017.
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

		frontLeft = hardwareMap.dcMotor.get("fl");
		frontRight = hardwareMap.dcMotor.get("fr");
		backLeft = hardwareMap.dcMotor.get("bl");
		backRight = hardwareMap.dcMotor.get("br");

		//Assuming that the right motors spin the oppisite way
		frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
		backRight.setDirection(DcMotorSimple.Direction.REVERSE);

		opMode.telemetry.addData(CHASSIS_TELEMENTRY_IDENTIFIER, "Initialized everything!");

		updateTelementry();
	}

	/**
	 * ! CALL AT THE BEGINNING OF LOOP() !
	 * This will automatically update telemetry
	 */
	public void loop(){
		//We might need this, not clear as of now
		updateTelementry();
	}

	private void updateTelementry(){opMode.telemetry.update();}

}
