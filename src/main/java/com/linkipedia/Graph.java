package com.linkipedia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Graph {
	private int N; // number of nodes
	private int E; // number of edges
	private Node[] nodes; // nodes, indexed by id
	public Node[] sorted_nodes; // nodes, sorted by article title
	private List<Integer>[] adj; // adjacency list

	public Graph(Node[] nodes) {
		this.nodes = nodes;
		this.N = nodes.length;
		this.E = 0;

		// copy Node object references to sorted_nodes
		this.sorted_nodes = new Node[N];
		for (int i = 0; i < N; i++)
			sorted_nodes[i] = this.nodes[i];
		Sort.quickSort(sorted_nodes);

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
		Sort.quickSort(sorted_nodes);

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
		Collections.shuffle(adj[n]);
		return adj[n];
	}

	public void addEdge(int src, int dst) {
		adj[src].add(dst);
		E++;
	}

	public ArrayList<Node> search(String title) {
		ArrayList<Integer> indices = Search.binarySearchAll(
			sorted_nodes,
			new Node(-1, title.toLowerCase()),
			100,
			new Node.SubstringComparator()
		);
		ArrayList<Node> results = new ArrayList<Node>();
		for (Integer i : indices)
			results.add(sorted_nodes[i]);
		return results;
	}

	public ArrayList<Node> shortestPath(Node src, Node dst) {
		boolean[] marked = new boolean[N];
        int[] edgeTo = new int[N];
		validNode(src);
		validNode(dst);

		Queue<Integer> q = new LinkedList<Integer>();
		marked[src.id()] = true;
		q.add(src.id());

		while (!q.isEmpty()) {
			int v = q.poll();
			for (int w : adj(v)) {
				if (w == dst.id()) {
					edgeTo[w] = v;
					marked[w] = true;

					ArrayList<Node> path = new ArrayList<Node>();
					int x = dst.id();
					for (; x != src.id(); x = edgeTo[x])
						path.add(0,nodes[x]);
					path.add(0,nodes[x]);
					return path;
				}
				if (!marked[w]) {
					edgeTo[w] = v;
					marked[w] = true;
					q.add(w);
				}
			}
		}

		return null;
	}

	public ArrayList<ArrayList<Node>> nShortestPaths(Node src, Node dst, int n) {
		ArrayList<ArrayList<Node>> paths = new ArrayList<ArrayList<Node>>();

		int count = 0;
		int repeats = 0;
		while (repeats < 3 && count < n) {
			ArrayList<Node> path = shortestPath(src, dst);
			if (pathExists(paths, path)) {
				repeats++;
			}
			else {
				repeats = 0;
				paths.add(path);
				count++;
			}
		}

		return paths;
	}

	private boolean pathExists(ArrayList<ArrayList<Node>> paths, ArrayList<Node> path) {
		for (ArrayList<Node> p : paths)
			if (p.equals(path))
				return true;
		return false;
	}

	private void validNode(Node n) {
		if (n.id() < 0 || n.id() >= N)
            throw new IllegalArgumentException("node id " + n + " is not between 0 and " + (N-1));
	}
}
