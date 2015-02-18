package assignment3;

import java.util.Comparator;

public class Edge implements Comparator<Edge>, Comparable<Edge>{
	public int from;
	public int to;
	
	public Edge(int from, int to){
		this.from = from;
		this.to = to;
	}
	
	public int hashCode() {
		return new Integer(to).hashCode() + new Integer(from).hashCode();
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof Edge)) return false;
		if (obj == this) return true;
		Edge ed = (Edge)obj;
		if(ed.to == this.to && ed.from == this.from){
			return true;
		}else{
			return false;
		}
	}
	
	// Overriding the compareTo method
	public int compareTo(Edge ed){
		Integer thisFrom = new Integer(this.from);
		Integer edFrom = new Integer(ed.from);
		return thisFrom.compareTo(edFrom);
	}

	// Overriding the compare method to sort by size
	// Sort by the from node
	public int compare(Edge ed1, Edge ed2){
		return ed1.from - ed2.from;
	}
   
	public String toString(){
		return "from: " + from + " to: " + to;
	}
}
