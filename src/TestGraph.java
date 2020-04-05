import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestGraph {
    private Graph g1;
    private Graph g2;

    @Before
    public void setUp() {
        g1 = new Graph(new Node[]{
            new Node(0, "alpha"),
            new Node(1, "bravo"),
            new Node(2, "charlie"),
            new Node(3, "delta"),
        });
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 0);
        g1.addEdge(1, 3);
        g1.addEdge(0, 3);

        g2 = new Graph(new Node[]{
            new Node(0, "toronto"),
            new Node(1, "ottawa"),
            new Node(2, "quebec city"),
            new Node(3, "montreal"),
            new Node(4, "vancouver"),
            new Node(5, "edmonton"),
            new Node(6, "winnipeg"),
            new Node(7, "calgary"),
            new Node(8, "victoria"),
            new Node(9, "hamilton"),
        });
        g2.addEdge(0, 1);
        g2.addEdge(1, 0);
        g2.addEdge(0, 9);
        g2.addEdge(9, 0);
        g2.addEdge(0, 6);
        g2.addEdge(6, 0);
        g2.addEdge(1, 6);
        g2.addEdge(6, 1);
        g2.addEdge(1, 3);
        g2.addEdge(3, 1);
        g2.addEdge(3, 2);
        g2.addEdge(2, 3);
        g2.addEdge(6, 7);
        g2.addEdge(7, 6);
        g2.addEdge(6, 5);
        g2.addEdge(5, 6);
        g2.addEdge(7, 5);
        g2.addEdge(5, 7);
        g2.addEdge(7, 4);
        g2.addEdge(4, 7);
        g2.addEdge(5, 4);
        g2.addEdge(4, 5);
        g2.addEdge(4, 8);
        g2.addEdge(8, 4);
    }

    @After
    public void tearDown() {
        g1 = null;
        g2 = null;
    }

    @Test
    public void testSearch() {
        assertEquals(0, g1.search("title").size());
        assertEquals(0, g1.search("pha").size());

        assertEquals("alpha", g1.search("alp").get(0).title());
        assertEquals("charlie", g1.search("charlie").get(0).title());

        assertEquals(2, g2.search("v").size());
        assertEquals("victoria", g2.search("vi").get(0).title());
        assertEquals("vancouver", g2.search("va").get(0).title());
    }

    @Test
    public void testShortestPath() {
        ArrayList<Node> path = g1.shortestPath(new Node(0, "alpha"), new Node(2, "charlie"));
        for (Node node : path)
            System.out.print(node.title() + " -> ");
        System.out.println();
        System.out.println();

        path = g2.shortestPath(new Node(2, "quebec city"), new Node(9, "hamilton"));
        for (Node node : path)
            System.out.print(node.title() + " -> ");
        System.out.println();
        System.out.println();
    }
}
