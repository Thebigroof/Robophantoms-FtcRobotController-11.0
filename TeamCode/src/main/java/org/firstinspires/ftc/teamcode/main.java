package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Spindexer;
import org.firstinspires.ftc.teamcode.mechanisms.FlywheelLaunch;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;

@TeleOp
public class main extends OpMode {

    MecanumDrive drive = new MecanumDrive();
    Spindexer spin = new Spindexer();
    FlywheelLaunch launcher = new FlywheelLaunch();
    Intake take = new Intake();

    double forward, strafe, rotate;

    @Override
    public void init() {
        drive.init(hardwareMap);
        spin.init(hardwareMap);
        launcher.init(hardwareMap);
        take.init(hardwareMap);
    }

    boolean lastA = false;
    boolean lastB = false;
    boolean lastRB = false;

    @Override
    public void loop() {
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.driveFieldRelative(forward, strafe, rotate);

        if (gamepad2.a && !lastA) {
            spin.indexIntake();
        }

        lastA = gamepad2.a;
        launcher.update();

        if (gamepad2.right_bumper) {
            launcher.shoot();
            // wait
            spin.indexShoot();
        }

        lastB = gamepad2.b;

        if (gamepad2.b) {
            take.in();
        }

        lastRB = gamepad1.right_bumper;
    }
}
