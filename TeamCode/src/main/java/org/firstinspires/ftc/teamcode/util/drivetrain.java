package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class drivetrain {
    DcMotorEx fr;
    DcMotorEx fl;
    DcMotorEx br;
    DcMotorEx bl;
    public void init (DcMotorEx front_left, DcMotorEx back_left, DcMotorEx front_right, DcMotorEx back_right) {
        fl = front_left;
        fr = front_right;
        br = back_right;
        bl = back_left;
    }
    public DcMotorEx[] ginit () {
        return new DcMotorEx[]{fl, fr, br, bl};
    }
    public void drive (int forward, int strafe, int turn, long duration) {
        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);
        long end = System.currentTimeMillis() + duration;
        while (System.currentTimeMillis() < end) {
            fl.setPower((-turn + forward + strafe));
            bl.setPower((-turn + (forward - strafe)));
            fr.setPower((turn + (forward - strafe)));
            br.setPower((turn + forward + strafe));
        }
    }
}
