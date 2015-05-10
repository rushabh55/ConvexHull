package emp;

import java.io.Serializable;

/**
 * This is a simple point class that keeps track of x and y coordinates and
 * stores if it is a part of hull (only for brute force algorithm)
 * 
 * @author hitesh
 * 
 */
public class Point implements Serializable{
	int x;
	int y;
	boolean hull;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		hull = false;
	}

	public String toString() {
		return x + " " + y;
	}

}
