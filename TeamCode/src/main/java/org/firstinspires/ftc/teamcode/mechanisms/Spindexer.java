package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Spindexer {

    private Servo spin;

    private final double HOME = 0.00;
    private final double STEP = 0.25;
    private final double EXTRA = 0.10;

    private double currentPos = HOME;

    public void init(HardwareMap hardwareMap) {
        spin = hardwareMap.get(Servo.class, "spin");
        spin.setPosition(HOME);
    }

    public void indexIntake() {
        currentPos = Math.min(currentPos + STEP, 1.0);
        spin.setPosition(currentPos);
    }

    public void indexShoot() {
        if (currentPos == (HOME + (STEP * 3))) {
            currentPos = Math.max(currentPos - STEP - EXTRA, 0.0);
        }
        else {
            currentPos = Math.max(currentPos - STEP, 0.0);
        }
        spin.setPosition(currentPos);
    }
}
