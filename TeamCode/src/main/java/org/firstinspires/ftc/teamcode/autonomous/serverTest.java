package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by zoe on 12/7/17.
 */
@TeleOp(name = "Servo Test")
public class serverTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Servo glyph1 = hardwareMap.servo.get("gly1");
        glyph1.setDirection(Servo.Direction.FORWARD);
        glyph1.setPosition(0.0);

        Servo glyph2 = hardwareMap.servo.get("gly2");
        glyph2.setDirection(Servo.Direction.REVERSE);
        glyph2.setPosition(0.0);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                telemetry.addData("A", "Pressed");

                glyph1.setPosition(0.5);
                glyph2.setPosition(0.5);
            } else if (gamepad1.b) {
                telemetry.addData("B", "Pressed");

                glyph1.setPosition(0.9);
                glyph2.setPosition(0.9);
            } else if (gamepad1.x) {
                telemetry.addData("X", "Pressed");

                glyph1.setPosition(1.0);
                glyph2.setPosition(1.0);
            } else if (gamepad1.y) {
                telemetry.addData("Y", "Pressed");

                glyph1.setPosition(0.0);
                glyph2.setPosition(0.0);
            }
            telemetry.update();
        }
    }
}
