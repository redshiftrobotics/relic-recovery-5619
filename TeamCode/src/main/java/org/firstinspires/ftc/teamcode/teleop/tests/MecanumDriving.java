package org.firstinspires.ftc.teamcode.teleop.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utilities.Chassis;
import org.firstinspires.ftc.teamcode.utilities.RRMath;

import java.text.DecimalFormat;

import static org.firstinspires.ftc.teamcode.utilities.RRMath.clamp;

/**
 * Created by Eric Golde on 9/26/2017.
 */

@TeleOp(name = "Mecanum Driving")
@Disabled
public class MecanumDriving extends OpMode {

	private Chassis chassis = new Chassis(this);
	private DecimalFormat df = new DecimalFormat("#.##");

    private static final double MAX_FORWARD_BACK_SPEED = 0.7;
    private static final double MAX_STRAFE_SPEED = 1;
    private static final double MAX_ROTATION_SPEED = 0.5;

	@Override
	public void init(){
		chassis.init();
	}

	@Override
	public void loop(){
        //                       - to switch forward and back                  reverse to change strafing                reverse to change rotation
		double fl = RRMath.clamp(gamepad1.left_stick_y * MAX_FORWARD_BACK_SPEED + gamepad1.left_stick_x * MAX_STRAFE_SPEED - gamepad1.right_stick_x * MAX_ROTATION_SPEED);
        double fr = RRMath.clamp(gamepad1.left_stick_y * MAX_FORWARD_BACK_SPEED - gamepad1.left_stick_x * MAX_STRAFE_SPEED + gamepad1.right_stick_x * MAX_ROTATION_SPEED);
        double bl = RRMath.clamp(gamepad1.left_stick_y * MAX_FORWARD_BACK_SPEED - gamepad1.left_stick_x * MAX_STRAFE_SPEED - gamepad1.right_stick_x * MAX_ROTATION_SPEED);
        double br = RRMath.clamp(gamepad1.left_stick_y * MAX_FORWARD_BACK_SPEED + gamepad1.left_stick_x * MAX_STRAFE_SPEED + gamepad1.right_stick_x * MAX_ROTATION_SPEED);

		chassis.frontLeft.setPower(fl);
		chassis.frontRight.setPower(fr);
		chassis.backLeft.setPower(bl);
		chassis.backRight.setPower(br);

		telemetry.addData("FL: ", df.format(fl));
		telemetry.addData("FR: ", df.format(fr));
		telemetry.addData("BL: ", df.format(bl));
		telemetry.addData("BR: ", df.format(br));

		chassis.loop();
	}

}
