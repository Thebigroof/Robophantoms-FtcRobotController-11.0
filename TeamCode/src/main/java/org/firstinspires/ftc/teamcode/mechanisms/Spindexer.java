package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Spindexer {

    private Servo spin1;
    private Servo spin2;

    private final double HOME = 0.00;
    private final double STEP = 0.29;
    private final double MORE = 0.14;

    private double currentPos = HOME;

    public void init(HardwareMap hardwareMap) {
        spin1 = hardwareMap.get(Servo.class, "spin1");
        spin2 = hardwareMap.get(Servo.class, "spin2");

        spin1.setPosition(HOME);
        spin2.setPosition(HOME);
    }

    public void indexIntake() {
        currentPos = Math.min(currentPos + STEP, 1.0);

        spin1.setPosition(currentPos);
        spin2.setPosition(currentPos);
    }

    public void indexBack() {
        currentPos = Math.min(currentPos - STEP, 1.0);

        spin1.setPosition(currentPos);
        spin2.setPosition(currentPos);
    }

    public void indexShoot() {
        currentPos = Math.min(currentPos + STEP, 1.0);
        spin1.setPosition(currentPos);
        spin2.setPosition(currentPos);
    }
    public void indexReset() {
        currentPos = HOME;
        spin1.setPosition(currentPos);
        spin2.setPosition(currentPos);
    }

    public void indexMode() {
        currentPos = Math.min(HOME + MORE, 1.0);
        spin1.setPosition(currentPos);
        spin2.setPosition(currentPos);
    }
}
