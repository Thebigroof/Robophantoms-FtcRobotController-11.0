package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.mechanisms.*;

@TeleOp
public class main extends OpMode {

    MecanumDrive drive = new MecanumDrive();
    Spindexer spin = new Spindexer();
    FlywheelLaunch launcher = new FlywheelLaunch();
    Intake take = new Intake();

    Odometry odometry = new Odometry();
    TrigOdometry trig = new TrigOdometry();
    IMU imu;

    double forward, strafe, rotate;

    double GOAL_X = 0;
    double GOAL_Y = 72;

    @Override
    public void init() {
        drive.init(hardwareMap);
        spin.init(hardwareMap);
        launcher.init(hardwareMap);
        take.init(hardwareMap);

        // IMU Initialization
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));
        imu.initialize(parameters);
    }

    boolean lastA = false;
    boolean lastB = false;
    boolean lastY = false;
    boolean lastDOWN = false;
    boolean lastUP = false;

    @Override
    public void loop() {

        // 1. Update Position (Assume deadwheels share ports with motors)
        double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        // Replace drive.motor.getPosition with your actual encoder wires
        odometry.update(drive.getLeftEncoder(), drive.getRightEncoder(), heading);

        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        // If holding Left Trigger, override manual rotation to face the goal
        if (gamepad1.left_trigger > 0.5) {
            double target = trig.getTargetAngle(odometry.robotX, odometry.robotY, GOAL_X, GOAL_Y);
            double error = trig.findTurn(Math.toDegrees(odometry.robotHeading), target);
            rotate = error * 0.02; // Simple P-loop (adjust 0.02 for speed)
        }
        drive.drive(forward, strafe, rotate);

        if (gamepad2.b && !lastB) {
            spin.indexIntake();
        }
        lastB = gamepad2.b;

        launcher.update();

        if (gamepad2.y && !lastY) {
            launcher.shoot();
        }

        lastY = gamepad2.y;

        if (gamepad2.a && !gamepad2.x) {
            take.in();
        }
        else if (gamepad2.x && !gamepad2.a) {
            take.eject();
        }
        else {
            take.stop();
        }

        lastA = gamepad2.a;

        //reset spindexer position if dpad_down is pressed
        if (gamepad2.dpad_down) {
            spin.indexReset();
        }
        lastDOWN = gamepad2.dpad_down;

        //switch spindexer position to shooting mode if dpad_up is pressed
        if (gamepad2.dpad_up) {
            spin.indexMode();
        }
        lastUP = gamepad2.dpad_up;

        // Telemetry for debugging
        telemetry.addData("Robot X", odometry.robotX);
        telemetry.addData("Robot Y", odometry.robotY);
        telemetry.update();
    }
}
