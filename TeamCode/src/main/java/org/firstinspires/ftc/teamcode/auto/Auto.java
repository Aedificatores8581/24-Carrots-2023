package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary.GAmovement;
import org.firstinspires.ftc.teamcode.util.drivetrain;
import org.firstinspires.ftc.teamcode.util.seek;

@Autonomous
public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() {
        drivetrain dt = new drivetrain();
        seek seek = new seek();
        waitForStart();
        GAmovement move = new GAmovement(hardwareMap);
        move.intString(1);
        move.intString(5);
        while (!isStopRequested()) {
            move.update();
            telemetry.addData("GAtelemetry", move.getTelemetry());
            telemetry.update();
        }
    }
}
