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
@TeleOp(name = "IMU Test")
public class IMU extends OpMode {
    Chassis chassis = null;
    Movement m = null;
    double rotationToMoveTo = 0;

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
        if (gamepad1.a){
            rotationToMoveTo = 90;
            telemetry.addData("a: 90", "pressed");
        } else if (gamepad1.b) {
            rotationToMoveTo = 160;
            telemetry.addData("b: 180", "pressed");
        } else if (gamepad1.x) {
            rotationToMoveTo = 0;
            telemetry.addData("x: 0", "pressed");
        } else if (gamepad1.y) {
            rotationToMoveTo = -90;
            telemetry.addData("y: -90", "pressed");
        } else {
            telemetry.addData("nothing", "pressed");
        }

        m.turnTo(telemetry, rotationToMoveTo);

        telemetry.update();
        chassis.loop();
    }
}
