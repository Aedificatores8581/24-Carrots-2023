package org.firstinspires.ftc.teamcode.GabrielAlimovMovementLibrary;

class GAstate {
    public static int stateT = 0;
    public static int state () {
        return stateT;
    }
    public static void machine (boolean in) {
        if (in) {
            stateT++;
        }
    }
}
