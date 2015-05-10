/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp;
import java.util.Scanner;


public class code {
	
	public void stringToArray(String s){
		
		Scanner sc = new Scanner(s);
		int[] x;
		int[] y;
		
		while(sc.hasNextLine()){
			String[] ar = sc.nextLine().split(",");
			x = new int[ar.length];
			y = new int[ar.length];
			for(int i=0;i<ar.length;i++){
				String[] xy = ar[i].split(" ");
				x[i] = Integer.parseInt(xy[0]);
				y[i] = Integer.parseInt(xy[1]);
			}
			
			//x and y array
		}
	}
	
	public String arrayToString(int[] x, int[] y){
		
		StringBuilder sc = new StringBuilder();
		for(int i=0;i<x.length;i++){
			if(i==x.length-1){
				sc.append(x[i]+" "+y[i]);				
			}else{
				sc.append(x[i]+" "+y[i]+",");
			}	
		}	
	
		return sc.toString();
	}
	

}

