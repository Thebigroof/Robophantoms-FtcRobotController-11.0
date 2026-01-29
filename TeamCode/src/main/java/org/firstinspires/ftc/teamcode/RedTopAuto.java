package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.mechanisms.AprilTagLimelight;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Spindexer;
import org.firstinspires.ftc.teamcode.mechanisms.FlywheelLaunch;

@Autonomous(name="RedTopAuto", group="Autonomous")
public class RedTopAuto extends LinearOpMode {

    // Initialize mechanism classes
    MecanumDrive drive = new MecanumDrive();
    Spindexer spin = new Spindexer();
    FlywheelLaunch launcher = new FlywheelLaunch();
    AprilTagLimelight limelight = new AprilTagLimelight();
    int detectedPattern;

    @Override
    public void runOpMode() {
        // Initialization (runs after hitting INIT)
        drive.init(hardwareMap);
        spin.init(hardwareMap);
        launcher.init(hardwareMap);
        limelight.init(hardwareMap);
        limelight.start();

        detectedPattern = -1;

        telemetry.addData("Status", "Initialized and Ready");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (opModeIsActive()) {

            // Drive back for a little
            drive.drive(-0.5, 0, 0);
            sleep(350);
            // Wait
            drive.drive(0, 0, 0); // Stop driving
            sleep(1000);
            //Turn to apriltag
            drive.drive(0, 0, 0.5); // Stop driving
            sleep(160);
            //Wait
            drive.drive(0, 0, 0); // Stop driving
            sleep(1000);

            //Detect limelight pattern
            detectedPattern = limelight.getDetectedTag();
            sleep(2000);

            //Wait
            drive.drive(0, 0, 0); // Stop driving
            sleep(1000);
            //Turn to shoot
            drive.drive(0, 0, -0.5); // Stop driving
            sleep(840);
            //Wait
            drive.drive(0, 0, 0); // Stop driving
            sleep(1000);

            spin.indexMode();
            sleep(1000);

            if (detectedPattern == 21) {
                telemetry.addData("Pattern","GPP");
                telemetry.update();
                spin.indexShoot();
                sleep(5000);

                launcher.setSpeed();
                sleep(2000);
                launcher.setKickOut();
                sleep(2000);
                launcher.setKickIn();
                sleep(2000);
                launcher.setZero();
                sleep(2000);

                spin.indexBack();
                launcher.setSpeed();
                sleep(2000);
                launcher.setKickOut();
                sleep(2000);
                launcher.setKickIn();
                sleep(2000);
                launcher.setZero();
                sleep(2000);

                spin.indexShoot();
                spin.indexShoot();
                launcher.setSpeed();
                sleep(2000);
                launcher.setKickOut();
                sleep(2000);
                launcher.setKickIn();
                sleep(2000);
                launcher.setZero();
                sleep(2000);
            }
            else if (detectedPattern == 22) {
                telemetry.addData("Pattern","PGP");
                telemetry.update();

                launcher.setSpeed();
                sleep(2000);
                launcher.setKickOut();
                sleep(2000);
                launcher.setKickIn();
                sleep(2000);
                launcher.setZero();
                sleep(2000);

                spin.indexShoot();
                sleep(5000);
                launcher.setSpeed();
                sleep(2000);
                launcher.setKickOut();
                sleep(2000);
                launcher.setKickIn();
                sleep(2000);
                launcher.setZero();
                sleep(2000);

                spin.indexShoot();
                sleep(5000);
                launcher.setSpeed();
                sleep(2000);
                launcher.setKickOut();
                sleep(2000);
                launcher.setKickIn();
                sleep(2000);
                launcher.setZero();
                sleep(5000);
            }
            else {
                telemetry.addData("Pattern","PPG");
                telemetry.update();

                launcher.setSpeed();
                sleep(2000);
                launcher.setKickOut();
                sleep(2000);
                launcher.setKickIn();
                sleep(2000);
                launcher.setZero();
                sleep(2000);

                spin.indexShoot();
                spin.indexShoot();

                launcher.setSpeed();
                sleep(2000);
                launcher.setKickOut();
                sleep(2000);
                launcher.setKickIn();
                sleep(2000);
                launcher.setZero();
                sleep(2000);

                spin.indexBack();
                launcher.setSpeed();
                sleep(2000);
                launcher.setKickOut();
                sleep(2000);
                launcher.setKickIn();
                sleep(2000);
                launcher.setZero();
                sleep(2000);
            }

            // 3. Drive forward for 1 second again
            drive.drive(0.5, 0, 0);
            sleep(200);
            drive.drive(0, 0, 0); // Final stop

            telemetry.addData("Status", "Autonomous Complete");
            telemetry.update();
        }
    }
}