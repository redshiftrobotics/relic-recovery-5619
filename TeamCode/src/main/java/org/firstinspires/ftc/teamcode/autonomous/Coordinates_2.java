package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
public class Coordinates_2 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        UltrasonicDistanceSensor ultraX = null;
        UltrasonicDistanceSensor ultraY = null;
        double inputX = 55;
        double inputY = 190;
        double
                left,
                right,
                distanceX = 0,
                distanceY = 0,
                powerLeft,
                powerRight = 0;
        Chassis chassis = null;
        Movement m = null;

        if (!isStarted()) {
            telemetry.addData("Start", "init");
            telemetry.update();
            ultraX = hardwareMap.get(UltrasonicDistanceSensor.class, "ultra0");
            ultraY = hardwareMap.get(UltrasonicDistanceSensor.class, "ultra1");
            chassis = new Chassis(this);
            m = new Movement(chassis);
            chassis.init();
            telemetry.addData("Finished", "init");
            telemetry.update();
        } else {
            telemetry.addData("INFO", "has started");
            telemetry.update();
        }

        waitForStart();

        while (opModeIsActive()) {
            chassis.loop();

            double x = ultraX.getDistance(DistanceUnit.CM, telemetry);
            double y = ultraY.getDistance(DistanceUnit.CM, telemetry);

            if (distanceX == 0 && distanceY == 0) {
                distanceX = x;
                distanceY = y;
            }

            telemetry.addData("X: CM", x);
            telemetry.addData("Motor speed X", x/distanceX);
            telemetry.addData("Y: CM", y);
            telemetry.addData("Motor speed Y", y/distanceY);

            //done logging
            if (x > 2 && y > 2){
                if (isBetween(inputY - 3, inputY + 3, y)){

                /* Telemematry { */
                    telemetry.addData("",
                            String.valueOf(y) +
                                    " is between " +
                                    String.valueOf(inputY-3) +
                                    " and " +
                                    String.valueOf(inputY + 3)
                    );
                /* } Telemematry */

                    if(isBetween(inputX - 3, inputX + 3, x)) {
                        m.zero();
                    }else {
                        if (inputX > x) {
                            m.strafeRight(x/distanceX);
                        } else {
                            m.strafeLeft(x/distanceX);
                        }
                    }
                } else {
                    if (inputY > y){
                        m.forward(y/distanceY);
                    } else {
                        m.back(y/distanceY);
                    }
                }
            }else{
                m.zero();
            }
            telemetry.update();
        }
    }

    public static boolean isBetween(double a, double b, double c) {
        return b > a ? c > a && c < b : c > b && c < a;
        //checks if c is between a and b
    }
}
