package org.firstinspires.ftc.teamcode.drive;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleTankDrive extends LinearOpMode {
    DcMotorEx fl;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;
    DcMotorEx sl;
    DcMotorEx sr;
    Servo claw;

    @Override
    public void runOpMode () {
        fl = hardwareMap.get(DcMotorEx.class, "front left");
        fr = hardwareMap.get(DcMotorEx.class, "front right");
        bl = hardwareMap.get(DcMotorEx.class, "back left");
        br = hardwareMap.get(DcMotorEx.class, "back right");

        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);

        sl = hardwareMap.get(DcMotorEx.class, "slide1");
        sr = hardwareMap.get(DcMotorEx.class, "slide2");

        sl.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        sr.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        claw = hardwareMap.get(Servo.class, "claw");

        double clawpos = 0.5;

        while (!isStopRequested()) {
            boolean y = gamepad2.y;
            boolean x = gamepad2.x;
            boolean a = gamepad2.a;
            boolean b = gamepad2.b;
//            boolean rb = gamepad1.right_bumper;
//            boolean lb = gamepad1.left_bumper;

            float ly = gamepad1.left_stick_y;
            float ry = gamepad1.right_stick_y;
            float Ytotal =(ly + ry )/2;
            float pivot = 0;
/*
            fl.setPower(ly);
            bl.setPower(ly);
            br.setPower(ry);
            fr.setPower(ry);

 */

            float lx = gamepad1.left_stick_x;
            float rx = gamepad1.right_stick_x;
            float Xtotal = (lx + rx)/2;

/*            if(lx < -.3 && rx < -.3){
               fl.setPower(-.5);
               bl.setPower(.5);
               br.setPower(-.5);
               fr.setPower(.5);
            }

            if(lx > .3 && rx > .3){
               fl.setPower(.5);
               bl.setPower(-.5);
               br.setPower(.5);
               fr.setPower(-.5);
            }


            if (rb){
               fl.setPower(-.5);
               bl.setPower(.5);
               br.setPower(-.5);
               fr.setPower(.5);
            }

            if (lb){
               fl.setPower(.5);
               bl.setPower(-.5);
               br.setPower(.5);
               fr.setPower(-.5);
            }
*/
            fl.setPower(-pivot + Ytotal - Xtotal);
            bl.setPower(-pivot +Ytotal + Xtotal);
            fr.setPower(pivot +Ytotal + Xtotal);
            br.setPower( pivot +Ytotal - Xtotal);

            if (ly >= .80 && ry <= .80){
                pivot = -1;
            }

            if (ry >= .80 && ly <= .80){
                pivot = 1;
            }




/*            fl.setPower(ly);
            bl.setPower(ly);
            br.setPower(ry);
            fr.setPower(ry);
 */

            if (y) {
                sl.setPower(-0.7);
                sr.setPower(-0.7);
            } else if (x) {
                sl.setPower(0.7);
                sr.setPower(0.7);
            } else {
                sl.setPower(0);
                sr.setPower(0);
            }
            if (a) {
                clawpos = 0.46;
            } else if (b) {
                clawpos = 0.69;
            }

            claw.setPosition(clawpos);
        }
    }
}
