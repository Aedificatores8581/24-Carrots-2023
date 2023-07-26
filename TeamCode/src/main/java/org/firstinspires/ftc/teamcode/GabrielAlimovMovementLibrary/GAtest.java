package org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public abstract class GAtest {
    DcMotorEx fl;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;
    IMU Imu;
    GAstate s = new GAstate();
    GAlocalization localization;
    public final void GAtestInit (DcMotorEx front_left, DcMotorEx front_right, DcMotorEx back_left, DcMotorEx back_right, IMU imu, DcMotorEx X_axis_odometer, DcMotorEx Y_axis_odometer) {
        fl = front_left;
        fr = front_right;
        bl = back_left;
        br = back_right;
        Imu = imu;
        localization = new GAlocalization(imu, X_axis_odometer, Y_axis_odometer);
    }
    public static boolean autoConstructor (final int t) {
        return true;
    }

    public final void update () {
        s.machine(autoConstructor(s.state()));
    }
    private void refs () {

    }
}
