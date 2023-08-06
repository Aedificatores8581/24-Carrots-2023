package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary.GAtest;

@Disabled
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
        builder.GAtestInit(fl, fr, bl, br, imu, bl, fl);
        while (!isStopRequested()) {
            builder.update();
        }
    }
    public IMU imumethod () {
        return imu;
    }
}
class funnyClass {

}
@Disabled
class AutoBuider extends GAtest {
    AutoTest t = new AutoTest();

    public static boolean autoConstructor (int f) {
        switch (f) {
            case 0:
                return true;
        }
        return true;
    }
}