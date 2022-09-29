import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class DelivE {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	// Constructor - DO NOT MODIFY
	public DelivE(File in, Graph gr) {
		inputFile = in;
		graph = gr;

		// Set up for writing to a file
		try {
			// Use input file name to create output file in the same location
			String inputFileName = inputFile.toString();
			String outputFileName = inputFileName.substring(0, inputFileName.length() - 4).concat("_out.txt");
			outputFile = new File(outputFileName);

			// A Printwriter is an object that can write to a file
			output = new PrintWriter(outputFile);
		} catch (Exception x) {
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}

		// Calls the method that will do the work of deliverable E
		runDelivE();

		output.flush();
	}

	// *********************************************************************************
	// This is where your work starts
	// make private

	private int distance;
	private Random dice = new Random();
	private int iTime = 1;
	private int jTime = 1;
	private Node lastI = new Node("");
	private Node lastJ = new Node("");
	private int edgeCount = 0;
	private int endCount = 0;
	private int min = Integer.MAX_VALUE;

	private void runDelivE() {

		// creating traversal nodes
		Node i = new Node("");
		Node j = new Node("");

		for (int h = 0; h < 25000; h++) {

			if (h == 24999) {
				System.out.println("Shortest path search is complete (:");
			}

			for (Node n : graph.getNodeList()) {
				n.setDiscoveryTimeI(-1);
				n.setDiscoveryTimeJ(-1);
			}

			// loop here
			lastI = null;
			lastJ = null;

			// resetting time
			iTime = 1;
			jTime = 1;

			distance = 0;

			i = null;
			j = null;

			// Sorts all outgoing edge lists associated with all node objects in graph in
			// ascending order based on distance
			for (Node n : graph.getNodeList()) {
				Collections.sort(n.getOutgoingEdges(), new EdgeComparerE());
			}

			// Sorts all edges in graph in ascending order based on distance
			Collections.sort(graph.getEdgeList(), new EdgeComparerE());

			// set distance as the distance of the shortest edge in edgelist
			distance += graph.getEdgeList().get(0).getDistance();

			// assigning traversal nodes to the two nodes associated with the shortest edge
			i = graph.getEdgeList().get(0).getHead();
			j = graph.getEdgeList().get(0).getTail();

			j.setDiscoveryTimeJ(jTime);
			jTime++;
			i.setDiscoveryTimeI(iTime);
			iTime++;

			for (Node n : graph.getNodeList()) {
				for (Edge e : n.getOutgoingEdges()) {
					if (e.getHead() == i || e.getHead() == j) {
						e.setEdgeType("abandoned");
					}
				}
			}

			// calling recursive method, will return when all nodes have been traversed
			recursiveMethod(i, j);

			ArrayList<Node> finalPath = new ArrayList<Node>();
			ArrayList<Node> finalPathI = new ArrayList<Node>();
			ArrayList<Node> finalPathJ = new ArrayList<Node>();
			for (Node n : graph.getNodeList()) {
				if (n.getDiscoveryTimeI() != -1) {
					finalPathI.add(n);
				}
				if (n.getDiscoveryTimeJ() != -1) {
					finalPathJ.add(n);
				}

			}
			// I goes from largest to smallest (decending)
			Collections.sort(finalPathI, new NodeComparerI());
			Collections.sort(finalPathJ, new NodeComparerJ());
			for (Node n : finalPathI) {
				finalPath.add(n);
			}

			for (Node n : finalPathJ) {
				finalPath.add(n);
			}

			distance += edgeLength(finalPath.get(0), finalPath.get(finalPath.size() - 1));

			// *************************writing to file********************
			String outputx = "";

			for (Node n : finalPath) {
				if (finalPath.get(0) == n) {
					outputx += n.getAbbrev();
				} else {
					outputx += " -> " + n.getAbbrev();
				}
			}
			output.write("\nShortest bitonic tour has distance " + min);
			output.write("\nThis tour's distance is " + distance);
			output.write("\nTour is " + outputx + "\n");

			// *************************************************************

			if (distance < min && distance > 9) {
				min = distance;

				System.out.println("Shortest bitonic tour has distance " + distance);
				String outputy = "";

				for (Node n : finalPath) {
					if (finalPath.get(0) == n) {
						outputy += n.getAbbrev();
					} else {
						outputy += " -> " + n.getAbbrev();
					}
				}
				System.out.println("Tour is " + outputy);
			}

		}

	}

	public void recursiveMethod(Node i, Node j) {

		int iorJ = dice.nextInt(2);
		int tempDeterminingDistanceI = distance;

		ArrayList<Edge> tempEdgeListI = new ArrayList<Edge>();
		tempEdgeListI.clear();
		int tempEdgeListCountI = 0;

		for (Edge e : i.getOutgoingEdges()) {
			if (e.getHead().getDiscoveryTimeI() == -1 && e.getHead().getDiscoveryTimeJ() == -1) {
				tempEdgeListI.add(e);
				tempEdgeListCountI++;
			}
			if (tempEdgeListCountI > 2) {
				break;
			}
		}

		if (tempEdgeListI.size() == 3) {
			tempDeterminingDistanceI += tempEdgeListI.get(0).getDistance() + tempEdgeListI.get(1).getDistance()
					+ tempEdgeListI.get(2).getDistance();
		}

		int tempDeterminingDistanceJ = distance;

		ArrayList<Edge> tempEdgeListJ = new ArrayList<Edge>();
		tempEdgeListJ.clear();
		int tempEdgeListCountJ = 0;

		for (Edge e : j.getOutgoingEdges()) {
			if (e.getHead().getDiscoveryTimeI() == -1 && e.getHead().getDiscoveryTimeJ() == -1) {
				tempEdgeListJ.add(e);
				tempEdgeListCountJ++;
			}
			if (tempEdgeListCountJ > 2) {
				break;
			}
		}

		if (tempEdgeListJ.size() == 3) {
			tempDeterminingDistanceJ += tempEdgeListJ.get(0).getDistance() + tempEdgeListJ.get(1).getDistance()
					+ tempEdgeListJ.get(2).getDistance();
		}

		if (tempEdgeListJ.size() == 3 && tempEdgeListI.size() == 3) {
			if (tempDeterminingDistanceI > tempDeterminingDistanceJ) {
				iorJ = 1;
			} else {
				iorJ = 0;
			}
		}

		if (iorJ == 0) {

			int edgeCount = 0;

			int endCount = 0;

			iorJ = -1;

			// TRAVERSING THROUGH I NODE

			ArrayList<Edge> tempOutgoingEdgeListI = new ArrayList<Edge>();

			tempOutgoingEdgeListI.clear();

			// continue loop looking for three smallest edges until 3 are found that are
			// valid next options, or
			// until all edges have been iterated through

			// EDGE COUNT DETERMINES HOW LARGE NEXT RANDOM POOL IS

			for (Edge e : i.getOutgoingEdges()) {

				if (e.getHead().getDiscoveryTimeJ() == -1 && e.getHead().getDiscoveryTimeI() == -1) {

					tempOutgoingEdgeListI.add(e);
					edgeCount++;
					if (edgeCount > 2) {
						break;
					}
				}
				endCount++;
				if (endCount >= 48) {
					break;
				}
			}

			// reached end of path, no nodes left that have not been discovered
			if (edgeCount == 0) {
				lastJ = j;
				lastI = i;
				return;
			}

			// if less than three possible options have been found, use first option in list
			else if (edgeCount > 0 && edgeCount < 3) {

				distance += edgeLength(i, tempOutgoingEdgeListI.get(0).getHead());
				tempOutgoingEdgeListI.get(0).setEdgeType("abandoned");
				tempOutgoingEdgeListI.get(0).getHead().setDiscoveryTimeI(iTime);
				iTime++;
				tempOutgoingEdgeListI.get(0).getHead().setColor("Black");
				recursiveMethod(tempOutgoingEdgeListI.get(0).getHead(), j);
			}

			// found top three
			else if (edgeCount == 3) {
				int nextNode = dice.nextInt(2);

				distance += edgeLength(i, tempOutgoingEdgeListI.get(nextNode).getHead());
				tempOutgoingEdgeListI.get(nextNode).setEdgeType("abandoned");
				tempOutgoingEdgeListI.get(nextNode).getHead().setDiscoveryTimeI(iTime);
				iTime++;
				tempOutgoingEdgeListI.get(nextNode).getHead().setColor("Black");
				recursiveMethod(tempOutgoingEdgeListI.get(nextNode).getHead(), j);
			}
		}

		if (iorJ == 1) {

			int edgeCount = 0;

			int endCount = 0;

			iorJ = -1;

			// TRAVERSING THROUGH I NODE

			ArrayList<Edge> tempOutgoingEdgeListJ = new ArrayList<Edge>();

			tempOutgoingEdgeListJ.clear();

			// continue loop looking for three smallest edges until 3 are found that are
			// valid next options, or
			// until all edges have been iterated through

			// EDGE COUNT DETERMINES HOW LARGE NEXT RANDOM POOL IS

			for (Edge e : j.getOutgoingEdges()) {

				if (e.getHead().getDiscoveryTimeJ() == -1 && e.getHead().getDiscoveryTimeI() == -1) {

					tempOutgoingEdgeListJ.add(e);
					edgeCount++;
					if (edgeCount > 2) {
						break;
					}
				}
				endCount++;
				if (endCount >= 48) {
					break;
				}
			}

			// reached end of path, no nodes left that have not been discovered
			if (edgeCount == 0) {
				lastJ = j;
				lastI = i;
				return;
			}

			// if less than three possible options have been found, use first option in list
			else if (edgeCount > 0 && edgeCount < 3) {

				distance += edgeLength(j, tempOutgoingEdgeListJ.get(0).getHead());
				tempOutgoingEdgeListJ.get(0).setEdgeType("abandoned");
				tempOutgoingEdgeListJ.get(0).getHead().setDiscoveryTimeJ(jTime);
				jTime++;
				tempOutgoingEdgeListJ.get(0).getHead().setColor("Black");
				recursiveMethod(i, tempOutgoingEdgeListJ.get(0).getHead());
			}

			// found top three
			else if (edgeCount == 3) {
				int nextNode = dice.nextInt(2);

				distance += edgeLength(j, tempOutgoingEdgeListJ.get(nextNode).getHead());
				tempOutgoingEdgeListJ.get(nextNode).setEdgeType("abandoned");
				tempOutgoingEdgeListJ.get(nextNode).getHead().setDiscoveryTimeJ(jTime);
				jTime++;
				recursiveMethod(i, tempOutgoingEdgeListJ.get(nextNode).getHead());
				tempOutgoingEdgeListJ.get(nextNode).getHead().setColor("Black");
			}

		}
		return;
	}

	/*
	 * @param tailNode
	 * 
	 * @param headNode
	 * 
	 * @return
	 */
	public Edge getEdge(Node tailNode, Node headNode) {
		Edge temp = new Edge(null, null, 0);
		for (Edge e : graph.getEdgeList()) {
			if (e.getTail() == tailNode && e.getHead() == headNode) {
				temp = e;
				return temp;
			}
		}
		return temp;
	}

	/**
	 * 
	 * @param head
	 * @param tail
	 * @return distance of edge associated with input head & tail
	 */
	public int edgeLength(Node head, Node tail) {
		int output = 0;
		for (Edge e : graph.getEdgeList()) {
			if (e.getHead() == head & e.getTail() == tail) {
				output = e.getDistance();
				break;
			}
		}
		return output;
	}

	private class EdgeComparerE implements Comparator<Edge> {

		@Override
		public int compare(Edge edge1, Edge edge2) {
			int output = 0;

			if (edge1.getDistance() > edge2.getDistance()) {
				return output = 1;
			} else if (edge1.getDistance() < edge2.getDistance()) {
				return output = -1;
			}
			return output;
		}
	}

	private class NodeComparerI implements Comparator<Node> {

		@Override
		public int compare(Node node1, Node node2) {
			int output = 0;

			if (node1.getDiscoveryTimeI() < node2.getDiscoveryTimeI()) {
				return output = 1;
			} else if (node1.getDiscoveryTimeI() > node2.getDiscoveryTimeI()) {
				return output = -1;
			}
			return output;
		}
	}

	private class NodeComparerJ implements Comparator<Node> {

		@Override
		public int compare(Node node1, Node node2) {
			int output = 0;

			if (node1.getDiscoveryTimeJ() > node2.getDiscoveryTimeJ()) {
				return output = 1;
			} else if (node1.getDiscoveryTimeJ() < node2.getDiscoveryTimeJ()) {
				return output = -1;
			}
			return output;
		}
	}

}