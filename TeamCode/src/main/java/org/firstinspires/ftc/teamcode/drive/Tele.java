package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class Tele extends LinearOpMode {
    DcMotorEx fl;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;
    DcMotorEx sl;
    DcMotorEx sr;
    Servo claw;

    @Override
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.get(DcMotorEx.class, "front left");
        fr = hardwareMap.get(DcMotorEx.class, "front right");
        bl = hardwareMap.get(DcMotorEx.class, "back left");
        br = hardwareMap.get(DcMotorEx.class, "back right");

        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);

        sl = hardwareMap.get(DcMotorEx.class, "slide1");
        sr = hardwareMap.get(DcMotorEx.class, "slide2");

        sl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        claw = hardwareMap.get(Servo.class, "claw");

        waitForStart();
        if (isStopRequested()) {return;}

        double clawpos = 0.5;

        while (!isStopRequested()) {
            float Y = 0;
            float X = 0;
            float pivot = 0;
            boolean y = gamepad1.y;
            boolean x = gamepad1.x;
            boolean a = gamepad1.a;
            boolean b = gamepad1.b;

            Y = gamepad1.left_stick_y;
            X = -gamepad1.left_stick_x;
            pivot = gamepad1.right_stick_x;
            float sensitivity = (float) 0.1;

            //sensitivity adjustments

            if (Y < 0) {
                Y = (float) (Y + sensitivity);
            }

            else if (Y > 0) {
                Y = (float) (Y - sensitivity);
            }

            if (X < 0) {
                X = (float) (X + sensitivity);
            }

            else if (X > 0) {
                X = (float) (X - sensitivity);
            }

            if (pivot < 0) {
                pivot = (float) (pivot + sensitivity);
            }

            else if (pivot > 0) {
                pivot = (float) (pivot + sensitivity);
            }

            fl.setPower(-pivot + Y + X);
            bl.setPower(-pivot + (Y - X));
            fr.setPower(pivot + (Y - X));
            br.setPower(pivot + Y + X);

            if (y) {
                sl.setPower(-0.7);
                sr.setPower(-0.7);
            } else if (x) {
                sl.setPower(0.7);
                sr.setPower(0.7);
            } else {
                sl.setPower(0);
                sr.setPower(0);
            }
            if (a) {
                clawpos = 0.46;
            } else if (b) {
                clawpos = 0.69;
            }

            claw.setPosition(clawpos);


            telemetry.addData("ClawPos", claw.getPosition());
            telemetry.update();

        }
    }
}