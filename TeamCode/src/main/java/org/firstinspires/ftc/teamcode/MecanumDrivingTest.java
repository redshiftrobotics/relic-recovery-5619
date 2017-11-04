package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utilities.Chassis;

import java.text.DecimalFormat;

@TeleOp(name = "MTD", group = "testing")
@Disabled
public class MecanumDrivingTest extends OpMode {
	double rotation = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
	double robotAngle = Math.atan2(gamepad1.left_stick_x, gamepad1.left_stick_y);
	double rightX = gamepad1.right_stick_x;

	double fl = rotation * Math.cos(robotAngle) + rightX;
	double fr = rotation * Math.sin(robotAngle) - rightX;
	double bl = rotation * Math.sin(robotAngle) + rightX;
	double br = rotation * Math.cos(robotAngle) - rightX;

	private Chassis chassis = new Chassis(this);
	private DecimalFormat df = new DecimalFormat("#.##");

	@Override
	public void init(){
		chassis.init();
	}

	@Override
	public void loop(){
		chassis.loop();

		chassis.frontLeft.setPower(fl);
		chassis.frontRight.setPower(fr);
		chassis.backLeft.setPower(bl);
		chassis.backRight.setPower(br);

		telemetry.addData("FL: ", df.format(fl));
		telemetry.addData("FR: ", df.format(fr));
		telemetry.addData("BL: ", df.format(bl));
		telemetry.addData("BR: ", df.format(br));
	}
}
