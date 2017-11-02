package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.utilities.Chassis;

import java.text.DecimalFormat;

@Autonomous(name = "Drive ODS", group = "Sensor")
public class ODSDrive extends LinearOpMode {
    com.qualcomm.robotcore.hardware.OpticalDistanceSensor odsSensor;  // Hardware Device Object

    @Override
    public void runOpMode() {
        Chassis chassis = new Chassis(this);
        float pwr = 0.2f;

        // get a reference to our Light Sensor object.
        odsSensor = hardwareMap.get(com.qualcomm.robotcore.hardware.OpticalDistanceSensor.class, "sensor_ods");

        chassis.init();
        telemetry.addData("> ", "initialized");
        telemetry.update();

        // wait for the start button to be pressed.
        waitForStart();

        // while the op mode is active, loop and read the light levels.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {

            // send the info back to driver station using telemetry function.
            telemetry.addData("Normal", odsSensor.getLightDetected() * 1000 > 300);
            telemetry.addData("> ", odsSensor.getLightDetected() * 1000);
            telemetry.update();

            if (odsSensor.getLightDetected() * 1000 < 300){
                chassis.loop();

                chassis.frontLeft.setPower(pwr);
                chassis.frontRight.setPower(pwr);
                chassis.backLeft.setPower(pwr);
                chassis.backRight.setPower(pwr);
            }else {
                if (pwr > 0){
                    pwr = -0.2f;
                }else{
                    pwr = 0.2f;
                }
            }
        }
    }
}