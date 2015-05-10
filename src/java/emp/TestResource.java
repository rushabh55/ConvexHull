/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp;

import java.io.File;
import java.io.FileInputStream;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Stack;

/**
 * REST Web Service
 *
 * @author Pritesh
 */
@Path("/Test/{array}")
public class TestResource {

    /**
     * Creates a new instance of TestResource
     */
    public TestResource() {
    }

    /**
     * Retrieves representation of an instance of emp.TestResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText(@PathParam("array") String data) {
    	
    	if(data.charAt(0)=='c'){
    		
    		data = data.substring(1);
    		Point[] points = ConvexHullUtils.readInput(data);
    		BruteForce obj = new BruteForce();
    		return obj.algorithmIncremental(points);
    		
    		
    	}else if(data.charAt(0)=='b'){
    		data = data.substring(1);
    		Point[] points = ConvexHullUtils.readInput(data);
    		BruteForce obj = new BruteForce();
    		obj.algorithm(points);
    		StringBuilder sc = new StringBuilder();
            
            for(Point p : points){
            	if(p.hull){
            		sc.append(p.x+" "+p.y+",");
            	}
            }
            return sc.toString().substring(0, sc.toString().length()-1);
    	
    	}else if(data.charAt(0)=='i'){
    		data = data.substring(1);
  
    		//get the output
    		Point[] points = ConvexHullUtils.readInput(data);
    		GrahamScan obj = new GrahamScan();
    		
    		// get convex hull
    		return obj.algorithmIncremental(points);

    		
    	}else{
    		//get the output
    		Point[] points = ConvexHullUtils.readInput(data);
    		GrahamScan obj = new GrahamScan();
    		
    		// get convex hull
    		Stack<Point> stack = obj.algorithm(points);
            StringBuilder sc = new StringBuilder();
            
            while(!stack.isEmpty()){
            	Point p = stack.pop();
            	if(stack.isEmpty()){
                	sc.append(p.x+" "+p.y);
            	}else{
                	sc.append(p.x+" "+p.y+",");
            	}
            }

            return sc.toString();   
    	}

    }

    /**
     * PUT method for updating or creating an instance of TestResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
//    @PUT
//    @Consumes("text/plain")
//    public void putText(String content) {
//    }
}
