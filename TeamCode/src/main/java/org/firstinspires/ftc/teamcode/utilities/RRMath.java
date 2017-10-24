package org.firstinspires.ftc.teamcode.utilities;

/**
 * Created by Eric Golde on 10/5/2017.
 */

public class RRMath {

    public static double clamp(double power)
    {
        if (power < -1)  return -1;
        if (power > 1) return 1;
        return power;
    }

}
