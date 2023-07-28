package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary.GAprimitive;
import org.firstinspires.ftc.teamcode.util.SleeveDetection;

@Autonomous
public class AutoPrimitive extends LinearOpMode {
    DcMotorEx sl;
    DcMotorEx sr;
    Servo claw;
    DigitalChannel limitswitch;
    SleeveDetection detector = new SleeveDetection();
    @Override
    public void runOpMode() throws InterruptedException {
        SleeveDetection.ParkingPosition location = detector.getPosition();

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
        telemetry.addLine(ga.telemetric());
        telemetry.update();
        ga.turn(85);
        ga.encodeTo(0, -61000);
        telemetry.addLine(ga.telemetric());
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
        ga.encodeTo(300, 0);
        telemetry.addLine(ga.telemetric());
        telemetry.update();
//        sr.setTargetPositionTolerance(300);
//        sr.setTargetPosition(-1500);
//        sr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        sr.setPower(0.4);
//        while (sr.isBusy()) {
//            sl.setPower(0.4);
//            telemetry.addData("sliding", "mother");
//            telemetry.update();
//        }
//        sl.setPower(0);
//        sr.setPower(0);
//        sr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("funny robot", "beep beep");
        telemetry.update();
        claw.setPosition(0.46);
        ga.encodeTo(-100, 0);
        telemetry.addLine(ga.telemetric());
        telemetry.update();
        ga.encodeTo(0, 1000);
//        sl.setPower(-0.4);
//        sr.setPower(-0.4);
//        while (sr.getCurrentPosition()<200) {
//            telemetry.addData("srencoder", sr.getCurrentPosition());
//            telemetry.update();
//        }
//        sl.setPower(0);
//        sr.setPower(0);
        if (location == SleeveDetection.ParkingPosition.CENTER) {
            ga.encodeTo(0, 0);
        } else if (location == SleeveDetection.ParkingPosition.LEFT) {
            ga.encodeTo(-10000, 0);
        } else {
            ga.encodeTo(10000, 0);
        }

        while (opModeIsActive()){
            telemetry.addData(ga.telemetric(), "");
            telemetry.update();
        }

//                    Horizontal.disable();
//                    drive.followTrajectory(pink);
//        if (isStopRequested()) {
//            sl.setPower(0.4);
//            sr.setPower(0.4);
//            while (!limitswitch.getState()) {}
//            sl.setPower(0);
//            sr.setPower(0);
//        }
    }
}
