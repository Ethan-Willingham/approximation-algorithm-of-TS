import java.util.*;

// A node of a graph for the Spring 2018 ICS 340 program

public class Node {

	private String name;
	private String value;  // The value of the Node which was stored in the value column
	private String abbrev;  // The abbreviation for the Node
	private ArrayList<Edge> outgoingEdges;  
	private ArrayList<Edge> incomingEdges;
	private String color;
	private Node predecesor;
	private int DiscoveryTime;
	private int DiscoveryTimeI;
	private int DiscoveryTimeJ;
	private int finishTime;
	private int key;
	private Node sibling;
	private ArrayList<Edge> tempOutgoingEdges;
	
	
	public Node(String abbreviation) {
		abbrev = abbreviation;
		value = null;
		name = null;
		outgoingEdges = new ArrayList<Edge>();
		incomingEdges = new ArrayList<Edge>();
		tempOutgoingEdges = new ArrayList<Edge>();
		color = "";
		predecesor = null;
		DiscoveryTime = -1;
		finishTime = -1;
		key = Integer.MAX_VALUE;
		DiscoveryTimeI = -1;
		DiscoveryTimeJ = -1;
		
	}
	
	public String getAbbrev() {
		return abbrev;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
	public ArrayList<Edge> getOutgoingEdges() {
		return outgoingEdges;
	}
	
	public ArrayList<Edge> getIncomingEdges() {
		return incomingEdges;
	}
	
	public void setAbbrev(String abbreviation) {
		abbrev = abbreviation;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void addOutgoingEdge(Edge e) {
		outgoingEdges.add(e);
	}
	
	public void addIncomingEdge(Edge e) {
		incomingEdges.add(e);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Node getPredecesor() {
		return predecesor;
	}

	public void setPredecesor(Node predecesor) {
		this.predecesor = predecesor;
	}

	public int getDiscoveryTime() {
		return DiscoveryTime;
	}

	public void setDiscoveryTime(int discoveryTime) {
		DiscoveryTime = discoveryTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public void setOutgoingEdges(ArrayList<Edge> outgoingEdges) {
		this.outgoingEdges = outgoingEdges;
	}

	public void setIncomingEdges(ArrayList<Edge> incomingEdges) {
		this.incomingEdges = incomingEdges;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Node getSibling() {
		return sibling;
	}

	public void setSibling(Node sibling) {
		this.sibling = sibling;
	}

	public ArrayList<Edge> getTempOutgoingEdges() {
		return tempOutgoingEdges;
	}

	public void setTempOutgoingEdges(ArrayList<Edge> tempOutgoingEdges) {
		this.tempOutgoingEdges = tempOutgoingEdges;
	}

	public int getDiscoveryTimeI() {
		return DiscoveryTimeI;
	}

	public void setDiscoveryTimeI(int discoveryTimeI) {
		DiscoveryTimeI = discoveryTimeI;
	}

	public int getDiscoveryTimeJ() {
		return DiscoveryTimeJ;
	}

	public void setDiscoveryTimeJ(int discoveryTimeJ) {
		DiscoveryTimeJ = discoveryTimeJ;
	}
	
	
	
	
	
}



