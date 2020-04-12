package com.linkipedia;

import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;

public class API {
	public static String s1 = "Kleroterion";
	public static String s2 = "Bobby Kerr";
	public static int numSearch = 5;
	public static int numPath = 5;


	// arguments can be included in the form of
	// source String, destination String, # search results, # path results
    public static void main(String[] args) {
    	// get information from args
    	if (args.length == 4) {
    		s1 = args[0];
    		s2 = args[1];
    		numSearch = Integer.parseInt(args[2]);
    		numPath = Integer.parseInt(args[3]);
    	}

        // parse data
        Graph graph = createGraph();

        // search for nodes matching string
        System.out.println(".... Searching Graph ....");
        Instant start = Instant.now();
        ArrayList<Node>  nodes1 = searchNode(graph, s1, numSearch);
        ArrayList<Node>  nodes2 = searchNode(graph, s2, numSearch);
        System.out.println("Time taken: "+ Duration.between(start, Instant.now()).toMillis() +" milliseconds \n");

        // print search results
        System.out.println("Results for Searching " + s1);
        for (Node node : nodes1)
        	System.out.println(node.title());
        System.out.println();
        System.out.println("Results for Searching " + s2);
        for (Node node : nodes2)
        	System.out.println(node.title());
        System.out.println();

        // paths using the first instance of any search result
        System.out.println(".... Finding Multiple Paths ....");
        start = Instant.now();
        Node n1 = nodes1.size() > 0 ? nodes1.get(0) : null;
        Node n2 = nodes2.size() > 0 ? nodes2.get(0) : null;
        ArrayList<String> stringPaths = pathToString(graph, n1, n2, 5);
        System.out.println("Time taken: "+ Duration.between(start, Instant.now()).toMillis() +" milliseconds \n");

        // print path results
        System.out.println("Results for Paths between " +
        		(n1 != null ? n1.title() : "N/A") + " and " +
        		(n2 != null ? n2.title() : "N/A"));
        for (String s : stringPaths)
        	System.out.println(s);

    }

    // create graph
    public static Graph createGraph() {
        // parse data
        Instant start = Instant.now();
    	System.out.println(".... Parsing Nodes ....");
        ArrayList<Node> nodes = DataParser.parseNodes("classpath:data/wiki-topcats-page-names.txt", "classpath:data/wiki-topcats-categories.txt");
        System.out.println("Time taken: "+ Duration.between(start, Instant.now()).toMillis() +" milliseconds \n");

        // build graph
        start = Instant.now();
        System.out.println(".... Parsing Connections/Building Graph ....");
        Graph graph = new Graph(nodes);
        DataParser.parseConnections("classpath:data/wiki-topcats.txt", graph);
        System.out.println("Time taken: "+ Duration.between(start, Instant.now()).toMillis() +" milliseconds \n");

        return graph;
    }

    // find list of nodes that match specific string
    public static ArrayList<Node> searchNode(Graph graph, String title, int max) {
        ArrayList<Node> results = graph.search(title);
        if (results.size() > max)
            results = new ArrayList<Node>(results.subList(0, max));
        return results;
    }

    // find single node from string
    public static Node searchSingleNode(Graph graph, String title) {
        ArrayList<Node> results = graph.search(title);
        return results.size() > 0 ? results.get(0) : null;
    }

    // find path between two nodes
    public static ArrayList<Node> findPath(Graph graph, Node src, Node dst) {
    	if (src == null || dst == null) return null;
        ArrayList<Node> path = graph.shortestPath(src, dst);
        return path;
    }

    // find n paths between two nodes
    public static ArrayList<ArrayList<Node>> findMulPaths(Graph graph, Node src, Node dst, int n) {
    	if (src == null || dst == null) return null;
        ArrayList<ArrayList<Node>> paths = graph.nShortestPaths(src, dst, n);
        return paths;
    }

    // turn path to string
    public static ArrayList<String> pathToString(Graph graph, Node src, Node dst, int x) {
    	ArrayList<ArrayList<Node>> pathsList = findMulPaths(graph, src, dst, x);
    	ArrayList<String> stringPath = new ArrayList<String>();
    	if (pathsList != null) {
	    	for (ArrayList<Node> path: pathsList) {
	    		String sPath = "";
	    		for (int i = 0; i < path.size(); i++) {
	    			sPath += path.get(i).title();
	    			if (i != path.size() - 1) {
	    				sPath += " -> ";
	    			}
	    		}
	    		stringPath.add(sPath);

	    	}
    	}
        return stringPath;
    }
}
