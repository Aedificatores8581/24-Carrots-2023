package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary.GAprimitive;

@Autonomous
public class AutoPrimitive extends LinearOpMode {
    DcMotorEx sl;
    DcMotorEx sr;
    Servo claw;
    DigitalChannel limitswitch;
    @Override
    public void runOpMode() throws InterruptedException {
        sl = hardwareMap.get(DcMotorEx.class, "slide1");
        sr = hardwareMap.get(DcMotorEx.class, "slide2");

        sr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        limitswitch = hardwareMap.get(DigitalChannel.class, "slidelimit");

        claw = hardwareMap.get(Servo.class, "claw");
        GAprimitive ga = new GAprimitive(hardwareMap);
        claw.setPosition(0.46);
        waitForStart();
        claw.setPosition(0.69);
        sr.setTargetPositionTolerance(200);
        sr.setTargetPosition(-300);
        sr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sr.setPower(-0.4);
        while (sr.isBusy()) {
            sl.setPower(-0.4);
            telemetry.addData("sliding", "g");
            telemetry.update();
        }
        sl.setPower(0);
        sr.setPower(0);
        sr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ga.encodeTo(3000,0);
        ga.turn(85);
        ga.encodeTo(0, -61000);
        telemetry.addData("goo goo", "ga ga");
        telemetry.update();
        sr.setTargetPositionTolerance(800);
        sr.setTargetPosition(-2800);
        sr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sr.setPower(-0.4);
        while (sr.isBusy()) {
            sl.setPower(-0.4);
            telemetry.addData("sliding", "father");
            telemetry.update();
        }
        sl.setPower(0);
        sr.setPower(0);
        sr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ga.encodeTo(450, 0);
        telemetry.addData(ga.telemetric(),"");
        telemetry.addData(ga.telemetric(), "beep boop");
        telemetry.update();
        sr.setTargetPositionTolerance(300);
        sr.setTargetPosition(-1500);
        sr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sr.setPower(0.4);
        while (sr.isBusy()) {
            sl.setPower(0.4);
            telemetry.addData("sliding", "mother");
            telemetry.update();
        }
        sl.setPower(0);
        sr.setPower(0);
        sr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("funny robot", "beep beep");
        telemetry.update();
        claw.setPosition(0.46);
//        sl.setPower(-0.4);
//        sr.setPower(-0.4);
//        while (sr.getCurrentPosition()<200) {
//            telemetry.addData("srencoder", sr.getCurrentPosition());
//            telemetry.update();
//        }
//        sl.setPower(0);
//        sr.setPower(0);
        while (opModeIsActive()){
            telemetry.addData(ga.telemetric(), "");
            telemetry.update();
        }
//        if (isStopRequested()) {
//            sl.setPower(0.4);
//            sr.setPower(0.4);
//            while (!limitswitch.getState()) {}
//            sl.setPower(0);
//            sr.setPower(0);
//        }
    }
}
