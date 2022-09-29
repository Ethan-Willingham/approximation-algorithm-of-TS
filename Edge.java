//import java.util.*;

//   5/28/2022 - Added toString method for testing purposes
//	 6/03/2022 - Added edgeType instance variable

// Edge between two nodes
public class Edge {
	
	private int distance;
	private Node tail;
	private Node head;
	private String edgeType;
	
	public Edge(Node tailNode, Node headNode, int dist) {
		distance = dist;
		tail = tailNode;
		head = headNode;
		edgeType = "";
	}
	
	public Node getTail() {
		return tail;
	}
	
	public Node getHead() {
		return head;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setTail(Node newTail) {
		tail = newTail;
	}
	
	public void setHead(Node newHead) {
		head = newHead;
	}
	
	public void setDistance(int dist) {
		distance = dist;
	}
	
	
	public String getEdgeType() {
		return edgeType;
	}

	public void setEdgeType(String edgeType) {
		this.edgeType = edgeType;
	}

	public String toString() {
		String output = "\nTail: " + "'" + tail.getName() + tail.getAbbrev() + "'" + "\tHead: " + "'" + head.getName() + "'" + head.getAbbrev() + "\tDistance: " + distance;
		return output;
	}
}
