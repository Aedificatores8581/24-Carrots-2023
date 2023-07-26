package org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

class GAlocalization {
    IMU Imu;
    DcMotorEx xOdo;
    DcMotorEx yOdo;
    public GAlocalization(IMU imu, DcMotorEx X_Odometry, DcMotorEx Y_Odometry) {
        Imu = imu;
        xOdo = X_Odometry;
        yOdo = Y_Odometry;
    }
    public double yaw () {
        return Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }
    public double pitch () {
        return Imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES);
    }
    public double roll () {
        return Imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.DEGREES);
    }
    //public int
}
