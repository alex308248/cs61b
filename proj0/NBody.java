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
		
	}
} 