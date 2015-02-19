package assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PageRank {
	
	private ArrayList<Edge> edges;
	private int numNodes;
	private int numEdges;
	private double[][] M;
	private double[] r;
	private double beta;
	private double[] teleport;
	
	public PageRank(String file,int numNodes,double beta){
		readEdges(file);
		this.numNodes = numNodes;
		this.numEdges = edges.size();
		initializeMatrix();
		r = Matrix.makeVector(numNodes,(double)1/numNodes);
		this.beta = beta;
		teleport = Matrix.makeVector(numNodes,(double)(1-beta)/numNodes);
	}
	
	public double[] powerIterate(int iterations){
		for(int i = 0; i < iterations; i++){
			r = Matrix.MatrixDotVector(M,r);
			Matrix.constMultVector(beta,r);
			r = Matrix.addVectors(teleport,r);
		}
		return copyArray(r);
	}
	
	private void initializeMatrix(){
		//Guaranteed to be initialized to 0.0 by the language spec
		M = new double[numNodes][numNodes];
		Collections.sort(edges);
		for(int i = 0; i < numEdges; i++){
			Edge edge = edges.get(i);
			int to = edge.to;
			int from = edge.from;
			M[to-1][from-1] = M[to-1][from-1] + 1;
		}
		Matrix.constrainSumToOne(M);
	}
	
	public double[][] getMatrix(){
		return copyMatrix(M);
	}
	
	public double[] getRankings(){
		return copyArray(r);
	}
	
	public double[] getTeleport(){
		return copyArray(teleport);
	}
	
	public ArrayList<Edge> getEdges(){
		return new ArrayList<Edge>(edges);
	}
	
	private static double[] copyArray(double[] src){
		int size = src.length;
		double[] dest = new double[size];
		System.arraycopy(src,0,dest,0,size); 
		return dest;
	}
	
	private static double[][] copyMatrix(double[][] srcMatrix){
		int numRows = srcMatrix.length;
		int numCols = srcMatrix[0].length;
		double[][] destMatrix = new double[numRows][numCols];
		for(int i = 0; i < numRows; i++){
			System.arraycopy(srcMatrix[i],0,destMatrix[i],0,numCols);
		}
		return destMatrix;
	}
	
	private void readEdges(String file){
		edges = new ArrayList<Edge>();
		File fi = new File(file);
		BufferedReader br;
		try{
			br = new BufferedReader(new FileReader(fi));
			String line = null;
			String[] str;
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				str = line.split("\\s+");
				int from = Integer.parseInt(str[0]);
				int to = Integer.parseInt(str[1]);
				edges.add(new Edge(from,to));
			}
			br.close();
			return;
		}catch(IOException es){
			System.out.println("Error reading in edges");
		}
	}
	
}
