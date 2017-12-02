package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.utilities.Chassis;
import org.redshiftrobotics.lib.descartes.UltrasonicDistanceSensor;
import org.firstinspires.ftc.teamcode.autonomous.Movement;

/**
 * Created by zoe on 11/15/17.
 */
@TeleOp(name = "Cordinate Test (PID)")
public class RunTeleop extends OpMode {
    Chassis chassis = null;
    Movement m = null;

    private static final double AMOUNT_TO_SLOW_DRIVING_SPEED = 1;

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
        chassis.loop();
    
    }
}
