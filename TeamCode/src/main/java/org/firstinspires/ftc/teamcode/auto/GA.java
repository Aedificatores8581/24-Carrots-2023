package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary.GAmovement;

@Autonomous
public class GA extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Move move = new Move();
        waitForStart();
        while (!isStopRequested()) {
            move.update();
        }
    }
    public void Tele (String string) {
        telemetry.addLine(string);
    }
}

class Move extends GAmovement {
    GA l = new GA();
    public boolean autoconstructor (int t) {
        switch (t) {
            case 0:
               Telemetry("case 0: " + getTicks());
               break;
            case 1:
                Telemetry("case 1: " + getTicks());
                Telemetry("case 1, 2: " + getTicks());
                break;
        }
        return true;
    }
    public void Telemetry (String string) {
        synchronize();
        l.Tele(string);
        if (getTicks() == 5000) {
            complete();
        }
    }
}