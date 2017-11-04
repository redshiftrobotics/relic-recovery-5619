package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.utilities.Chassis;

/**
 * Created by Eric Golde on 11/4/2017.
 */
@Autonomous(name="DriveForward", group = "auto")
public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Chassis chassis = new Chassis(this);

        chassis.init();

        waitForStart();
        chassis.loop();

        //testing turing

        chassis.frontLeft.setPower(-0.8f);
        chassis.backLeft.setPower(-0.8f);
        chassis.frontRight.setPower(-0.8f);
        chassis.backRight.setPower(-0.8f);
        sleep(2000);
        chassis.frontLeft.setPower(0.8f);
        chassis.backLeft.setPower(0.8f);
        chassis.frontRight.setPower(0.8f);
        chassis.backRight.setPower(0.8f);
        sleep(500);
    }
}
