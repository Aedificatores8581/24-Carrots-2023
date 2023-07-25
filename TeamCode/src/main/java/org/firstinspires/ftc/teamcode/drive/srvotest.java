package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class srvotest extends LinearOpMode {
    Servo arm;

    @Override
    public void runOpMode() throws InterruptedException {
        arm = hardwareMap.get(Servo.class, "srv");

        boolean du = false, dd = false;

        waitForStart();
        if (isStopRequested()) {return;}

        while (!isStopRequested()) {
            int i = 5;
            if (i == 5) {
                du = gamepad1.dpad_up;
                dd = gamepad1.dpad_down;
                i = 0;
            }

            if (du) {
               telemetry.addData("pos (du): ", arm.getPosition());
                arm.setPosition(arm.getPosition() + .005);
            } else if (dd) {
                telemetry.addData("pos (dd): ", arm.getPosition());
                arm.setPosition(arm.getPosition() -.005);
            }
            telemetry.update();
            du = false;
            dd = false;
            i++;
        }
    }
}
