package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary.GAmovement;

@Autonomous
public class GA extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Move move = new Move();
        move.Map(hardwareMap);
        waitForStart();
        while (!isStopRequested()) {
            move.update();
            telemetry.update();
        }
    }
//    public void Tele (String string) {
//        telemetry.addLine(string);
//    }
}

class Move extends GAmovement {
    GA l = new GA();
    DcMotorEx fl;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;
    int state = getState();
    int ticks = getTicks();
    public boolean autoConstructor (int t) {
        switch (t) {
            case 0:
               parabola(1892*20, 0, 0.3);
               break;
            default:
                telem = "nope";
                break;
        }
        return true;
    }
    public void Telemetry (String string) {
        synchronize();
        if (getTicks() == 100000) {
            complete();
        }
        telem = string;
    }
}