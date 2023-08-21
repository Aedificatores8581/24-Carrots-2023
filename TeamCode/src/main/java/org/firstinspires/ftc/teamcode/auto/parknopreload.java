package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary.GAprimitive;
import org.firstinspires.ftc.teamcode.util.SleeveDetection;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous
public class parknopreload extends LinearOpMode {
    DcMotorEx sl;
    DcMotorEx sr;
    Servo claw;
    DigitalChannel limitswitch;
    SleeveDetection detector = new SleeveDetection();
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

//        OpenCvCamera phoneCam;
//        int width = 320;
//        int height = 240;

//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
//        phoneCam.openCameraDevice();
//        phoneCam.setPipeline(detector);
//        phoneCam.startStreaming(width, height, OpenCvCameraRotation.SIDEWAYS_LEFT);

        waitForStart();

        ga.encodeTo(1892*20, 0);
/*        SleeveDetection.ParkingPosition location = detector.getPosition();

        phoneCam.stopStreaming();

        ga.encodeTo(35000, 0);
        telemetry.addLine(ga.telemetric());
        telemetry.update();

        if (location == SleeveDetection.ParkingPosition.CENTER) {
            telemetry.addData("center", location);
            telemetry.update();
        } else if (location == SleeveDetection.ParkingPosition.RIGHT) {
            ga.encodeTo(0, 39000);
            telemetry.addData("right", location);
            telemetry.addLine(ga.telemetric());
            telemetry.update();
        } else {
            ga.encodeTo(0, -39000);
            telemetry.addData("left", location);
            telemetry.addLine(ga.telemetric());
            telemetry.update();

 */
        //}

//        while (!isStopRequested()) {}
    }
}
