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

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.autonomous.PixyCam;
import org.firstinspires.ftc.teamcode.utilities.Chassis;

import java.util.concurrent.locks.Lock;

/**
 * An example of a linear op mode that shows how to change the I2C address.
 */
@Autonomous(name = "BackRed", group = "Auto")
public class BlueFront extends LinearOpMode {

    VuforiaLocalizer vuforia;
    int possitionNumber;

    @Override
    public void runOpMode() throws InterruptedException {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // Set VuforiaLocalizer parameters.
        parameters.vuforiaLicenseKey = "Acq/trz/////AAAAGXUOe/SqwE6QmoOrIXJe3S4va9eMDKZHt3tVhm6SIswWHx9Je8DgPAhpIK/mvU8vKs30ybIcUOPqYu738fQl/zQe0DKmzR0SxpqcpnH2VIiteHk8vjfmCuZQGX0PWcaJ/rgat+Fm999sc4UgPuyRo86DpJZ73ZVSZ7zpvyQgH5xkrj42cgFpcfPeMuQGgrqxf4+LT8FXV0NlLbXJzCIbniMEvyHiWd/YMbAO2x83oXa6bjZBUjZlCCUN+EhuKKQlCdfwxzN0aqQEHy8U1svpOPGd7SDp5FmIZsVdt089IGrcwDPYqkgg61sFL7PgaVQAffT6WeUrZq0xXg78FH5i1VYsVkETDu/I69lOdQXKjGlY";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        // Create a new VuforiaLocalizer from parameters.
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables trackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable template = trackables.get(0);
        template.setName("relicVuMarkTemplate"); // Label for debugging, otherwise unnecessary.

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(template);

        switch (vuMark) {
            case LEFT:
                telemetry.addData("type: ", "Left");
                possitionNumber = 0;
                break;
            case RIGHT:
                telemetry.addData("type: ", "Right");
                possitionNumber = 1;
                break;
            case CENTER:
                telemetry.addData("type: ", "Center");
                possitionNumber = 2;
                break;
            default:
                telemetry.addData("type: ", "Not Sure/ Unknown");
                possitionNumber = 0;
                break;
        }

        boolean glyphBool = true;
        int i = 0;

        Chassis chassis = new Chassis(this);
        chassis.init();

        Movement m = new Movement(chassis);

        telemetry.addData(">", " initialized");
        telemetry.update();
        waitForStart();

        m.forward();
        sleep(600); //TODO: Change this

        m.strafeLeft();
        sleep(500);

        m.forward();
        sleep(300); //TODO: Change this

        m.zero();

        m.intakeRight();
        m.intakeLeft();
        sleep(500);

        m.back();
        sleep(150);

        m.forward();
        sleep(300);

        m.back();
        sleep(300);

        m.forward();
        sleep(200);

        m.back();
        sleep(100);

        m.zeroIntake();
    }
}
