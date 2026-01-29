package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FlywheelLaunch {

    private DcMotor shooter;
    private Servo kicker;

    // TUNABLE VALUES
    private static final double FLYWHEEL_POWER = 0.27;
    private static final double KICK_OUT = 1.0;
    private static final double KICK_IN = 0.0;

    private static final double SPINUP_TIME = 2.0;
    private static final double SHOOT_TIME = 1.5;
    private static final double KICK_TIME = 0.20;

    // STATE MACHINE
    private enum State {IDLE, SPINNING, KICKING}

    private State state = State.IDLE;
    private final ElapsedTime timer = new ElapsedTime();

    public void init(HardwareMap hardwareMap) {
        shooter = hardwareMap.get(DcMotor.class, "shooter");
        kicker = hardwareMap.get(Servo.class, "kicker");

        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        kicker.setPosition(KICK_IN);
    }

    public void shoot() {
        if (state == State.IDLE) {
            shooter.setPower(FLYWHEEL_POWER);
            timer.reset();
            state = State.SPINNING;
        }
    }

    public void update() {
        switch(state) {
            case SPINNING:
                if (timer.seconds() >= SPINUP_TIME) {
                    kicker.setPosition(KICK_OUT);
                    timer.reset();
                    state = State.KICKING;
                }
                break;

            case KICKING:
                if (timer.seconds() >= KICK_TIME) {
                    kicker.setPosition(KICK_IN);

                    while (timer.seconds() <= SHOOT_TIME) {
                        shooter.setPower(1.0);
                    }

                    shooter.setPower(0.0);


                    state = State.IDLE;
                }
                break;
            default:
                // IDLE does nothing
                break;
        }
    }

    public void setSpeed() {
        shooter.setPower(0.27);
    }

    public void setZero() {
        shooter.setPower(0.0);
    }

    public void setKickOut() {
        kicker.setPosition(KICK_OUT);
    }

    public void setKickIn() {
        kicker.setPosition(KICK_OUT);
    }
}
