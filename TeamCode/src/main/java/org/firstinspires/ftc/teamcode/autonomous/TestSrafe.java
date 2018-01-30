package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.autonomous.Movement;
import org.firstinspires.ftc.teamcode.utilities.Chassis;

/**
 * Created by zoe on 11/25/17.
 */
@Autonomous(name = "Strafe Test", group = "Strafing")
public class TestSrafe extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Chassis chassis = new Chassis(this);

        chassis.init();
        Movement m = new Movement(chassis);

        telemetry.addData(">", " initialized");
        telemetry.update();
        waitForStart();
        chassis.loop();

        //testing turing

        m.forward();
        telemetry.addData("forward: ", "");
        telemetry.update();
        sleep(1500);

        m.strafeLeft();
        telemetry.addData("lef: ", "");
        telemetry.update();
        sleep(1500);

        m.strafeLeft();
        telemetry.addData("back: ", "");
        telemetry.update();
        sleep(1500);

        m.strafeRight();
        telemetry.addData("right: ", "");
        telemetry.update();
        sleep(1500);

        telemetry.update();

    }

}
