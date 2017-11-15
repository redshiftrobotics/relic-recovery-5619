//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.*;
//
///**
// * Created by zoe on 10/21/17.
// */
//
//@Autonomous(name = "Sensor: test range sensor", group = "Sensor")
//
//public class RS_Test extends LinearOpMode {
//
//    I2cDevice rangeSensor;
//
//    @Override public void runOpMode() {
//
//        // get a reference to our compass
//        rangeSensor = hardwareMap.get(I2cDevice.class, "max_sonar_1");
//        DistanceSensor DS = new DistanceSensor(rangeSensor);
//
//        telemetry.addData(">", "Initicialized");
//        telemetry.update();
//
//        // wait for the start button to be pressed
//        waitForStart();
//
//        while (opModeIsActive()) {
//            DS.startReading();
//            int distance = DS.getUnsanitizedReading(telemetry);
//        }
//    }
//
//}
