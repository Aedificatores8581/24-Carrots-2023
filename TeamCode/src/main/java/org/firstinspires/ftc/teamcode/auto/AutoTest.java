package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary.GAtest;

public class AutoTest extends LinearOpMode {
    static DcMotorEx fl;
    static DcMotorEx fr;
    static DcMotorEx bl;
    static DcMotorEx br;
    public static IMU imu;
    @Override
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.get(DcMotorEx.class, "front left");
        fr = hardwareMap.get(DcMotorEx.class, "front right");
        bl = hardwareMap.get(DcMotorEx.class, "back left");
        br = hardwareMap.get(DcMotorEx.class, "back right");

        imu = hardwareMap.get(IMU.class, "imu");

        AutoBuider builder = new AutoBuider();
        builder.GAtestInit(fl, fr, bl, br, imu);
        builder.test3();
    }
    public IMU imumethod () {
        return imu;
    }
}
class funnyClass {

}
class AutoBuider extends GAtest {
    AutoTest t = new AutoTest();
    public String test(int meow) {
        switch ((int) t.imumethod().getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)) {
            case 1:
                break;
        }
        return "";
    }

    @Override
    public int instructions() {
        return 0;
    }
    public int instructions(int state) {
        switch (state) {
            case 0:
                break;
            case 1:
                break;
            //etc
        }
        return 0;
    }
}