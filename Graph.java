import java.util.*;

public class Graph {

	private ArrayList<Node> nodeList;
	private ArrayList<Edge> edgeList;
	
	public Graph() {
		nodeList = new ArrayList<Node>();
		edgeList = new ArrayList<Edge>();
	}
	
	public Graph(ArrayList<Edge> edgeList1, ArrayList<Node> nodeList1) {
		this();
		nodeList.addAll(nodeList1);
		edgeList.addAll(edgeList1);
	}
	
	public ArrayList<Node> getNodeList() {
		return nodeList;
	}
	
	public ArrayList<Edge> getEdgeList() {
		return edgeList;
	}
	
	public void addNode(Node n) {
		nodeList.add(n);
	}
	
	public void addEdge(Edge e) {
		edgeList.add(e);
	}

	private void setNodeList(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}

	private void setEdgeList(ArrayList<Edge> edgeList) {
		this.edgeList = edgeList;
	}
	
	
	//input = graph
	//output = thisgraph
	
//	public void copyOf(ArrayList<Edge> edgeList1, ArrayList<Node> nodeList1) {
//		this.nodeList = new ArrayList<Node>(nodeList1);
//		this.edgeList = new ArrayList<Edge>(edgeList1);
//	}
	
	

	
}

