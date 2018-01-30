package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.utilities.Chassis;
import org.redshiftrobotics.lib.descartes.UltrasonicDistanceSensor;
import org.firstinspires.ftc.teamcode.autonomous.Movement;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by zoe on 11/15/17.
 */
@TeleOp(name = "Run Teleop (Compitition - DEC 16)")
public class RunTeleop extends OpMode {
    Chassis chassis = null;
    Movement m = null;

    boolean glyphBool = true;

    @Override
    public void init() {
        chassis = new Chassis(this);
        m = new Movement(chassis);
        chassis.init();
        telemetry.addData("Finished", "init");
        telemetry.addData("Press", "Start");
        telemetry.update();
    }

    @Override
    public void loop() {
        float xValue = gamepad1.left_stick_y;
        float yValue = gamepad1.right_stick_y;
        float xValue2 = gamepad2.left_stick_y;
        float yValue2 = gamepad2.right_stick_y;

        xValue = Range.clip(xValue, -1, 1);
        yValue = Range.clip(yValue, -1, 1);

        if (isBetween(-0.5, 0.5, gamepad1.right_stick_x) && isBetween(-0.5, 0.5, gamepad1.left_stick_x)) {
            m.stickDrive(gamepad1.left_stick_y, gamepad1.right_stick_y);
        } else {
            m.strafeLeft(-gamepad1.left_stick_x);
            m.turnRight(-gamepad1.right_stick_x);
        }

        if (gamepad2.a) {
            if (gamepad2.y) {
                chassis.glyph.setPower(-0.8f);
            } else {
                chassis.glyph.setPower(0.8f);
            }
        } else if (gamepad2.x) {
            if (gamepad2.y) {
                chassis.glyph.setPower(-0.5f);
            } else {
                chassis.glyph.setPower(0.5f);
            }
        } else {
            chassis.glyph.setPower(0f);
        }

        if (gamepad2.left_bumper){
            m.intakeLeft(gamepad2.left_stick_y/3);
        } else if (gamepad2.left_trigger != 0) {
            m.intakeLeft(gamepad2.left_stick_y);
        } else {
            m.zeroIntake();
        }

        if (gamepad2.right_bumper){
            m.intakeRight(gamepad2.right_stick_y/3);
        } else if (gamepad2.right_trigger != 0) {
            m.intakeRight(gamepad2.right_stick_y);
        } else {
            m.zeroIntake();
        }

        chassis.loop();
    }

    private boolean lastBtnStateGlyphServo = false;
    private boolean toggleStateGlyphServo = false;

    public boolean toggleGlyphServos() {
        if (gamepad2.a && !lastBtnStateGlyphServo) {
            toggleStateGlyphServo = !toggleStateGlyphServo;
        }
        lastBtnStateGlyphServo = gamepad2.a;
        return toggleStateGlyphServo;
    }

    public static boolean isBetween(double a, double b, double c) {
        return b > a ? c > a && c < b : c > b && c < a;
        //checks if c is between a and b
    }
}
