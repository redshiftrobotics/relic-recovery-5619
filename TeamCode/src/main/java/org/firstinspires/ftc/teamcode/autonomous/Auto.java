package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.utilities.Chassis;

/**
 * Created by zoe on 11/3/17.
 */

public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Chassis chassis = new Chassis(this);

        chassis.init();

        telemetry.addData(">", " initialized");
        telemetry.update();
        waitForStart();
        chassis.loop();

        //testing turing

        chassis.frontLeft.setPower(0.8f);
        chassis.backLeft.setPower(0.8f);
        chassis.frontRight.setPower(0.8f);
        chassis.backRight.setPower(0.8f);
        telemetry.addData("M: ", "Turning Left");
        telemetry.update();
        sleep(5000);
    }
}
