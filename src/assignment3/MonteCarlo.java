package assignment3;

import java.util.*;

public class MonteCarlo {
	//Need to keep a map of nodes to edges
	private HashMap<Integer,ArrayList<Edge>> nextJump;
	private ArrayList<Edge> allEdges;
	private double[] rScores;
	private int numNodes;
	
	public MonteCarlo(int numNodes,ArrayList<Edge> allEdges){
		nextJump = new HashMap<Integer,ArrayList<Edge>>(numNodes);
		this.allEdges = new ArrayList<Edge>(allEdges);
		Collections.sort(this.allEdges);
		populateMap();
		this.numNodes = numNodes;
		rScores = new double[numNodes];
	}
	
	public double[] randomWalk(int RTimes){
		for(int i = 0; i < numNodes; i++){
			for(int r = 0; r < RTimes; r++){
				if(nextJump.containsKey(i)){
					ArrayList<Edge> possibilities = nextJump.get(i);
					//Need to randomly pick one of these
				}
			}
		}
		return null;
	}
	
	public double[] getRankings(){
		return Matrix.copyArray(rScores);
	}
	
	private void populateMap(){
		int size = allEdges.size();
		for(int i = 0; i < size; i++){
			Edge edge = allEdges.get(i);
			if(nextJump.containsKey(edge.from)){
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
