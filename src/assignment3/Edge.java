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
		Integer thisTo = new Integer(this.to);
		Integer edTo = new Integer(ed.to);
		int result = thisTo.compareTo(edTo);
		if(result == 0){
			Integer thisFrom = new Integer(this.from);
			Integer edFrom = new Integer(ed.from);
			return thisFrom.compareTo(edFrom);
		}else{
			return result;
		}
	}

	// Overriding the compare method to sort by node index
	// Sorting by the to node is more efficient
	public int compare(Edge ed1, Edge ed2){
		int result = ed1.to - ed2.to;
		if(result == 0){
			//Sort by from if they point to the same node
			return ed1.from - ed2.from;
		}else{
			return result;
		}
	}
   
	public String toString(){
		return "from: " + from + " to: " + to;
	}
}
