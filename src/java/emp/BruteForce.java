package emp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class implements the brute force method for finding the convex hull
 * 
 * @author Hitesh Vyas
 * 
 */
public class BruteForce {

	/**
	 * This method performs brute force algorithm.
	 * It creates a line by using 2 points, and checks the direction of each point from that line.
	 * If all points come at one of the side then it adds to the hull
	 * 
	 * @param points
	 */
	public void algorithm(Point[] points) {
		
		//for all the points
		for (int startPoint = 0; startPoint < points.length - 1; startPoint++) {
			for (int endPoint = startPoint + 1; endPoint < points.length; endPoint++) {
				//create a line out of 2 points
				Line line = new Line(points[startPoint], points[endPoint]);

				double sideOfInitialPoint = 0;
				boolean isHull = true;
				//check for all points for the line
				for (int aPoint = 0; aPoint < points.length; aPoint++) {
					if (aPoint == startPoint || aPoint == endPoint) {
						continue;
					}
					
					//check the direction of point with respect to the line
					double side = line.directionOfPoint(points[aPoint]);
					
					if (sideOfInitialPoint == 0) {
						sideOfInitialPoint = side;
					} else if (sideOfInitialPoint < 0 && side > 0) {
						isHull = false;
						break;
					} else if (sideOfInitialPoint > 0 && side < 0) {
						isHull = false;
						break;
					}
				}
				
				//if all points lie on the same side, add it to hull
				if (isHull) {
					points[startPoint].hull = true;
					points[endPoint].hull = true;
				}
			}
		}
	}

	public String algorithmIncremental(Point[] points) {
		StringBuilder sc = new StringBuilder();
		String point ="";
		
		//for all the points
		for (int startPoint = 0; startPoint < points.length - 1; startPoint++) {
			for (int endPoint = startPoint + 1; endPoint < points.length; endPoint++) {
				//create a line out of 2 points
				Line line = new Line(points[startPoint], points[endPoint]);
				String temp = points[startPoint].toString() +","+ points[endPoint].toString();
						
				double sideOfInitialPoint = 0;
				boolean isHull = true;
				//check for all points for the line
				for (int aPoint = 0; aPoint < points.length; aPoint++) {
					if (aPoint == startPoint || aPoint == endPoint) {
						continue;
					}
					
					//check the direction of point with respect to the line
					double side = line.directionOfPoint(points[aPoint]);
					
					if (sideOfInitialPoint == 0) {
						sideOfInitialPoint = side;
					} else if (sideOfInitialPoint < 0 && side > 0) {
						isHull = false;
						break;
					} else if (sideOfInitialPoint > 0 && side < 0) {
						isHull = false;
						break;
					}
				}
				
				//if all points lie on the same side, add it to hull
				if (isHull) {
					points[startPoint].hull = true;
					points[endPoint].hull = true;
					if(!point.equals("")){
						point = point+",";
					}
					point = point + points[startPoint].x+" "+points[startPoint].y+","+points[endPoint].x+" "+points[endPoint].y;
					sc.append(point+"$"+temp+"\n");
				}
			}
		}
		return sc.toString();
	}

	
	public static void main(String[] args) throws FileNotFoundException {
		//read the points
		Point[] points = ConvexHullUtils.readInput(new FileInputStream(new File(args[0])));
		BruteForce obj = new BruteForce();
		//run the algorithm
		obj.algorithm(points);
		//sort it anticlockwise
		points = ConvexHullUtils.sort(points);
		//print the hull
		ConvexHullUtils.printHull(points);
	}

}
