package org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.List;

public class GAmovementD {
    static String telem;
    static DcMotorEx fl;
    static DcMotorEx fr;
    static DcMotorEx bl;
    static DcMotorEx br;
    DcMotorEx xOdo;
    DcMotorEx yOdo;
    static int State = -1;
    static List<String> instructionList = new ArrayList<String>();
    static List<Integer> orderList = new ArrayList<Integer>();
    List<Integer> intInputCache = new ArrayList<Integer>();
    List<Integer> intInputCacheIDs = new ArrayList<Integer>();
    List<Integer> IDList = new ArrayList<Integer>();
    int intInputCacheTracker = 0;
    static int order = 0;
    int ID = 0;
    int IDtracker = 0;
    String intString;
    MecanumDrive drivetrain;

    //change these if you don't want to use config every time you write a new thing
    public GAmovementD (HardwareMap hardwareMap) {
     //   GAlocalization localization = new GAlocalization(hardwareMap);
        fl = hardwareMap.get(DcMotorEx.class, "front left");
        fr = hardwareMap.get(DcMotorEx.class, "front right");
        bl = hardwareMap.get(DcMotorEx.class, "back left");
        br = hardwareMap.get(DcMotorEx.class, "back right");

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
               //     boolean t = runToTicks(Integer.parseInt(instructionList.get(MethodsThisState.get(i) + 1)), Integer.parseInt(instructionList.get(MethodsThisState.get(i) + 2)));
                 //   if (t) {State ++; telem = "yaaaaaasaaaaasay";} else {telem = "noooooo";}
                    break;
            }
        }
    }
//    protected Motor[] ginit () {
//        return new Motor[]{xOdo, yOdo};
//    }
    public static void start () {
        State = 0;
    }
//    public static boolean runToTicks(int distX, int distY) {
//        if (State < 0) {
//            telem = "nope" + State;
//            addToList(1, 2);
//            instructionList.add(String.valueOf(distX));
//            instructionList.add(String.valueOf(distY));
//            return false;
//        }
//        double X;
//        double Y;
//        if (Math.round(Math.abs(GAlocalization.coordinates()[0]/100)) < Math.round((float)Math.abs(distX)/100)) {
//            if (GAlocalization.coordinates()[0] < distX) {
//                X = 0.5;
//            } else {
//                X = -0.5;
//            }
//        } else {
//            X = 0;
//        }
//       // if (Math.round(Math.abs(GAlocalization.coordinates()[1]/100)) < Math.round((float)Math.abs(distY)/100)) {
//         //   if (GAlocalization.coordinates()[1] < distY) {
//                Y = 0.5;
//            } else {
//                Y = -0.5;
//            }
//        } else {
//            Y = 0;
//        }
//        telem = "X:" + X + "y:" + Y + "0" + GAlocalization.coordinates()[0] + "1" + GAlocalization.coordinates()[1];
//        fl.setPower(Y + X);
//        bl.setPower(Y - X);
//        fr.setPower(Y - X);
//        br.setPower(Y + X);
//
//        return true;
//    }
//    public static String getTelemetry() {
//        return telem;
//    }
    private static void addToList(int instrcuctionID, int variableSpaceRequired) {
        instructionList.add(String.valueOf(instrcuctionID));
        for (int i = 0; i < variableSpaceRequired; i++) {
            orderList.add(-1);
        }
        order = order +1;
    }
}