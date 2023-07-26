package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary.GAlocalization;

@TeleOp
public class LocalizationTest extends LinearOpMode {

    public void runOpMode() {
  //      GAlocalization local = new GAlocalization(hardwareMap);

        waitForStart();
        if(opModeIsActive()) {

            while (opModeIsActive()) {
                //local.update();
         //       telemetry.addData("X", local.coordinates()[0]);
         //       telemetry.addData("Y", local.coordinates()[1]);
                telemetry.addData("Heading", 1);
                telemetry.update();
            }
        }
    }
}
