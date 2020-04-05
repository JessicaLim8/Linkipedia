import java.util.ArrayList;

public class API {
    public static void main(String[] args) {
        // parse data
        System.out.println("... loading graph ....");
        ArrayList<Node> nodes = DataParser.parseNodes("data/wiki-topcats-page-names.txt", "data/wiki-topcats-categories.txt");
        // ArrayList<Pair<String, ArrayList<Integer>>> categories = DataParser.parseCategories("data/wiki-topcats-categories.txt");
        ArrayList<Integer[]> connnections = DataParser.parseConnections("data/wiki-topcats.txt");

        // build graph
        Graph graph = new Graph(nodes);
        for (Integer[] pairs : connnections)
            graph.addEdge(pairs[0], pairs[1]);
        
        // search
        System.out.println(".... searching graph ....");
        Node a = search(graph, "Kleroterion");
        Node b = search(graph, "Bobby Kerr");
        
        // paths
        System.out.println(".... finding paths ....");
        path(graph, a, b);
        // paths(graph, a, v, 5);
    }

    private static void testWikiTopcats() {
        
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
