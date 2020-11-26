public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);

		/* Every time you call a read method from the In class,
		 * it reads the next thing from the file, assuming it is
		 * of the specified type. */

		/* Compare the calls below to the contents of BasicInDemo_input_file.txt */

		int numbers = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	public static Body [] readBodies(String filename) {
		In in = new In(filename);
		int numbers = in.readInt();
		double radius = in.readDouble();
		Body[] Bodys = new Body[numbers];
		for(int i = 0; i < numbers; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();   
			Body newbody = new Body(xP, yP, xV, yV, mass, img);
			Bodys[i] = newbody;
		}
		return Bodys;
	}
	public static void main(String[] args) {
		Double T = Double.parseDouble(args[0]);
		Double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] bodies = readBodies(filename);
		String background = "images/starfield.jpg";
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-2*radius, 2*radius);
		StdDraw.clear();
        StdDraw.picture(0, 0, background);
        for(int i = 0; i < bodies.length; i++) {
        	bodies[i].draw();
        }
        StdDraw.show();
        for(double time = 0; time < T; time = time + dt) {
        	double[] xForce = new double[bodies.length];
        	double[] yForce = new double[bodies.length];
        	for(int i = 0; i < bodies.length; i++) {
        		xForce[i] = bodies[i].calcNetForceExertedByX(bodies);
        		yForce[i] = bodies[i].calcNetForceExertedByX(bodies);
        	}
        	for(int i = 0; i < bodies.length; i++) {
        	    bodies[i].update(dt, xForce[i], yForce[i]);   
        	}
        	StdDraw.setScale(-2*radius, 2*radius);
    		StdDraw.clear();
            StdDraw.picture(0, 0, background);
            for(int i = 0; i < bodies.length; i++) {
        	    bodies[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
            bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
	}
} 