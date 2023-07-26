package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary.GAprimitive;

@Autonomous
public class AutoPrimitive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        GAprimitive ga = new GAprimitive(hardwareMap);
        waitForStart();
        ga.encodeTo(1000, 0);
        while (!isStopRequested()){}
    }
}
