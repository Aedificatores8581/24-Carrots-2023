package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@TeleOp
public class Tele2 extends LinearOpMode {
    DcMotorEx fl;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;
    DcMotorEx sl;
    DcMotorEx sr;
    //Servo arm;

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

        //arm = hardwareMap.get(Servo.class, "srv");

        waitForStart();
        if (isStopRequested()) {return;}

        while (!isStopRequested()) {
            float Y = 0;
            float X = 0;
            float pivot = 0;
            boolean y = gamepad2.y;
            boolean x = gamepad2.x;
            boolean a = gamepad2.a;
            boolean b = gamepad2.b;
            int mode = 1;
            //boolean du = gamepad1.dpad_up;
            //boolean dd = gamepad1.dpad_down;

            if (gamepad1.right_bumper) {
                mode = 5;
            }

            Y = gamepad1.left_stick_y;
            X = -gamepad1.left_stick_x;
            pivot = gamepad1.right_stick_x;
            float sensitivity = (float) 0.1;

            //sensitivity adjustments
            if (Y < 0) {Y = (float) (Y + sensitivity);} else if (Y > 0) {Y = (float) (Y - sensitivity);}
            if (X < 0) {X = (float) (X + sensitivity);} else if (X > 0) {X = (float) (X - sensitivity);}
            if (pivot < 0) {pivot = (float) (pivot + sensitivity);} else if (pivot > 0) {pivot = (float) (pivot + sensitivity);}

            fl.setPower((-pivot + Y + X)/mode);
            bl.setPower((-pivot + (Y - X))/mode);
            fr.setPower((pivot + (Y - X))/mode);
            br.setPower((pivot + Y + X)/mode);

            if (y) {
                sl.setPower(-0.7);
                sr.setPower(-0.7);
                telemetry.addData("y", null);
            } else if (x) {
                sl.setPower(0.7);
                sr.setPower(0.7);
                telemetry.addData("x", null);
            } else {
                sl.setPower(0);
                sr.setPower(0);
            }
            
            if (b) {
                sl.setPower(-0.2);
                sr.setPower(-0.2);
                telemetry.addData("b", null);
            } else if (a) {
                sl.setPower(0.2);
                sr.setPower(0.2);
                telemetry.addData("a", null);
            } else {
                sl.setPower(0);
                sr.setPower(0);
            }
            //if (du) {
            //    arm.setPosition(arm.getPosition() + .01);
            //} else if (dd) {
            //    arm.setPosition(arm.getPosition() -.01);
            //}
            telemetry.update();
        }
    }
}