package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Intake {

    private DcMotor intake;

    public void init(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotor.class, "intake");
    }

    public void in() {
        intake.setPower(1.0);
    }
}

