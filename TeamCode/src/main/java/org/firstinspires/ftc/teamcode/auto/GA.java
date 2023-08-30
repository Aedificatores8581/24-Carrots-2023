package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
            telemetry.addLine(move.DistData());
            telemetry.update();
        }
    }
}

class Move extends GAmovement {
    public boolean autoConstructor (int t) {
        switch (t) {
            case 0:
               ADMove(-18920, -3800, 0.5);
               break;
            default:
                break;
        }
        return true;
    }
}