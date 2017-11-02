package org.firstinspires.ftc.teamcode.teleop.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.utilities.Chassis;

/**
 * Created by Eric Golde on 10/10/2017.
 */

@TeleOp(name="Tank Drive")
@Disabled
public class TankDrive extends OpMode {

    private Chassis chassis = new Chassis(this);
    private final double POWER_MODIFIER = 0.5;

    public void init(){
        chassis.init();
    }

    public void loop(){
        float xValue = -gamepad1.left_stick_y;
        float yValue = -gamepad1.right_stick_y;
        xValue = Range.clip(xValue, -1, 1);
        yValue = Range.clip(yValue, -1, 1);
        chassis.frontLeft.setPower(xValue * POWER_MODIFIER);
        chassis.backLeft.setPower(xValue * POWER_MODIFIER);
        chassis.frontRight.setPower(yValue * POWER_MODIFIER);
        chassis.backRight.setPower(yValue * POWER_MODIFIER);
        chassis.loop();
    }

}
