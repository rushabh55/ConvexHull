package emp;

/**
 * This is a class representing a line made of 2 points
 * 
 * @author hitesh vyas
 * 
 */
public class Line implements Comparable<Line> {

	Point p1, p2;
	// Line: y=mx+c;
	double slope, constant;
	// polar angle
	Double angle;
	// straight line distance between 2 points
	Integer distance;

	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
		calculateCoef();
	}

	/**
	 * This method calculates all the instance variable of this class using the
	 * value of both points
	 */
	private void calculateCoef() {
		slope = (p2.y - p1.y) / ((double) (p2.x - p1.x));
		if (!Double.isInfinite(slope)) {
			constant = p2.y - slope * p2.x;
		} else {
			constant = p1.y;
		}

		int dy = p2.y - p1.y;
		int dx = p2.x - p1.x;

		angle = Math.atan2((double) dy, (double) dx);
		distance = dy * dy + dx * dx;
	}

	/**
	 * This method return either positive or negative depending on where point
	 * lies with respect to this line
	 * 
	 * @param point
	 * @return direction
	 */
	public double directionOfPoint(Point p) {

		if (Double.isInfinite(slope)) {
			return (p.y - p1.y);
		} else {
			return (p.y - slope * p.x - constant);
		}

	}

	public String toString() {
		return "y=" + slope + "x +" + " " + constant + " -> " + p2;
	}

	/**
	 * This method sorts the lines on the polar angles, if polar angles are
	 * equal it sorts using the straight line distances
	 */
	@Override
	public int compareTo(Line o) {
		return this.angle.compareTo(o.angle) == 0 ? this.distance
				.compareTo(o.distance) : this.angle.compareTo(o.angle);
	}

}
