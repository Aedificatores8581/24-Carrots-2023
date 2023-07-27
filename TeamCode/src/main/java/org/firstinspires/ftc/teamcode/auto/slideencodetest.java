package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous
public class slideencodetest extends LinearOpMode {
    DcMotorEx sl;
    DcMotorEx sr;
    @Override
    public void runOpMode() throws InterruptedException {
        sl = hardwareMap.get(DcMotorEx.class, "slide1");
        sr = hardwareMap.get(DcMotorEx.class, "slide2");

        sr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();

        while (!isStopRequested()) {
            telemetry.addData("slide right", sr.getCurrentPosition());
            telemetry.update();
        }
    }
}
