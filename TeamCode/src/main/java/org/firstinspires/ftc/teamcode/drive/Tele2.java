package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@TeleOp
public class Tele2 extends LinearOpMode {
    DcMotorEx fl;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;
    DcMotorEx sl;
    DcMotorEx sr;
    //Servo arm;
    Servo claw;
    IMU imu;

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

        //arm = hardwareMap.get(Servo.class, "srv");

        waitForStart();
        if (isStopRequested()) {return;}
        double clawpos = 0.46;

        while (!isStopRequested()) {
            double Y = 0;
            double X = 0;
            double pivot = 0;
            boolean y = gamepad2.y;
            boolean x = gamepad2.x;
            boolean a = gamepad2.a;
            boolean b = gamepad2.b;
            double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            double x_local;
            double y_local;
            int mode = 1;
            //boolean du = gamepad1.dpad_up;
            //boolean dd = gamepad1.dpad_down;

//            if (gamepad1.right_bumper) {
//                mode = 5;
//            }

            Y = gamepad1.left_stick_y;
            X = -gamepad1.left_stick_x;
            pivot = gamepad1.right_stick_x;
            float sensitivity = (float) 0.1;

            //sensitivity adjustments
            if (Y < 0) {Y = (float) (Y + sensitivity);} else if (Y > 0) {Y = (float) (Y - sensitivity);}
            if (X < 0) {X = (float) (X + sensitivity);} else if (X > 0) {X = (float) (X - sensitivity);}
            if (pivot < 0) {pivot = (float) (pivot + sensitivity);} else if (pivot > 0) {pivot = (float) (pivot + sensitivity);}

            //Raw Field Centric Values
            x_local = X * Math.cos(-heading) - Y * Math.sin(-heading);
            y_local = X * Math.sin(-heading) + Y * Math.cos(-heading);

            fl.setPower(-pivot + y_local + x_local);
            bl.setPower(-pivot + (y_local - x_local));
            fr.setPower(pivot + (y_local - x_local));
            br.setPower(pivot + y_local + x_local);

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

            if (a) {
                clawpos = 0.46;
            } else if (b) {
                clawpos = 0.69;
            }
            claw.setPosition(clawpos);
            telemetry.addData("heading", heading);
            //if (du) {
            //    arm.setPosition(arm.getPosition() + .01);
            //} else if (dd) {
            //    arm.setPosition(arm.getPosition() -.01);
            //}
            telemetry.update();
        }
    }
}