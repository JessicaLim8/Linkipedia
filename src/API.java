import java.util.ArrayList;

public class API {
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

    public static void main() {
        Node[] nodes = DataParser.parseNodes("data/wiki-topcats-page-names.txt", "data/wiki-topcats-categories");
        ArrayList<Pair<Integer, Integer>> connnections = DataParser.parseConnections("wiki-topcats.txt");

        Graph graph = new Graph(nodes);
        for (Pair<Integer, Integer> p : connnections)
            graph.addEdge(p.getValue0(), p.getValue1());

        // tests
        Node a = search(graph, "Lebron James");
        Node b = search(graph, "Toronto Raptors");
        path(graph, a, b, 10);
    }
}
