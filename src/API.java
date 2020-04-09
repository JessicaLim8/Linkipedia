import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;

public class API {
	
    public static void main(String[] args) {
        // parse data
        System.out.println("... parsing data ....");
        Instant start = Instant.now();
        ArrayList<Node> nodes = DataParser.parseNodes("data/wiki-topcats-page-names.txt", "data/wiki-topcats-categories.txt");
        // ArrayList<Pair<String, ArrayList<Integer>>> categories = DataParser.parseCategories("data/wiki-topcats-categories.txt");
        ArrayList<Integer[]> connnections = DataParser.parseConnections("data/wiki-topcats.txt");
        System.out.println("Time taken: "+ Duration.between(start, Instant.now()).toMillis() +" milliseconds");

        // build graph
        System.out.println("... building graph ....");
        start = Instant.now();
        Graph graph = new Graph(nodes);
        for (Integer[] pairs : connnections)
            graph.addEdge(pairs[0], pairs[1]);
        System.out.println("Time taken: "+ Duration.between(start, Instant.now()).toMillis() +" milliseconds");

        // search
        System.out.println(".... searching graph ....");
        start = Instant.now();
        Node a = search(graph, "Kleroterion");
        Node b = search(graph, "Bobby Kerr");
        System.out.println("Time taken: "+ Duration.between(start, Instant.now()).toMillis() +" milliseconds");

        // path
        System.out.println(".... finding single path ....");
        start = Instant.now();
        path(graph, a, b);
        System.out.println("Time taken: "+ Duration.between(start, Instant.now()).toMillis() +" milliseconds");

        // paths
        System.out.println(".... finding multiple paths ....");
        start = Instant.now();
        paths(graph, a, b, 5);
        System.out.println("Time taken: "+ Duration.between(start, Instant.now()).toMillis() +" milliseconds");
    }
    
    public static Graph createGraph() {
    	// parse data
    	System.out.println("... parsing data ....");
        ArrayList<Node> nodes = DataParser.parseNodes("data/wiki-topcats-page-names.txt", "data/wiki-topcats-categories.txt");
        // ArrayList<Pair<String, ArrayList<Integer>>> categories = DataParser.parseCategories("data/wiki-topcats-categories.txt");
        ArrayList<Integer[]> connnections = DataParser.parseConnections("data/wiki-topcats.txt");
        
        //build graph
        System.out.println("... building graph ....");
        Graph graph = new Graph(nodes);
        for (Integer[] pairs : connnections)
            graph.addEdge(pairs[0], pairs[1]);
        
        return graph;
    }

    public static ArrayList<Node> searchNode(Graph graph, String title, int max) {
        ArrayList<Node> results = graph.search(title);
        for (Node node : results)
        /*for (Node node : results)
            System.out.println(node.title());
        System.out.println();*/
        if (results.size() > max) {
            results = new ArrayList<Node>(results.subList(0, max));
        }
        return results;
    }

    public static ArrayList<Node> findPath(Graph graph, Node src, Node dst) {
        ArrayList<Node> path = graph.shortestPath(src, dst);
        /*
        for (Node node : path)
            System.out.print(node.title() + " -> ");
        System.out.println();
        return results.get(0);
        */
        return path;
    }

    public static ArrayList<ArrayList<Node>> findMulPaths(Graph graph, Node src, Node dst, int n) {
        ArrayList<ArrayList<Node>> paths = graph.nShortestPaths(src, dst, n);
        /*
        for (ArrayList<Node> path : paths) {
            for (Node node : path)
                System.out.print(node.title() + " -> ");
            System.out.println();
        }
        System.out.println();
        */
        return paths;
    }
    
    public static ArrayList<String> pathToString(Graph graph, Node src, Node dst, int x) {
    	ArrayList<ArrayList<Node>> pathsList = findMulPaths(graph, src, dst, x);
    	ArrayList<String> stringPath = new ArrayList<String>();
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
        return stringPath;
    }

    public static Node search(Graph graph, String title) {
        ArrayList<Node> results = graph.search(title);
        for (Node node : results)
            System.out.println(node.title());
        System.out.println();
        return results.get(0);
    }

    public static void path(Graph graph, Node src, Node dst) {
        ArrayList<Node> path = graph.shortestPath(src, dst);
        for (Node node : path)
            System.out.print(node.title() + " -> ");
        System.out.println();
        System.out.println();
    }

    public static void paths(Graph graph, Node src, Node dst, int n) {
        ArrayList<ArrayList<Node>> paths = graph.nShortestPaths(src, dst, n);

        for (ArrayList<Node> path : paths) {
            for (Node node : path)
                System.out.print(node.title() + " -> ");
            System.out.println();
        }
        System.out.println();
    }
}
