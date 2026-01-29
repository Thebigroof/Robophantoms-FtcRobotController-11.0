package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Odometry {
    // ADJUST THESE CONSTANTS
    private static final double TICKS_TO_INCHES = 0.0073033; //Calculate this by taking the circumference of your odometry wheel (diameter(inches) x pi) and dividing it by the 537.7
    private static final double PERP_OFFSET = 3.5; //Distance between middle/perpendicular odometer and center of robot in inches

    public double robotX = 0, robotY = 0, robotHeading = 0;
    private int lastPar = 0, lastPerp = 0;
    private double lastHeading = 0;

    public void update(int parPos, int perpPos, double headingRad) {
        double deltaHeading = headingRad - lastHeading;

        double dPar = (parPos - lastPar) * TICKS_TO_INCHES;
        double dPerp = (perpPos - lastPerp) * TICKS_TO_INCHES;

        // Remove "fake" sideways movement from rotation
        double localX = dPerp - (deltaHeading * PERP_OFFSET);
        double localY = dPar;

        // Rotate to field-centric coordinates
        robotX += localX * Math.cos(headingRad) - localY * Math.sin(headingRad);
        robotY += localX * Math.sin(headingRad) + localY * Math.cos(headingRad);

        robotHeading = headingRad;
        lastPar = parPos;
        lastPerp = perpPos;
        lastHeading = headingRad;
    }
}