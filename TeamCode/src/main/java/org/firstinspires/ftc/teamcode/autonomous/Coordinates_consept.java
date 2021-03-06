package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.utilities.Chassis;
import org.redshiftrobotics.lib.descartes.UltrasonicDistanceSensor;

/**
 * Created by zoe on 11/15/17.
 */
@TeleOp(name = "Cordinate Test (DEPRICATED)")
public class Coordinates_consept extends OpMode {
    UltrasonicDistanceSensor ultraX;
    UltrasonicDistanceSensor ultraY;
    double inputX = 55;
    double inputY = 190;
    double left = 0;
    double right = 0;
    Chassis chassis = null;

    @Override
    public void init() {
        telemetry.addData("Start", "init");
        telemetry.update();
        ultraX = hardwareMap.get(UltrasonicDistanceSensor.class, "ultra0");
        ultraY = hardwareMap.get(UltrasonicDistanceSensor.class, "ultra1");
        chassis = new Chassis(this);
        chassis.init();
        telemetry.addData("Finished", "init");
        telemetry.update();
    }

    @Override
    public void loop() {
        chassis.loop();

        double x = ultraX.getDistance(DistanceUnit.CM, telemetry);
        double y = ultraY.getDistance(DistanceUnit.CM, telemetry);
        telemetry.addData("X: CM", x);
        telemetry.addData("Y: CM", y);

        //done logging
        if (x > 2 && y > 2){
            if (x > inputX){
                left = 1f;
                right = 0f;
                telemetry.addData("M: ", "should be more on left");
            }else if (x < inputX){
                left = 0f;
                right = 1f;
                telemetry.addData("M: ", "should be more on right");
            }
            if (y < inputY) {
                chassis.frontLeft.setPower(-0.2f * left + 0.2f);
                chassis.backLeft.setPower(-0.2f * left + 0.2f);
                chassis.frontRight.setPower(-0.2f * right + 0.2f);
                chassis.backRight.setPower(-0.2f * right + 0.2f);
                telemetry.addData("Move", "Forward");
            } else if (y > inputY) {
                chassis.frontLeft.setPower(0.2f * left + 0.2f);
                chassis.backLeft.setPower(0.2f * left + 0.2f);
                chassis.frontRight.setPower(0.2f * right + 0.2f);
                chassis.backRight.setPower(0.2f * right + 0.2f);
                telemetry.addData("Move", "Forward");
            }
        }else{
            chassis.frontLeft.setPower(0.0f);
            chassis.backLeft.setPower(0.0f);
            chassis.frontRight.setPower(0.0f);
            chassis.backRight.setPower(0.0f);
            telemetry.addData("M:", "All: 0");
        }
        telemetry.update();
    }
}
