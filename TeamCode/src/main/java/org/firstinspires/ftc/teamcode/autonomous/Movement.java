package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.hardware.I2cDeviceSynch;

import org.firstinspires.ftc.teamcode.utilities.Chassis;

/**
 * Created by zoe on 11/25/17.
 */

public class Movement {
    Chassis m_chassis = null;

    Movement() {}
    Movement( Chassis chassis ) { initialize( chassis); }

    public void initialize( Chassis chassis )
    {
        m_chassis = chassis;
    }

    public void left (double i) {
        if (m_chassis == null) {
            throw new Error("Chassis not inicialized (you probably forgot to run the initialize method)");
        }else {
            m_chassis.frontLeft.setPower(-i);
            m_chassis.backLeft.setPower(i);
            m_chassis.frontRight.setPower(-i);
            m_chassis.backRight.setPower(i);
        }
    }

    public void right (double i) {
        if (m_chassis == null) {
            throw new Error("Chassis not inicialized (you probably forgot to run the initialize method)");
        }else {
            m_chassis.frontLeft.setPower(i);
            m_chassis.backLeft.setPower(-i);
            m_chassis.frontRight.setPower(i);
            m_chassis.backRight.setPower(-i);
        }
    }

    public void back (double i) {
        if (m_chassis == null) {
            throw new Error("Chassis not inicialized (you probably forgot to run the initialize method)");
        }else {
            m_chassis.frontLeft.setPower(-i);
            m_chassis.backLeft.setPower(-i);
            m_chassis.frontRight.setPower(-i);
            m_chassis.backRight.setPower(-i);
        }
    }

    public void forward (double i) {
        if (m_chassis == null) {
            throw new Error("Chassis not inicialized (you probably forgot to run the initialize method)");
        }else {
            m_chassis.frontLeft.setPower(i);
            m_chassis.backLeft.setPower(i);
            m_chassis.frontRight.setPower(i);
            m_chassis.backRight.setPower(i);
        }
    }

    public void left () {
        left(0.8);
    }

    public void right () {
        right(0.8);
    }

    public void back () {
        back(0.8);
    }

    public void forward () {
        forward(0.8);
    }
}
