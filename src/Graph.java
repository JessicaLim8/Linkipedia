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
			sorted_nodes[i] = nodes[i];
		// TODO sort sorted_nodes by "title"

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
		return "TODO";
	}

	// output paths do NOT include src and dst and are ordered from src to dst, n must be greater than or equal to 1
	public ArrayList<ArrayList<Integer>> nShortestPaths(int src, int dst, int n) throws Exception {
		ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();

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
		queue.add(src);

		int v;
		while (!queue.isEmpty()) {
			v = queue.poll();

			for (int w : this.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    prev[w] = v;
                    queue.add(w);
                }
                if (w == dst) {
                	ArrayList<Integer> path = new ArrayList<Integer>();
                	int i = prev[dst];
                	while (prev[i] != -1) {
                		path.add(i,1);
                		i = prev[i];
                	}
                	paths.add(path);
                }
                if (paths.size() >= n)
                	return paths;;
            }
		}

		return paths;
	}
}
