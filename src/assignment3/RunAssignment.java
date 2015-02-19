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
		System.out.println("Average Error R=1 K=10 " + absoluteError(mc,10,1,rEnd));
		System.out.println("Average Error R=1 K=30 " + absoluteError(mc,30,1,rEnd));
		System.out.println("Average Error R=1 K=50 " + absoluteError(mc,50,1,rEnd));
		System.out.println("Average Error R=1 K=100 " + absoluteError(mc,100,1,rEnd));
		System.out.println("Average Error R=3 K=10 " + absoluteError(mc,10,3,rEnd));
		System.out.println("Average Error R=3 K=30 " + absoluteError(mc,30,3,rEnd));
		System.out.println("Average Error R=3 K=50 " + absoluteError(mc,50,3,rEnd));
		System.out.println("Average Error R=3 K=100 " + absoluteError(mc,100,3,rEnd));
		System.out.println("Average Error R=5 K=10 " + absoluteError(mc,10,5,rEnd));
		System.out.println("Average Error R=5 K=30 " + absoluteError(mc,30,5,rEnd));
		System.out.println("Average Error R=5 K=50 " + absoluteError(mc,50,5,rEnd));
		System.out.println("Average Error R=5 K=100 " + absoluteError(mc,100,5,rEnd));
	}
	
	private static double absoluteError(MonteCarlo mc, int k, int rWalks, ArrayList<Double> trueValue){
		double overAllError = 0.0;
		for(int i = 0; i < 100; i++){
			double kError = 0.0;
			double[] approx = mc.randomWalk(rWalks);
			ArrayList<Score> scores = convertToScore(approx);
			Collections.sort(scores);
			Collections.reverse(scores);
			for(int j = 0; j < k; j++){
				Score a = scores.get(j);
				kError = kError + Math.abs((trueValue.get(a.index) - a.score));
			}
			overAllError = overAllError + kError/(double)k;
		}
		overAllError = overAllError/(double)(100);
		return overAllError;
	}
	
	//Very inefficient, can do this linear (no need to do all of this and then sort
	private static ArrayList<Score> convertToScore(double[] approx){
		ArrayList<Score> scores = new ArrayList<Score>();
		for(int i = 0; i < approx.length; i++){
			scores.add(new RunAssignment.Score(i,approx[i]));
		}
		return scores;
	}
	
	static class Score implements Comparator<Score>, Comparable<Score>{
		public int index;
		public double score;
		
		public Score(int index, double score){
			this.index = index;
			this.score = score;
		}
		
		public int hashCode() {
			return new Integer(index).hashCode();
		}
		
		public boolean equals(Object obj) {
			if (!(obj instanceof Edge)) return false;
			if (obj == this) return true;
			Score sc = (Score)obj;
			if(sc.index == this.index){
				return true;
			}else{
				return false;
			}
		}
		
		// Overriding the compareTo method
		public int compareTo(Score sc){
			Double thisScore = new Double(this.score);
			Double scScore = new Double(sc.score);
			return thisScore.compareTo(scScore);
		}

		// Overriding the compare method to sort by node index
		// Sorting by the to node is more efficient
		public int compare(Score sc1, Score sc2){
			if(sc1.score == sc2.score) return 0;
			if(sc1.score < sc2.score){
				return -1;
			}else{
				return 1;
			}
		}
		
		public String toString(){
			return "score: " + score + " index: " + index;
		}
	}
	
	
	
}
