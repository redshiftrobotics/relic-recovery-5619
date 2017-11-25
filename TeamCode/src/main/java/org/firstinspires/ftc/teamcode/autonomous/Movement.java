package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.hardware.I2cDeviceSynch;

import org.firstinspires.ftc.teamcode.utilities.Chassis;

/**
 * Created by zoe on 11/25/17.
 */

public class Movement {
    Chassis m_chassis = null;

    Movement( Chassis chassis ) {
        if (chassis == null){
            throw new Error("Chassis not inicialized (you probably forgot to run the initialize method)");
        }
        m_chassis = chassis;
    }

    public void strafeLeft (double i) {
        m_chassis.frontLeft.setPower(-i);
        m_chassis.backLeft.setPower(i);
        m_chassis.frontRight.setPower(-i);
        m_chassis.backRight.setPower(i);
    }

    public void strafeRight (double i) {
        m_chassis.frontLeft.setPower(i);
        m_chassis.backLeft.setPower(-i);
        m_chassis.frontRight.setPower(i);
        m_chassis.backRight.setPower(-i);
    }

    public void back (double i) {
        m_chassis.frontLeft.setPower(-i);
        m_chassis.backLeft.setPower(-i);
        m_chassis.frontRight.setPower(-i);
        m_chassis.backRight.setPower(-i);
    }

    public void forward (double i) {
        m_chassis.frontLeft.setPower(i);
        m_chassis.backLeft.setPower(i);
        m_chassis.frontRight.setPower(i);
        m_chassis.backRight.setPower(i);
    }

    public void strafeLeft () {
        strafeLeft(0.8);
    }

    public void strafeRight () {
        strafeRight(0.8);
    }

    public void back () {
        back(0.8);
    }

    public void forward () {
        forward(0.8);
    }

    public void zero () {
        m_chassis.frontLeft.setPower(0);
        m_chassis.backLeft.setPower(0);
        m_chassis.frontRight.setPower(0);
        m_chassis.backRight.setPower(0);
    }
}
