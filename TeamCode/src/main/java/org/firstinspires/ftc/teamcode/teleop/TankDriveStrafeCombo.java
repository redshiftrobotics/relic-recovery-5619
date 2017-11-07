package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.utilities.Chassis;

/**
 * Created by Eric Golde on 11/7/2017.
 */

@TeleOp(name = "Tank w/ Strafe", group = "teleop")
public class TankDriveStrafeCombo extends OpMode {

    private static final double AMOUNT_TO_SLOW_DRIVING_SPEED = 1;

    private static final double GLYPH_MOTOR_SPEED_UP = 0.7;
    private static final double GLYPH_MOTOR_SPEED_DOWN = -0.7;

    //Glyph Clamps
    private static final double GLYPH_LEFT_OPEN = 0;
    private static final double GLYPH_RIGHT_OPEN = 1;

    private static final double GLYPH_LEFT_CLOSE = .50;
    private static final double GLYPH_RIGHT_CLOSE = .50;

    Chassis chassis = new Chassis(this);

    public void init() {
        chassis.init();
    }

    public void loop() {

        if (gamepad1.left_trigger == 0 && gamepad1.right_trigger == 0) {
            //Tank Drive
            float xValue = gamepad1.left_stick_y;
            float yValue = gamepad1.right_stick_y;

            xValue = Range.clip(xValue, -1, 1);
            yValue = Range.clip(yValue, -1, 1);

            chassis.frontLeft.setPower(xValue * AMOUNT_TO_SLOW_DRIVING_SPEED);
            chassis.frontRight.setPower(yValue * AMOUNT_TO_SLOW_DRIVING_SPEED);
            chassis.backLeft.setPower(xValue * AMOUNT_TO_SLOW_DRIVING_SPEED);
            chassis.backRight.setPower(yValue * AMOUNT_TO_SLOW_DRIVING_SPEED);
            //End tank Drive
        } else {
            /*
            Either left or right is being pushed, now we need to deside which one is being pushed
             */

           final float leftPower = gamepad1.left_trigger;
           final float rightPower = gamepad1.right_trigger;

            if(gamepad1.right_stick_y != 0){
                //Strafe left
                chassis.frontLeft.setPower(-leftPower);
                chassis.backLeft.setPower(leftPower);
                chassis.frontRight.setPower(leftPower);
                chassis.backRight.setPower(-leftPower);
            } else {
                //Strafe right
                chassis.frontLeft.setPower(rightPower);
                chassis.backLeft.setPower(-rightPower);
                chassis.frontRight.setPower(-rightPower);
                chassis.backRight.setPower(rightPower);
            }
        }

        //Strafe


        //End Strafe

        //Glyph Up and Down
        chassis.glyphMotor.setPower(Range.clip(gamepad2.right_stick_y, GLYPH_MOTOR_SPEED_DOWN, GLYPH_MOTOR_SPEED_UP));

        //Glyph Servos
        if (toggleGlyphServos()) {
            chassis.glyphLeft.setPosition(GLYPH_LEFT_OPEN);
            chassis.glyphRight.setPosition(GLYPH_RIGHT_OPEN);
        } else {
            chassis.glyphLeft.setPosition(GLYPH_LEFT_CLOSE);
            chassis.glyphRight.setPosition(GLYPH_RIGHT_CLOSE);
        }

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
