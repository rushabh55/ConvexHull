package emp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * This is a utility class for Graham scan
 * 
 * @author hitesh vyas
 * 
 */
public class ConvexHullUtils {

	/**
	 * This method reads input of points and returns an array of it
	 * 
	 * @param input
	 * @return points
	 */
	public static Point[] readInput(InputStream input) {
		Scanner sc = new Scanner(input);
		int numberOfPoints = sc.nextInt();
		Point[] points = new Point[numberOfPoints];

		for (int i = 0; i < numberOfPoints; i++) {
			points[i] = new Point(sc.nextInt(), sc.nextInt());
		}
		sc.close();
		return points;
	}

	public static Point[] readInput(String data) {
		Scanner sc = new Scanner(data);

		String[] in = data.split(",");
        Point[] points = new Point[in.length];

		for (int i = 0; i < points.length; i++) {
			String [] p = in[i].split(" ");
			points[i] = new Point(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
		}
		sc.close();		
		return points;
	}

	private static void setPoints(String input) throws IOException {
		File f = new File("points.txt");
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
		out.flush();

		Scanner sc = new Scanner(input);
		String[] in = input.split(",");
		Point[] points = new Point[in.length];

		for (int i = 0; i < points.length; i++) {
			String[] p = in[i].split(" ");
			points[i] = new Point(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
		}
		sc.close();
		out.close();
		out.writeObject(points);

	}

	private static Point[] getPoints() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("points.txt")));
		Point[] points = (Point[]) in.readObject();
		in.close();
		return points;

	}

	public static Point[] readInput(String input, String flag) throws FileNotFoundException, ClassNotFoundException, IOException {

		if (flag == null || flag.equalsIgnoreCase("null")) {
			setPoints(input);
			return null;
		} else {
			return getPoints();
		}
	}

	/**
	 * This internal method finds the lowest point
	 * 
	 * @param points
	 * @return lowest point
	 */
	private static int findMin(Point[] points) {
		int min = 0;

		for (int i = 1; i < points.length; i++) {
			min = (points[i].y == points[min].y) ? (points[i].x < points[min].x ? i : min) : (points[i].y < points[min].y ? i : min);
		}

		return min;
	}

	/**
	 * This method sorts the points using polar angle, if polar angles are same
	 * it sorts using straight line distance
	 * 
	 * @param points
	 * @return sorted points
	 */
	public static Point[] sort(Point[] points) {
		int min = findMin(points);

		List<Line> lines = new ArrayList<Line>();
		for (int i = 0; i < points.length; i++) {
			if (i != min) {
				lines.add(new Line(points[min], points[i]));
			}
		}
		Collections.sort(lines);

		Point[] sortedPoints = new Point[points.length];
		sortedPoints[0] = points[min];
		int j = 1;
		for (Line l : lines) {
			sortedPoints[j++] = l.p2;
		}

		return sortedPoints;
	}

	/**
	 * This method writes all the convex hull points into a file for brute force
	 * 
	 * @param points
	 * @throws FileNotFoundException
	 */
	public static void printHull(Point points[]) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(new File("Brute_Force_Output_" + (new Date())));
		// count the points in hull
		int count = 0;
		for (Point p : points) {
			if (p.hull) {
				count++;
			}
		}

		out.println(count);
		for (Point p : points) {
			if (p.hull) {
				out.println(p);
			}
		}
		out.close();
	}

	/**
	 * This method writes all the convex hull points into a file for graham scan
	 * 
	 * @param points
	 * @throws FileNotFoundException
	 */
	public static void printHull(Collection<Point> list) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(new File("Graham_Scan_Output_" + (new Date())));

		out.println(list.size());
		for (Point p : list) {
			out.println(p);
		}
		out.close();
	}
}
