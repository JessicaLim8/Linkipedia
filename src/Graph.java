import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Graph {
	private int N; // number of nodes
	private int E; // number of edges
	private Node[] nodes; // nodes, indexed by id
	private Node[] sorted_nodes; // nodes, sorted by article title
	private List<Integer>[] adj; // adjacency list

	public Graph(Node[] nodes) {
		this.nodes = nodes;
		this.N = nodes.length;
		this.E = 0;

		// copy Node object references to sorted_nodes
		this.sorted_nodes = new Node[N];
		for (int i = 0; i < N; i++)
			sorted_nodes[i] = this.nodes[i];
		Sort.sort(sorted_nodes);

		// initialize adj list
		adj = (ArrayList<Integer>[]) new ArrayList[N];
		for (int n = 0; n < N; n++)
			adj[n] = new ArrayList<Integer>();
	}

	public Graph(ArrayList<Node> nodes) {
		this.nodes = new Node[nodes.size()];
		this.N = nodes.size();
		this.E = 0;

		for (int i = 0; i < this.N; i++)
			this.nodes[i] = nodes.get(i);

		// copy Node object references to sorted_nodes
		this.sorted_nodes = new Node[N];
		for (int i = 0; i < N; i++)
			sorted_nodes[i] = this.nodes[i];
		Sort.sort(sorted_nodes);

		// initialize adj list
		adj = (ArrayList<Integer>[]) new ArrayList[N];
		for (int n = 0; n < N; n++)
			adj[n] = new ArrayList<Integer>();
	}

	public int N() {
		return N;
	}

	public int E() {
		return E;
	}

	public Node node(int n) {
		return nodes[n];
	}

	public Iterable<Integer> adj(int n) {
		return adj[n];
	}

	public void addEdge(int src, int dst) {
		adj[src].add(dst);
		E++;
	}

	public String toString() {
		// TODO
		return "TODO";
	}

	public ArrayList<Node> search(String title) {
		ArrayList<Integer> indices = Search.binarySearchAll(sorted_nodes, new Node(-1, title.toLowerCase()), 100, new Node.SubstringComparator());
		ArrayList<Node> results = new ArrayList<Node>();
		for (Integer i : indices)
			results.add(sorted_nodes[i]);
		return results;
	}

	// output paths do NOT include src and dst and are ordered from src to dst, n must be greater than or equal to 1
	public ArrayList<ArrayList<Node>> nShortestPaths(Node src, Node dst, int n) {
		ArrayList<ArrayList<Node>> paths = new ArrayList<ArrayList<Node>>();

		// base case
		if (src == dst)
			return paths;

		boolean[] marked = new boolean[N];
		int[] prev = new int[N];
		for (int i = 0; i < N; i++) {
			marked[i] = false;
			prev[i] = -1;
		}

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(src.id());

		int v;
		while (!queue.isEmpty()) {
			v = queue.poll();

			for (int w : this.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    prev[w] = v;
                    queue.add(w);
                }
                if (w == dst.id()) {
                	ArrayList<Node> path = new ArrayList<Node>();
                	int i = prev[dst.id()];
                	while (prev[i] != -1) {
                		path.add(0, nodes[i]);
                		i = prev[i];
                	}
                	paths.add(path);
                }
                if (paths.size() >= n)
                	return paths;
            }
		}

		return paths;
	}
}
