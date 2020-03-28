import java.util.ArrayList;

public class API {
    public static void main(String args[]) {
        /*
        ArrayList<Node> nodes = DataParser.parseNodes("data/names.txt");
        ArrayList<Integer[]> connections = DataParser.parseConnections("data/main.txt");
        ArrayList<Pair<String, ArrayList<Integer>>> categories = DataParser.parseCategories("data/categories.txt");
        */
    	
        ArrayList<Node> nodes = DataParser.parseNodes("data/wiki-topcats-page-names.txt", "data/wiki-topcats-categories.txt");
        ArrayList<Pair<String, ArrayList<Integer>>> categories = DataParser.parseCategories("data/wiki-topcats-categories.txt");
        ArrayList<Integer[]> connnections = DataParser.parseConnections("data/wiki-topcats.txt");

        
        Graph graph = new Graph(nodes);
        for (Integer[] pairs : connnections) {
            graph.addEdge(pairs[0], pairs[1]);
        }
        
        // tests
        Node a = search(graph, "Lebron James");
        Node b = search(graph, "Toronto Raptors");
        path(graph, a, b, 10);

    }
    

    public static Node search(Graph graph, String title) {
        ArrayList<Node> results = graph.search(title);
        for (Node node : results)
            System.out.println(node.title());
        return results.get(0);
    }

    public static void path(Graph graph, Node src, Node dst, int n) {
        ArrayList<ArrayList<Node>> paths = graph.nShortestPaths(src, dst, n);

        for (ArrayList<Node> path : paths) {
            System.out.print(src.title() + " -> ");
            for (Node node : path)
                System.out.println(node.title());
            System.out.println(dst.title());
        }
    }

}
