package org.firstinspires.ftc.teamcode.mechanisms;

public class TrigOdometry {
    // Find angle to look at goal (degrees from North/Y+)
    public double getTargetAngle(double robX, double robY, double goalX, double goalY) {
        double targetDegrees = Math.toDegrees(Math.atan2(goalX - robX, goalY - robY));
        return Double.isNaN(targetDegrees) ? 0 : targetDegrees;
    }

    // Find shortest turn distance
    public double findTurn(double currentHeadingDeg, double targetAngleDeg) {
        double turnAmt = targetAngleDeg - currentHeadingDeg;
        while (turnAmt > 180) turnAmt -= 360;
        while (turnAmt < -180) turnAmt += 360;
        return turnAmt;
    }
}
