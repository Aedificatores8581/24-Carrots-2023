package org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.A;

public class GAmovement {
    String telem;
    DcMotorEx fl;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;
    DcMotorEx xOdo;
    DcMotorEx yOdo;
    int State = -1;
    List<String> instructionList = new ArrayList<String>();
    List<Integer> orderList = new ArrayList<Integer>();
    List<Integer> intInputCache = new ArrayList<Integer>();
    List<Integer> intInputCacheIDs = new ArrayList<Integer>();
    List<Integer> IDList = new ArrayList<Integer>();
    int intInputCacheTracker = 0;
    int order = 0;
    int ID = 0;
    int IDtracker = 0;
    String intString;

    //change these if you don't want to use config every time you write a new thing
    public GAmovement (HardwareMap hardwareMap) {
        fl = hardwareMap.get(DcMotorEx.class, "front left");
        fr = hardwareMap.get(DcMotorEx.class, "front right");
        bl = hardwareMap.get(DcMotorEx.class, "back left");
        br = hardwareMap.get(DcMotorEx.class, "back right");

        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);
        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        bl.setDirection(DcMotorSimple.Direction.FORWARD);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        xOdo = bl;
        yOdo = fl;
    }
//    public void config (DcMotorEx Front_Left_Motor, DcMotorEx Front_Right_Motor, DcMotorEx Back_Left_Motor, DcMotorEx Back_Right_Motor, DcMotorEx XAxisOdometry, DcMotorEx YAxisOdometry) {
//        fl = Front_Left_Motor;
//        fr = Front_Right_Motor;
//        bl = Back_Left_Motor;
//        br = Back_Right_Motor;
//        xOdo = XAxisOdometry;
//        yOdo = YAxisOdometry;
//
//        fr.setDirection(DcMotor.Direction.REVERSE);
//        br.setDirection(DcMotor.Direction.REVERSE);
//        fl.setDirection(DcMotorSimple.Direction.FORWARD);
//        bl.setDirection(DcMotorSimple.Direction.FORWARD);
//        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//    }
    public void update () {
        List<Integer> MethodsThisState = new ArrayList<Integer>();
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i) == State) {
                MethodsThisState.add(i);
            }
        }
        for (int i = 0; i < MethodsThisState.size(); i++) {
            int currentMethod = Integer.parseInt(instructionList.get(MethodsThisState.get(i)));
            switch(currentMethod) {
                case 1:
                    runToTicks(Integer.parseInt(instructionList.get(MethodsThisState.get(i) + 1)), Integer.parseInt(instructionList.get(MethodsThisState.get(i) + 2)));
            }
        }
    }
    public void intString (int Integer) {
        if (State < 0) {
            addToList(0, 1);
            instructionList.add(String.valueOf(Integer));
        }
        intString = intString + Integer;
        telem = intString;
    }
    public boolean runToTicks (int distX, int distY) {
        if (State < 0) {
            telem = "nope" + State;
            addToList(1, 2);
            instructionList.add(String.valueOf(distX));
            instructionList.add(String.valueOf(distY));
            return false;
        }
        int xPos = xOdo.getCurrentPosition();
        int yPos = yOdo.getCurrentPosition();
        boolean xAbs = Math.abs(xPos) < Math.abs(distX);
        boolean yAbs = Math.abs(yPos) < Math.abs(distY);
        telem = "Y: " + yOdo.getCurrentPosition() + "\nX: " + xOdo.getCurrentPosition() + "\nState: " + State;

        xPos = xOdo.getCurrentPosition();
        yPos = yOdo.getCurrentPosition();
        int Y = 0;
        int X = 0;
        if (xAbs) {
            if (distX - xPos < 0) {
                X = -1;
            } else if (distX - xPos > 0) {
                X = 1;
            }
        }
        if (yAbs) {
            if (distY - yPos < 0) {
                Y = -1;
            } else if (distY - yPos > 0) {
                Y = 1;
            }
        }
        fl.setPower(Y + X);
        bl.setPower(Y - X);
        fr.setPower(Y - X);
        br.setPower(Y + X);
        if (!yAbs || !xAbs) {
            telem = "out";
            return true;
        }

        State ++;
        telem = "next";
        return true;
    }
    public String getTelemetry () {
        return telem;
    }
    private void addToList(int instrcuctionID, int variableSpaceRequired) {
        instructionList.add(String.valueOf(instrcuctionID));
        for (int i = 0; i < variableSpaceRequired; i++) {
            orderList.add(-1);
        }
        order = order +1;
    }
}