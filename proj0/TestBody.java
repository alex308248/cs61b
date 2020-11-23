public class TestBody {
	public static void main(String[] args) {
		checkBody();
	}

    /**
     *  Checks whether or not two Strings are equal and prints the result.
     *  @param  expected    double expected
     *  @param  actual      double received
     *  @param  label       Label for the 'test' case
     */

	private static void checkEquals(double expected, double actual, String label) {
        if (Double.isNaN(actual) || Double.isInfinite(actual)) {
            System.out.println("FAIL: " + label
                    + ": froce form b1 to b2  " + expected + " froce from b2 to b1 " + actual);
        } else if (expected == actual) {
            System.out.println("PASS: " + label
                    + ": froce form b1 to b2 " + expected + " froce from b2 to b1 " + actual);
        } else {
            System.out.println("FAIL: " + label
                    + ": froce form b1 to b2  " + expected + " froce from b2 to b1 " + actual);
        }
     }

	private static void checkBody() {
        Body b1 = new Body(3.0, 4.0, 0.0, 0.0, 10.0, "b1");
        Body b2 = new Body(0.0, 0.0, 0.0, 0.0, 10.0, "b2");
		double x12 = b1.calcForceExertedByX(b2);
		double x21 = b2.calcForceExertedByX(b1);
		double y12 = b1.calcForceExertedByY(b2);
		double y21 = b2.calcForceExertedByY(b1);
		checkEquals(Math.abs(x12), Math.abs(x21), "Xequal()");
		checkEquals(Math.abs(y12), Math.abs(y21), "Yequal()");
	}
}