package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.utilities.Chassis;
import org.firstinspires.ftc.teamcode.utilities.RRMath;

import java.text.DecimalFormat;

import static android.R.attr.x;

/**
 * Created by Eric Golde on 9/26/2017.
 */

@TeleOp(name = "TeleOP")
public class TeleOP extends OpMode {

	private Chassis chassis = new Chassis(this);
	private DecimalFormat df = new DecimalFormat("#.##");

    private static final double MAX_FORWARD_BACK_SPEED = 0.7;
    private static final double MAX_STRAFE_SPEED = 1;
    private static final double MAX_ROTATION_SPEED = 0.5;

	//Glyph Clamps
	private static final double GLYPH_LEFT_OPEN = .50;
	private static final double GLYPH_RIGHT_OPEN = .50;
	private static final double GLYPH_LEFT_CLOSE = 0;
	private static final double GLYPH_RIGHT_CLOSE = 0;

	//GLYPH Motor
	private static final double GLYPH_MOTOR_SPEED = 1;

	@Override
	public void init(){
		chassis.init();
	}

	@Override
	public void loop(){
		//Driving
        //                       - to switch forward and back                  reverse to change strafing                reverse to change rotation
		double fl = RRMath.clamp(-gamepad1.left_stick_y * MAX_FORWARD_BACK_SPEED + gamepad1.left_stick_x * MAX_STRAFE_SPEED - gamepad1.right_stick_x * MAX_ROTATION_SPEED);
        double fr = RRMath.clamp(-gamepad1.left_stick_y * MAX_FORWARD_BACK_SPEED - gamepad1.left_stick_x * MAX_STRAFE_SPEED + gamepad1.right_stick_x * MAX_ROTATION_SPEED);
        double bl = RRMath.clamp(-gamepad1.left_stick_y * MAX_FORWARD_BACK_SPEED - gamepad1.left_stick_x * MAX_STRAFE_SPEED - gamepad1.right_stick_x * MAX_ROTATION_SPEED);
        double br = RRMath.clamp(-gamepad1.left_stick_y * MAX_FORWARD_BACK_SPEED + gamepad1.left_stick_x * MAX_STRAFE_SPEED + gamepad1.right_stick_x * MAX_ROTATION_SPEED);

		chassis.frontLeft.setPower(fl);
		chassis.frontRight.setPower(fr);
		chassis.backLeft.setPower(bl);
		chassis.backRight.setPower(br);
		chassis.glyphMotor.setPower(Range.clip(gamepad2.right_stick_y, -GLYPH_MOTOR_SPEED, GLYPH_MOTOR_SPEED));

		telemetry.addData("FL: ", df.format(fl));
		telemetry.addData("FR: ", df.format(fr));
		telemetry.addData("BL: ", df.format(bl));
		telemetry.addData("BR: ", df.format(br));

		//Clamps
		if(toggleGlyphServos()){
			chassis.glyphLeft.setPosition(GLYPH_LEFT_OPEN);
			chassis.glyphLeft.setPosition(GLYPH_RIGHT_OPEN);
		}
		else{
			chassis.glyphLeft.setPosition(GLYPH_LEFT_CLOSE);
			chassis.glyphLeft.setPosition(GLYPH_RIGHT_CLOSE);
		}

		telemetry.addData("Servos: ", toggleStateGlyphServo);

		chassis.loop();
	}

	private boolean lastBttnStateGlyphServo = false;
	private boolean toggleStateGlyphServo = false;

	public boolean toggleGlyphServos() {
		if (gamepad2.a && !lastBttnStateGlyphServo) {
			toggleStateGlyphServo = !toggleStateGlyphServo;
		}
		lastBttnStateGlyphServo = gamepad2.a;
		return toggleStateGlyphServo;
	}

}
