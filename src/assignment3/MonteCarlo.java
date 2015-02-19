package assignment3;

import java.util.*;

public class MonteCarlo {
	//Need to keep a map of nodes to edges
	private HashMap<Integer,ArrayList<Edge>> nextJump;
	private ArrayList<Edge> allEdges;
	private double[] rScores;
	private int numNodes;
	private double beta;
	private Random rand;
	
	public MonteCarlo(int numNodes,ArrayList<Edge> allEdges,double beta){
		nextJump = new HashMap<Integer,ArrayList<Edge>>(numNodes);
		this.allEdges = new ArrayList<Edge>(allEdges);
		Collections.sort(this.allEdges);
		populateMap();
		this.numNodes = numNodes;
		this.beta = beta;
		rScores = new double[numNodes];
		rand = new Random();
	}
	
	public double[] randomWalk(int RTimes){
		rScores = new double[rScores.length];
		for(int i = 0; i < numNodes; i++){
			if(nextJump.containsKey(i)){
			ArrayList<Edge> iPossibilities = nextJump.get(i);
				for(int r = 0; r < RTimes; r++){
					double termOrContinue = 0.0;
					int index = 0;
					ArrayList<Edge> thisPossibilities = new ArrayList<Edge>(iPossibilities);
					while(termOrContinue <= beta){
						int choices = thisPossibilities.size();
						index = rand.nextInt(choices);
						Edge edge = thisPossibilities.get(index);
						rScores[edge.to] = rScores[edge.to] + 1;
						termOrContinue = rand.nextDouble();
						if(nextJump.containsKey(edge.to)){
							thisPossibilities = nextJump.get(edge.to);
						}else{
							break;
						}
					}
				}
			}
		}
		double num = numNodes*RTimes;
		double denom = 1 - beta;
		Matrix.constMultVector(denom/num,rScores);
		return rScores;
	}
	
	public double[] getRankings(){
		return Matrix.copyArray(rScores);
	}
	
	private void populateMap(){
		int size = allEdges.size();
		for(int i = 0; i < size; i++){
			Edge edge = allEdges.get(i);
			if(!nextJump.containsKey(edge.from)){
				ArrayList<Edge> edgeList = new ArrayList<Edge>();
				edgeList.add(edge);
				nextJump.put(edge.from,edgeList);
			}else{
				ArrayList<Edge> edgeList = nextJump.get(edge.from);
				edgeList.add(edge);
			}
		}
	}
}
