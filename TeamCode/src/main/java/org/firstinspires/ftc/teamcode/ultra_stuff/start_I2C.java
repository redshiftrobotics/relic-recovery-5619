package org.firstinspires.ftc.teamcode.ultra_stuff;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;


@Autonomous(name = "distance boi", group = "sensor")
public class start_I2C extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        I2cDeviceSynch device = hardwareMap.get(I2cDeviceSynch.class, "ultra");
        UltrasonicDistanceSensor sonar = new UltrasonicDistanceSensor(device);

        sonar.startReading();

        telemetry.addData("Inicilised", "feel free to press start");
        telemetry.update();

        waitForStart();
        while(opModeIsActive()) {
            telemetry.addData(">", sonar.getNextReading());
            telemetry.update();
        }
    }

}
