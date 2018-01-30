package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.hardware.I2cDeviceSynch;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.utilities.Chassis;

import java.util.Locale;

import static org.firstinspires.ftc.teamcode.utilities.RRMath.isBetween;

/**
 * Created by zoe on 11/25/17.
 */

public class Movement {
    Chassis m_chassis = null;
    Orientation angles;

    Boolean glyphBool = false;

    public Movement( Chassis chassis ) {
        if (chassis == null){
            throw new Error("Chassis not inicialized (you probably forgot to run the initialize method)");
        }
        m_chassis = chassis;
    }

    public void strafeLeft (double i) {
        m_chassis.frontLeft.setPower(-i);
        m_chassis.backLeft.setPower(i);
        m_chassis.frontRight.setPower(i);
        m_chassis.backRight.setPower(-i);
    }

    public void strafeRight (double i) {
        m_chassis.frontLeft.setPower(i);
        m_chassis.backLeft.setPower(-i);
        m_chassis.frontRight.setPower(-i);
        m_chassis.backRight.setPower(i);
    }

    public void turnLeft (double i) {
        m_chassis.frontLeft.setPower(i);
        m_chassis.backLeft.setPower(i);
        m_chassis.frontRight.setPower(-i);
        m_chassis.backRight.setPower(-i);
    }

    public void turnRight (double i) {
        m_chassis.frontLeft.setPower(-i);
        m_chassis.backLeft.setPower(-i);
        m_chassis.frontRight.setPower(i);
        m_chassis.backRight.setPower(i);
    }

    public void turnTo (Telemetry telemetry, double rotationToMoveTo) {
        angles = m_chassis.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        String STRAngle = formatAngle(angles.angleUnit, angles.firstAngle);
        double INTAngle = Double.parseDouble(STRAngle);
        telemetry.addData("IMU", STRAngle);
        telemetry.update();

        if (!isBetween(INTAngle, rotationToMoveTo+2, rotationToMoveTo-2)) {
            turnRight();
        } else {
            zero();
        }
    }

    public void turnToWithBlock (Telemetry telemetry, double rotationToMoveTo) {
        angles = m_chassis.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//
//        String STRAngle = formatAngle(angles.angleUnit, angles.firstAngle);
//        double INTAngle = Double.parseDouble(STRAngle);
        telemetry.addData("IMU", angles.firstAngle);
        telemetry.update();

        while (!isBetween(angles.firstAngle, rotationToMoveTo+2, rotationToMoveTo-2)) {
            turnLeft(0.5);

            angles = m_chassis.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            telemetry.addData("IMU", angles.firstAngle);
            telemetry.update();
        }

        zero();
    }


    public void turnToWithBlockRight (Telemetry telemetry, double rotationToMoveTo) {
        angles = m_chassis.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//
//        String STRAngle = formatAngle(angles.angleUnit, angles.firstAngle);
//        double INTAngle = Double.parseDouble(STRAngle);
        telemetry.addData("IMU", angles.firstAngle);
        telemetry.update();

        while (!isBetween(angles.firstAngle, rotationToMoveTo+2, rotationToMoveTo-2)) {
            turnRight(0.5);

            angles = m_chassis.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            telemetry.addData("IMU", angles.firstAngle);
            telemetry.update();
        }

        zero();
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

    public void stickDrive (double x, double y, double AMOUNT_TO_SLOW_DRIVING_SPEED) {
        m_chassis.frontLeft.setPower(x * AMOUNT_TO_SLOW_DRIVING_SPEED);
        m_chassis.frontRight.setPower(y * AMOUNT_TO_SLOW_DRIVING_SPEED);
        m_chassis.backLeft.setPower(x * AMOUNT_TO_SLOW_DRIVING_SPEED);
        m_chassis.backRight.setPower(y * AMOUNT_TO_SLOW_DRIVING_SPEED);
    }

    public void stickDrive (double x, double y) {
        stickDrive(x, y, -1f);
    }

    public void glyphMechBool () {
        glyphBool = !glyphBool;

        if (glyphBool) {
            m_chassis.glyph1.setPosition(0.5);
            m_chassis.glyph2.setPosition(0.5);
        } else {
            m_chassis.glyph1.setPosition(0f);
            m_chassis.glyph2.setPosition(0f);
        }
    }

    public void fullGlyphMechBool () {
        glyphBool = !glyphBool;

        if (glyphBool) {
            m_chassis.glyph1.setPosition(1f);
            m_chassis.glyph2.setPosition(1f);
        } else {
            m_chassis.glyph1.setPosition(0f);
            m_chassis.glyph2.setPosition(0f);
        }
    }

    public void glyphMech (double val) {
        m_chassis.glyph1.setPosition((val + 1)/2);
        m_chassis.glyph2.setPosition((val + 1)/2);
    }

    public void intakeLeft (double amount) {
        m_chassis.intakeLeft.setPower(-amount);
    }

    public void intakeRight (double amount) {
        m_chassis.intakeRight.setPower(amount);
    }

    public void intakeLeft () {
        intakeLeft(0.3);
    }

    public void intakeRight () {
        intakeRight(0.3);
    }

    public void zeroIntake () {
        m_chassis.intakeRight.setPower(0f);
        m_chassis.intakeLeft.setPower(0f);
    }

    public void strafeLeft () {
        strafeLeft(0.8);
    }

    public void strafeRight () {
        strafeRight(0.8);
    }

    public void turnLeft () {
        turnLeft(0.8);
    }

    public void turnRight () {
        turnRight(0.8);
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

    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}
