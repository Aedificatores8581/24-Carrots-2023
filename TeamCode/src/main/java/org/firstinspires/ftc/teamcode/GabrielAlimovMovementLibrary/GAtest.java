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
    public void GAtestInit (DcMotorEx front_left, DcMotorEx front_right, DcMotorEx back_left, DcMotorEx back_right, IMU imu) {
        fl = front_left;
        fr = front_right;
        bl = back_left;
        br = back_right;
        Imu = imu;
    }
    public String test(int blah){
        return "this is a test method";
    };
    public abstract int test2();
    public void test3() {
        test(1).isEmpty();
    }
}
