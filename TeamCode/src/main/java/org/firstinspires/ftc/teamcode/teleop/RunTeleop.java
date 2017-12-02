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
@TeleOp(name = "Run Teleop (Compitition - DEC 3)")
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
        if (gamepad1.left_trigger == 0 && gamepad1.right_trigger == 0) {
            //Tank Drive
            float xValue = gamepad1.left_stick_y;
            float yValue = gamepad1.right_stick_y;

            xValue = Range.clip(xValue, -1, 1);
            yValue = Range.clip(yValue, -1, 1);

            m.stickDrive(xValue, yValue);
            //End tank Drive
        } else {
            /*
            Either left or right is being pushed, now we need to deside which one is being pushed
             */

            final float leftPower = gamepad1.left_trigger;
            final float rightPower = gamepad1.right_trigger;

            if(gamepad1.right_stick_y != 0){
                //Strafe left
                m.strafeLeft();
            } else {
                //Strafe right
                m.strafeRight();
            }
        }

        if (toggleGlyphServos()) {
            glyphBool = m.glypeMech(glyphBool);
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
}
