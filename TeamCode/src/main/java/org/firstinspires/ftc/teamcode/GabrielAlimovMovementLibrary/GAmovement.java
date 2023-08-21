package org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ReadWriteFile;

import java.io.File;

public abstract class GAmovement {
    String telem = "";
    final int inch = 1892;
    HardwareMap map;
    DcMotorEx fl;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;
    IMU Imu;
   //`   GAstate s = new GAstate();
   // GAlocalization localization;
    int substate = 0;
    int substateGoal = 0;
    int ticksThisState = 0;
    int state = 0;
    int prevTickSpeed = 0;
    int speedF = 0;
    int speedS = 0;
    int startDistF = 0;
    int startDistS = 0;
    int fnegative;
    int snegative;
    int targetDistF;
    int targetDistS;
    int distanceToDecelF;
    int distanceToDecelS;
    public final void GAtestInit (DcMotorEx front_left, DcMotorEx front_right, DcMotorEx back_left, DcMotorEx back_right, IMU imu, DcMotorEx X_axis_odometer, DcMotorEx Y_axis_odometer) {
        fl = front_left;
        fr = front_right;
        bl = back_left;
        br = back_right;
        Imu = imu;
 //       localization = new GAlocalization(imu, X_axis_odometer, Y_axis_odometer);
    }
    public boolean autoConstructor (final int t) {
        return true;
    }

    public final void complete () {
        substate ++;
    }
    public final void synchronize () {
        if (ticksThisState == 0) {
            substateGoal ++;
        }
    }

    public final void update () {
        if (substate == substateGoal && ticksThisState != 0) {
            state ++;
            ticksThisState = 0;
        }
        autoConstructor(state);
        ticksThisState ++;
    }
    public final int getState () {
        return state;
    }
    public final int getTicks () {
        return ticksThisState;
    }
    public final void parabola (int forward, int strafe, double TargetSpeed) {
        synchronize();
        int yesorno = 0;
        if (ticksThisState == 0) {
            prevTickSpeed = 0;
            speedF = 0;
            speedS = 0;
            startDistF = bl.getCurrentPosition();
            startDistS = fl.getCurrentPosition();
            targetDistF = startDistF + forward;
            targetDistS = startDistS + strafe;
            if (forward < 0) {
                fnegative = -1;
            } else {
                fnegative = 1;
            }
            if (strafe < 0) {
                snegative = -1;
            } else {
                snegative = 1;
            }
        }
        if (targetDistF == bl.getCurrentPosition() && targetDistS == fl.getCurrentPosition()) {
            File log = new File("logFile.txt");
            ReadWriteFile.writeFile(log, "blah blah blah");
        }
        distanceToDecelF = speedF*10*inch;
        distanceToDecelS = speedS*10*inch;
        if (targetDistF - bl.getCurrentPosition() >= distanceToDecelF) {
            speedF -= 0.5*fnegative;
        } else if ((fnegative == -1 && bl.getCurrentPosition() > targetDistF) || (fnegative == 1 && bl.getCurrentPosition() < targetDistF) && speedF < TargetSpeed) {
            speedF += 0.05*fnegative;
        } else {
            speedF = 0;
            yesorno = 1;
        }
        if (targetDistS - fl.getCurrentPosition() >= distanceToDecelS) {
            speedS -= 0.5*snegative;
        } else if ((snegative == -1 && bl.getCurrentPosition() > targetDistS) || (snegative == 1 && bl.getCurrentPosition() < targetDistS) && speedS < TargetSpeed) {
            speedS += 0.05*snegative;
        } else {
            speedS = 0;
            if (yesorno == 1) {
                complete();
            }
        }
        fl.setPower(speedF + speedS);
        bl.setPower(speedF - speedS);
        fr.setPower(speedF - speedS);
        br.setPower(speedF + speedS);
    }
    public final void Map (HardwareMap r) {
        map = r;
        fl = r.get(DcMotorEx .class, "front left");
        fr = r.get(DcMotorEx.class, "front right");
        bl = r.get(DcMotorEx.class, "back left");
        br = r.get(DcMotorEx.class, "back right");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
