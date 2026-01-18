import java.util.Scanner;
import java.lang.Math;

public class trigOdometry {

  // Returns the angle for the front of the robot to turn (assuming the turret facing north is 0 degrees)
  // robX and robY are robot position (will be odometry-read), goalX and goalY are the goal position (will be pre-programmed since it doesn't move)
  static double doTrig(double robX, double robY, double goalX, double goalY) {
    
    // Putting the toa in sohcahtoa
    double opp = Math.abs(goalX - robX);
    double adj = Math.abs(goalY - robY);

    // Math.atan() returns rads, so we convert to degrees (unless rads work better for gyrometer)
    // Math.signum() is to determine left or right based off of differences in x pos
    double targetAngle = Math.atan(opp / adj) * Math.signum(goalX - robX);
    double targetDegrees = targetAngle * (180 / Math.PI);

    System.out.println(targetDegrees);

    // sanitizes output incase y pos are equal, triggers a fail-safe 'point north' angle
    if (Double.isNaN(targetDegrees)) {
      targetDegrees = 0;
    }

    return targetDegrees;

  }

  // finds the degrees for the robot to turn from the current angle robRot and target angle tarRot
  static double findTurn(double robRot, double tarRot) {

    robRot = robRot % 360;
    double turnAmt = tarRot - robRot;

    // makes the turn angle more efficient, e.g. instead of turning 270 cw it turns 90 ccw 
    if (turnAmt > 180) {

      System.out.println("Original angle: " + turnAmt);
      turnAmt = (turnAmt - 360) * -1;

    } else if (turnAmt < -180) {

      System.out.println("Original angle: " + turnAmt);
      turnAmt = (turnAmt + 360) * -1;

    }

    System.out.println("robRot: " + robRot + "\ntarRot: " + tarRot + "\nturnAmt: " + turnAmt);
    return turnAmt;

  }

  // test main
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("RobX: ");
    double robX = scanner.nextDouble();
    System.out.print("RobY: ");
    double robY = scanner.nextDouble();
    System.out.print("GoalX: ");
    double goalX = scanner.nextDouble();
    System.out.print("GoalY: ");
    double goalY = scanner.nextDouble();
    System.out.print("robRot: ");
    double robRot = scanner.nextDouble();



    double target = doTrig(robX, robY, goalX, goalY);
    System.out.println("Computed turn target: " + target + " degrees from north");

    double turning = findTurn(robRot, target);
    System.out.println("To turn: " + turning + " degrees");

    scanner.close();
  }
}