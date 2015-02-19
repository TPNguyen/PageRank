package assignment3;

import java.util.*;

public class RunAssignment {

	public static void main(String[] args) {
		//The normal page rank
		PageRank pg = new PageRank("graph.txt",100,.8);
		ArrayList<Double> rBegin = Matrix.convertToArrayList(pg.getRankings());
		//System.out.println("r begin: " + rBegin);
		double begin = System.currentTimeMillis();
		pg.powerIterate(40);
		double end = System.currentTimeMillis();
		double time = (end - begin)/1000;
		ArrayList<Double> rEnd = Matrix.convertToArrayList(pg.getRankings());
		//System.out.println("r end: " + rEnd);
		System.out.println("Time elapsed pageRank: " + time);
		//Monte carlo below here
		ArrayList<Double> monteApprox = null;
		MonteCarlo mc = new MonteCarlo(100,pg.getEdges(),.8);
		//For R = 1
		begin = System.currentTimeMillis();
		monteApprox = Matrix.convertToArrayList(mc.randomWalk(1));
		end = System.currentTimeMillis();
		time = (end - begin)/1000;
		//System.out.println("mcApprox R1: " + monteApprox);
		System.out.println("Time elapsed R1: " + time);
		//For R = 2
		begin = System.currentTimeMillis();
		monteApprox = Matrix.convertToArrayList(mc.randomWalk(2));
		end = System.currentTimeMillis();
		time = (end - begin)/1000;
		//System.out.println("mcApprox R2: " + monteApprox);
		System.out.println("Time elapsed R2: " + time);
		//For R = 3
		begin = System.currentTimeMillis();
		monteApprox = Matrix.convertToArrayList(mc.randomWalk(3));
		end = System.currentTimeMillis();
		time = (end - begin)/1000;
		//System.out.println("mcApprox R3: " + monteApprox);
		System.out.println("Time elapsed R3: " + time);
	}
	
	
	
}
