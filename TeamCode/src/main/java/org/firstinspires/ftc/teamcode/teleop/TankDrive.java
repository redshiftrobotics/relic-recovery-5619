package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.utilities.Chassis;

import static android.R.attr.left;
import static android.R.attr.right;

/**
 * Created by Eric Golde on 11/4/2017.
 */

@TeleOp(name = "Tank", group="teleop" )
@Disabled
public class TankDrive extends OpMode {

    Chassis chassis = new Chassis(this);
    double amountToSlowDownTheDrivingSpeed = 1;

    private static final double GLYPH_MOTOR_SPEED_UP =0.7;
    private static final double GLYPH_MOTOR_SPEED_DOWN = -0.7;

    //Glyph Clamps
    private static final double GLYPH_LEFT_OPEN = 0;
    private static final double GLYPH_RIGHT_OPEN = 1;

    private static final double GLYPH_LEFT_CLOSE = .50;
    private static final double GLYPH_RIGHT_CLOSE = .50;

    public void init(){
        chassis.init();
    }

    public void loop(){
        float xValue = gamepad1.left_stick_y;
        float yValue = gamepad1.right_stick_y;

        xValue = Range.clip(xValue, -1, 1);
        yValue = Range.clip(yValue, -1, 1);

        chassis.frontLeft.setPower(xValue * amountToSlowDownTheDrivingSpeed);
        chassis.frontRight.setPower(yValue * amountToSlowDownTheDrivingSpeed);
        chassis.backLeft.setPower(xValue * amountToSlowDownTheDrivingSpeed);
        chassis.backRight.setPower(yValue * amountToSlowDownTheDrivingSpeed);

        chassis.glyphMotor.setPower(Range.clip(gamepad2.right_stick_y, GLYPH_MOTOR_SPEED_DOWN, GLYPH_MOTOR_SPEED_UP));

        if(toggleGlyphServos()){
            chassis.glyphLeft.setPosition(GLYPH_LEFT_OPEN);
            chassis.glyphRight.setPosition(GLYPH_RIGHT_OPEN);
        }
        else{
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

