package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Intake {

    private DcMotor intake;

    public void init(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotor.class, "intake");
    }

    public void in() {
        intake.setDirection(DcMotor.Direction.FORWARD);
        intake.setPower(1.0);
    }

    public void stop() {
        intake.setPower(0.0);
    }
    public void eject() {
        intake.setDirection(DcMotor.Direction.REVERSE);
        intake.setPower(1.0);
    }
}

