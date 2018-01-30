package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.redshiftrobotics.lib.descartes.UltrasonicDistanceSensor;

/**
 * Created by zoe on 11/15/17.
 */

@TeleOp(name = "Do nothing")
public class telio_test extends OpMode {
    int i = 0;

    @Override
    public void init() {
        telemetry.addData("completed", "nothing");
        telemetry.update();
    }

    @Override
    public void loop() {
        telemetry.addData("Loop", i);
        telemetry.update();
        i++;
    }
}
