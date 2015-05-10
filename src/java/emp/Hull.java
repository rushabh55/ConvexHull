/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author hitesh
 */
@WebService(serviceName = "Hull")
@Stateless()
public class Hull {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        
        Point[] points = ConvexHullUtils.readInput(txt);
        
		GrahamScan obj = new GrahamScan();
		// get convex hull
		Stack<Point> stack = obj.algorithm(points);
                return stack.toString();
    }
}
