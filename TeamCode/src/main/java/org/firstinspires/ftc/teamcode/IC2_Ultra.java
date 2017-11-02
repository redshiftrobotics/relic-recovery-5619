package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.utilities.Chassis;

import java.util.Locale;

/**
 * Created by zoe on 10/26/17.
 */

@Autonomous(name = "Ultra Test", group = "Sensor")
public class IC2_Ultra extends LinearOpMode  {

    UltrasonicI2cRangeSensor ultra;
    BNO055IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        float rot;

        Chassis chassis = new Chassis(this);
        chassis.init();

        telemetry.addData(">", " initialized");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            rot = get(imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES));

            telemetry.addData("Data: ", rot);
            telemetry.update();

            if ((isBetween(rot, -1, 1)) || (isBetween(rot, 179, 181))){
                chassis.frontLeft.setPower(-0.5f);
                chassis.backLeft.setPower(-0.5f);
                chassis.frontRight.setPower(0f);
                chassis.backRight.setPower(0f);
            } else {
                
            }
        }
    }

    float get(final Orientation angles) {
        return Float.valueOf(formatAngle(angles.angleUnit, angles.firstAngle));
    }

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }

    public static boolean isBetween(float a, float b, float c) {
        return b > a ? c > a && c < b : c > b && c < a;
    }
}
