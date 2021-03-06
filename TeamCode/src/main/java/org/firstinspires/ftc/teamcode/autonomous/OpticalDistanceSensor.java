package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@Autonomous(name = "Sensor: MR ODS", group = "Sensor")

public class OpticalDistanceSensor extends LinearOpMode {
    com.qualcomm.robotcore.hardware.OpticalDistanceSensor odsSensor;  // Hardware Device Object

    @Override
    public void runOpMode() {

        // get a reference to our Light Sensor object.
        odsSensor = hardwareMap.get(com.qualcomm.robotcore.hardware.OpticalDistanceSensor.class, "sensor_ods");

        // wait for the start button to be pressed.
        waitForStart();

        // while the op mode is active, loop and read the light levels.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {

            // send the info back to driver station using telemetry function.
            telemetry.addData("Normal", odsSensor.getLightDetected() * 10000 > 6322);
            telemetry.addData("> ", odsSensor.getLightDetected() * 10000);
            telemetry.update();
        }
    }
}
