import java.util.ArrayList;

public class Graph {
	ArrayList<ArrayList<Integer>> adj;
	ArrayList<Node> nodes;
	ArrayList<Integer> sorted_nodes;
	
	public Graph(ArrayList<Node> nodes) {
		this.nodes = nodes;
		
		// do sorting here
		// this.sorted_nodes = sort by Node.title;
		
		this.adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < nodes.size(); i++)
			this.adj.add(new ArrayList<Integer>());
	} 
	
	public void addEdge(int src, int trg) {
		adj.get(src).add(trg);
	}
	
	public int numNodes() {
		return nodes.size();
	}
	
	public Node getNode(int src) {
		return nodes.get(src);
	}
	
	public ArrayList<Integer> getTargets(int src) {
		return adj.get(src);
	}
	
	public ArrayList<Integer> shortestPath(int src, int trg) {
		// TODO Depth-First Search
		return null;
	}
}