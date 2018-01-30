package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.utilities.Chassis;
import org.redshiftrobotics.lib.descartes.UltrasonicDistanceSensor;
import org.firstinspires.ftc.teamcode.autonomous.Movement;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by zoe on 11/15/17.
 */
@TeleOp(name = "Get how long it takes to travel from point to point")
public class GetHowLongItTakesToTravelFromPointToPoint extends OpMode {
    Chassis chassis = null;
    Movement m = null;


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
        double rotationToMoveTo = 0;
        long drivingStartTime = 0;
        if (gamepad1.a){
            drivingStartTime = System.currentTimeMillis();
            telemetry.addData("starting", "timer");
            m.forward();
        } else if (gamepad1.b) {
            m.zero();
            long estimatedTime = System.currentTimeMillis() - drivingStartTime;
            telemetry.addData("driving took", estimatedTime);
        }

        telemetry.update();
        chassis.loop();
    }
}
