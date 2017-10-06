package org.firstinspires.ftc.teamcode.teleop.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utilities.Chassis;

/**
 * Created by Eric Golde on 10/5/2017.
 */

@TeleOp(name="AllForward", group = "testing")
public class AllPowerOne extends OpMode {

    Chassis chassis = new Chassis(this);

    public void init(){
        chassis.init();
    }

    public void loop(){
        chassis.loop();
        chassis.frontLeft.setPower(1);
        chassis.frontRight.setPower(1);
        chassis.backLeft.setPower(1);
        chassis.backRight.setPower(1);
    }

}
