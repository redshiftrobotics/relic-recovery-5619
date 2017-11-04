package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.utilities.Chassis;

/**
 * Created by zoe on 10/14/17.
 */

@Autonomous(name = "Full Test: FL, RL, FR, BR", group = "Pixy")
@Disabled
public class turning_test extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Chassis chassis = new Chassis(this);

        chassis.init();

        telemetry.addData(">", " initialized");
        telemetry.update();
        waitForStart();
        chassis.loop();

        //testing turing

        chassis.frontLeft.setPower(-0.8f);
        chassis.backLeft.setPower(-0.8f);
        chassis.frontRight.setPower(0.8f);
        chassis.backRight.setPower(0.8f);
        telemetry.addData("M: ", "Turning Left");
        telemetry.update();
        sleep(5000);

        chassis.frontLeft.setPower(0.8f);
        chassis.backLeft.setPower(0.8f);
        chassis.frontRight.setPower(-0.8f);
        chassis.backRight.setPower(-0.8f);
        telemetry.addData("M: ", "Turning Right");
        telemetry.update();
        sleep(5000);

        //testing individual
        chassis.frontLeft.setPower(0f);
        chassis.backLeft.setPower(0f);
        chassis.frontRight.setPower(0f);
        chassis.backRight.setPower(0f);

        chassis.frontLeft.setPower(0.8f);
        telemetry.addData("Spinning: ", "Front Left");
        telemetry.update();
        sleep(3000);

        chassis.frontLeft.setPower(0f);
        chassis.backLeft.setPower(0f);
        chassis.frontRight.setPower(0f);
        chassis.backRight.setPower(0f);

        chassis.backLeft.setPower(0.8f);
        telemetry.addData("Spinning: ", "Back Left");
        telemetry.update();
        sleep(3000);

        chassis.frontLeft.setPower(0f);
        chassis.backLeft.setPower(0f);
        chassis.frontRight.setPower(0f);
        chassis.backRight.setPower(0f);

        chassis.frontRight.setPower(0.8f);
        telemetry.addData("Spinning: ", "Front Right");
        telemetry.update();
        sleep(3000);

        chassis.frontLeft.setPower(0f);
        chassis.backLeft.setPower(0f);
        chassis.frontRight.setPower(0f);
        chassis.backRight.setPower(0f);

        chassis.backRight.setPower(0.8f);
        telemetry.addData("Spinning: ", "Back Right");
        telemetry.update();
        sleep(3000);

        //testing forward and backwards

        chassis.frontLeft.setPower(0.1f);
        chassis.backLeft.setPower(0.1f);
        chassis.frontRight.setPower(0.1f);
        chassis.backRight.setPower(0.1f);
        telemetry.addData("Forward: ", "All: 0.1");
        telemetry.update();
        sleep(3000);

        chassis.frontLeft.setPower(-0.1f);
        chassis.backLeft.setPower(-0.1f);
        chassis.frontRight.setPower(-0.1f);
        chassis.backRight.setPower(-0.1f);
        telemetry.addData("Backwards: ", "All: 0.1");
        telemetry.update();
        sleep(3000);

        chassis.frontLeft.setPower(0.2f);
        chassis.backLeft.setPower(0.2f);
        chassis.frontRight.setPower(0.2f);
        chassis.backRight.setPower(0.2f);
        telemetry.addData("Forward: ", "All: 0.2");
        telemetry.update();
        sleep(3000);

        chassis.frontLeft.setPower(-0.2f);
        chassis.backLeft.setPower(-0.2f);
        chassis.frontRight.setPower(-0.2f);
        chassis.backRight.setPower(-0.2f);
        telemetry.addData("Backwards: ", "All: 0.2");
        telemetry.update();
        sleep(3000);

        chassis.frontLeft.setPower(0.5f);
        chassis.backLeft.setPower(0.5f);
        chassis.frontRight.setPower(0.5f);
        chassis.backRight.setPower(0.5f);
        telemetry.addData("Forward: ", "All: 0.5");
        telemetry.update();
        sleep(3000);

        chassis.frontLeft.setPower(-0.5f);
        chassis.backLeft.setPower(-0.5f);
        chassis.frontRight.setPower(-0.5f);
        chassis.backRight.setPower(-0.5f);
        telemetry.addData("Backwards: ", "All: 0.5");
        telemetry.update();
        sleep(3000);

        chassis.frontLeft.setPower(0.8f);
        chassis.backLeft.setPower(0.8f);
        chassis.frontRight.setPower(0.8f);
        chassis.backRight.setPower(0.8f);
        telemetry.addData("Forward: ", "All: 0.8");
        telemetry.update();
        sleep(3000);

        chassis.frontLeft.setPower(-0.8f);
        chassis.backLeft.setPower(-0.8f);
        chassis.frontRight.setPower(-0.8f);
        chassis.backRight.setPower(-0.8f);
        telemetry.addData("Backwards: ", "All: 0.8");
        telemetry.update();
        sleep(3000);

        chassis.frontLeft.setPower(1.0f);
        chassis.backLeft.setPower(1.0f);
        chassis.frontRight.setPower(1.0f);
        chassis.backRight.setPower(1.0f);
        telemetry.addData("Forward: ", "All: 1.0");
        telemetry.update();
        sleep(3000);

        chassis.frontLeft.setPower(-1.0f);
        chassis.backLeft.setPower(-1.0f);
        chassis.frontRight.setPower(-1.0f);
        chassis.backRight.setPower(-1.0f);
        telemetry.addData("Backwards: ", "All: 1.0");
        telemetry.update();
        sleep(3000);

        telemetry.update();

    }
}
