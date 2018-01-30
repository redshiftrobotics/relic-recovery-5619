/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsUsbDeviceInterfaceModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.util.RobotLog;
import com.qualcomm.robotcore.util.TypeConversion;
import org.firstinspires.ftc.teamcode.autonomous.PixyCam;
import org.firstinspires.ftc.teamcode.utilities.Chassis;

import java.util.concurrent.locks.Lock;

/**
 * An example of a linear op mode that shows how to change the I2C address.
 */
@Autonomous(name = "Pixy BOI", group = "Pixy")
public class I2C_Init extends LinearOpMode {

    PixyCam myPixyCam = new PixyCam();

    @Override
    public void runOpMode() throws InterruptedException {
        Chassis chassis = new Chassis(this);

        myPixyCam.initialize(hardwareMap.get(com.qualcomm.robotcore.hardware.I2cDeviceSynch.class, "pixy_sensor"));

        chassis.init();

        telemetry.addData(">", " initialized");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            chassis.loop();

            if (myPixyCam.GetXCenter() > 0){
                if (myPixyCam.GetXCenter() > 160){
                    chassis.frontLeft.setPower(-0.5f);
                    chassis.backLeft.setPower(-0.5f);
                    chassis.frontRight.setPower(0f);
                    chassis.backRight.setPower(0f);
                    telemetry.addData("M: ", "should be more on left");
                }else{
                    chassis.frontLeft.setPower(0f);
                    chassis.backLeft.setPower(0f);
                    chassis.frontRight.setPower(-0.5f);
                    chassis.backRight.setPower(-0.5f);
                    telemetry.addData("M: ", "should be more on right");
                }
            }else{
                chassis.frontLeft.setPower(0.0f);
                chassis.backLeft.setPower(0.0f);
                chassis.frontRight.setPower(0.0f);
                chassis.backRight.setPower(0.0f);
                telemetry.addData("M:", "All: 0");
            }

            myPixyCam.Update();
            telemetry.addData("Pos: ", "X: " + myPixyCam.GetXCenter() + " Y:" + myPixyCam.GetYCenter());
            telemetry.update();
        }
    }
}
