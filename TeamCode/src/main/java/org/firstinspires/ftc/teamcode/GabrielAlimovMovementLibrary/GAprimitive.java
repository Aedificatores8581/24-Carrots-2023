package org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class GAprimitive {
    static DcMotorEx fl;
    static DcMotorEx fr;
    static DcMotorEx bl;
    static DcMotorEx br;
    static DcMotorEx xOdo;
    static DcMotorEx yOdo;
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
        double X;
        double Y;
        while (Math.abs(forward) < Math.abs(yOdo.getCurrentPosition())-Yadjustment || Math.abs(strafe) < Math.abs(xOdo.getCurrentPosition())-Xadjustment) {
            if (Math.abs(forward) < Math.abs(yOdo.getCurrentPosition())-Yadjustment) {
                if (fNegative) {
                    Y = -0.5;
                } else {
                    Y = 0.5;
                }
            } else {
                Y = 0;
            }
            if (Math.abs(strafe) < Math.abs(xOdo.getCurrentPosition())-Xadjustment) {
                if (sNegative) {
                    X = -0.5;
                } else {
                    X = 0.5;
                }
            } else {
                X = 0;
            }
            fl.setPower(Y + X);
            bl.setPower(Y - X);
            fr.setPower(Y - X);
            br.setPower(Y + X);
        }
    }
}
