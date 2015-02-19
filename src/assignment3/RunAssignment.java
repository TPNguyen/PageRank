package assignment3;

import java.util.*;

public class RunAssignment {

	public static void main(String[] args) {
		PageRank pg = new PageRank("graph.txt",100,.8);
		ArrayList<Double> rBegin = Matrix.convertToArrayList(pg.getRankings());
		System.out.println("r begin: " + rBegin);
		double begin = System.currentTimeMillis();
		pg.powerIterate(40);
		double end = System.currentTimeMillis();
		double time = end - begin;
		ArrayList<Double> rEnd = Matrix.convertToArrayList(pg.getRankings());
		System.out.println("r end: " + rEnd);
		System.out.println("Time elapsed: " + time);
	}
	
}
