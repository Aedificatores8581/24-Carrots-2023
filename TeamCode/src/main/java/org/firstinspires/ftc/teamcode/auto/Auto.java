package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

//import org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary.GAlocalization;
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
        GAmovement.start();
       // GAlocalization.resetEncoders();
        while(!isStopRequested()) {
     //       GAlocalization.resetEncoders();
           // GAmovement.runToTicks(0,1000);
            //telemetry.addData("GA", GAmovement.getTelemetry());
            telemetry.update();
        }
    }
}
