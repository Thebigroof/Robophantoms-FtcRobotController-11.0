package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Bottom", group="Linear Opmode")
public class Bottom extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    @Override
    public void runOpMode() {

        // Initialize the hardware variables.
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "front_left_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "back_left_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "back_right_drive");


        // Reverse left motors, keep right motors forward (adjust based on your bot)
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();

        // Run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {

            // --- DRIVE FORWARD SECTION ---

            // Set motor powers to 50%
            leftFrontDrive.setPower(0.5);
            leftBackDrive.setPower(0.5);
            rightFrontDrive.setPower(0.5);
            rightBackDrive.setPower(0.5);

            sleep(1300);

            leftFrontDrive.setPower(0.0);
            leftBackDrive.setPower(0.0);
            rightFrontDrive.setPower(0.0);
            rightBackDrive.setPower(0.0);

            telemetry.addData("Status", "Finished");
            telemetry.update();

        }
    }
}