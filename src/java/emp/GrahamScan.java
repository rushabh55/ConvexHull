package emp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

import Jama.Matrix;

/**
 * This is graham scan algorithm implementation for finding convex hull
 * 
 * @author Hitesh Vyas
 * 
 */
public class GrahamScan {

	/**
	 * This method checks if the orientation of 3 points is left or right
	 * 
	 * @param p
	 * @param q
	 * @param r
	 * @return true if left or co-linear else false
	 */
	private boolean isLeftTurn(Point p, Point q, Point r) {
		double[][] orien = { { 1, p.x, p.y }, { 1, q.x, q.y }, { 1, r.x, r.y } };
		Matrix m = new Matrix(orien);
		double a = m.det();
		return a >= 0 ? true : false;
	}

	/**
	 * This method performs graham scan algorithm and returns all the points in
	 * form of stack
	 * 
	 * @param points
	 * @return
	 */
	public Stack<Point> algorithm(Point[] points) {

		points = ConvexHullUtils.sort(points);
		Point last = null;
		Stack<Point> stack = new Stack<Point>();
		stack.push(points[0]);
		stack.push(points[1]);
		last = points[2];

		for (int i = 3; i < points.length; i++) {
			while (!isLeftTurn(stack.peek(), last, points[i])) {
				last = stack.pop();
			}
			stack.push(last);
			last = points[i];
		}

		stack.push(points[points.length - 1]);
		return stack;
	}

	/**
	 * This method performs graham scan algorithm and returns all the points in
	 * form of stack
	 * 
	 * @param points
	 * @return
	 */
	public String algorithmIncremental(Point[] points) {
		StringBuilder incre = new StringBuilder();
		
		points = ConvexHullUtils.sort(points);
		Point last = null;
		Stack<Point> stack = new Stack<Point>();
		stack.push(points[0]);
		incre.append(stack.toString().substring(1, stack.toString().length()-1));
		incre.append("\n");
		
		stack.push(points[1]);
		incre.append(stack.toString().substring(1, stack.toString().length()-1));
		incre.append("\n");
		
		last = points[2];

		for (int i = 3; i < points.length; i++) {
			while (!isLeftTurn(stack.peek(), last, points[i])) {
				last = stack.pop();
			}
			stack.push(last);
			last = points[i];
			incre.append(stack.toString().substring(1, stack.toString().length()-1));
			incre.append("\n");
			}

		stack.push(points[points.length - 1]);
		incre.append(stack.toString().substring(1, stack.toString().length()-1));

		return incre.toString();
	}

	
	public static void main(String[] args) throws FileNotFoundException {
		// read the points
		Point[] points = ConvexHullUtils.readInput(new FileInputStream(
				new File("input.txt")));
		GrahamScan obj = new GrahamScan();
		// get convex hull
		Stack<Point> stack = obj.algorithm(points);
		// print convex hull
                System.out.println(stack);
	}

}
