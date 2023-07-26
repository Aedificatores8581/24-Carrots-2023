package org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary;

import com.arcrobotics.ftclib.geometry.Translation2d;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class GAlocalization {
    IMU imu;
    static DcMotorEx X;
    static DcMotorEx Y;
    //Orientation angle;
    YawPitchRollAngles angle;
    Translation2d translation = new Translation2d();

    int ticksPerInch = 1500;
    static float Xreal = 0;
    static float Yreal = 0;
    public GAlocalization (HardwareMap hardwareMap) {
        X = hardwareMap.get(DcMotorEx.class, "back left");
        Y = hardwareMap.get(DcMotorEx.class, "front left");
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters imuParameters = new IMU.Parameters(
            new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT
            )
        );
    }
    public double heading () {
        //angle = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        angle = imu.getRobotYawPitchRollAngles();
        return angle.getYaw(AngleUnit.DEGREES);
    }
    public static float[] coordinates() {
        resetEncoders();
        return new float[]{Xreal, Yreal};
    }
    public static void resetEncoders() {
        Xreal += Y.getCurrentPosition();//(int)Math.sin(heading());// * (Y.getCurrentPosition());// / ticksPerInch);
        Yreal += Y.getCurrentPosition();//(int)Math.cos(heading());// * (Y.getCurrentPosition());// / ticksPerInch);
        Xreal += X.getCurrentPosition();//(int)Math.sin(heading()+90);// * (X.getCurrentPosition());// / ticksPerInch);
        Yreal += X.getCurrentPosition();//(int)Math.cos(heading()+90);// * (X.getCurrentPosition());// / ticksPerInch);
        X.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Y.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        X.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Y.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void update() {
        resetEncoders();
    }
}
