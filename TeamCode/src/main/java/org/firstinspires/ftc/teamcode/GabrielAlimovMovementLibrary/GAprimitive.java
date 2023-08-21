package org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.BatteryChecker;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class GAprimitive {
    static DcMotorEx fl;
    static DcMotorEx fr;
    static DcMotorEx bl;
    static DcMotorEx br;
    static DcMotorEx xOdo;
    static DcMotorEx yOdo;
    static String telem;
    static IMU imu;
    public GAprimitive(HardwareMap hardwareMap) {
        fl = hardwareMap.get(DcMotorEx.class, "front left");
        fr = hardwareMap.get(DcMotorEx.class, "front right");
        bl = hardwareMap.get(DcMotorEx.class, "back left");
        br = hardwareMap.get(DcMotorEx.class, "back right");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters imuParameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT
                )
        );
        imu.resetDeviceConfigurationForOpMode();
        imu.initialize(imuParameters);
        imu.resetYaw();

        xOdo = bl;
        yOdo = fl;
    }
    public static void encodeTo (int forward, int strafe) {
        boolean fNegative = false;
        boolean sNegative = false;
        int Xadjustment = Math.abs(xOdo.getCurrentPosition());
        int Yadjustment = Math.abs(yOdo.getCurrentPosition());
        if (forward < 0) {
            fNegative = true;
        }
        if (strafe < 0) {
            sNegative = true;
        }
        double X = 0;
        double Y = 0;
        double Ytracker = 0;
        double Xtracker = 0;
        while (Math.abs(forward) > Math.abs(yOdo.getCurrentPosition())-Yadjustment || Math.abs(strafe) > Math.abs(xOdo.getCurrentPosition())-Xadjustment) {
            if (Math.abs(forward) > Math.abs(yOdo.getCurrentPosition())-Yadjustment && forward != 0) {
                if (fNegative) {
                    Y = 1;
                } else {
                    Y = -1;
                }
            } else {
                Y = 0;
            }
            Ytracker += Y * 2;
            if (Math.abs(strafe) > Math.abs(xOdo.getCurrentPosition())-Xadjustment && strafe != 0) {
                if (sNegative) {
                    X = 1;
                } else {
                    X = -1;
                }
            } else {
                X = 0;
            }
            Xtracker += X * 2;
            fl.setPower(Y + X);
            bl.setPower(Y - X);
            fr.setPower(Y - X);
            br.setPower(Y + X);
            if (X == 0 && Y == 0) {
                fl.setPower(0);
                bl.setPower(0);
                fr.setPower(0);
                br.setPower(0);
                telem = Xtracker + "\n" + Ytracker + "\n" + "yeehaw";
                return;
            }
        }
        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        telem = Xtracker + "\n" + Ytracker + "\n" + "yeehaw";
    }
    public static String telemetric () {
        return telem;
    }
    public void turn (double endpoint) {
        double imun = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        double dir = 0;
        if (imun < endpoint) {
            dir = 0.5;
        } else if (imun > endpoint) {
            dir = -0.5;
        }
        while (Math.abs(imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)) < Math.abs(endpoint)) {
            fr.setPower(dir);
            br.setPower(dir);
            bl.setPower(-dir);
            fl.setPower(-dir);
        }
        fl.setPower(0);
        br.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
    }
}
