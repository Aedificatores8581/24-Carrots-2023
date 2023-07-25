package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class seek {
    DistanceSensor sens;
    Servo serv;
    Servo army;
    //something something sensor
    public void init (DistanceSensor sensor, Servo turret, Servo arm) {
        sens = sensor;
        serv = turret;
        army = arm;
    }
    public DistanceSensor ginit () {
        return sens;
    }
    public double Seek (int time) {
        double lowDist[] = {10000, 10000};
        for (int i = 0; i < time; i++) {
            if (sens.getDistance(DistanceUnit.CM) < lowDist[0]) {
                lowDist[0] = sens.getDistance(DistanceUnit.CM);
                lowDist[1] = serv.getPosition();
            }
            serv.setPosition(serv.getPosition()+0.01);
        }
        serv.setPosition(lowDist[1]);
        army.setPosition(0.5);
        return lowDist[0];
    }
}
