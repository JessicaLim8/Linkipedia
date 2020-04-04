import org.junit.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

public class TestSearch {
    private Integer[] iarr;
    private String[] sarr;
    private Node[] narr;

    @Before
    public void setUp() {
        iarr = new Integer[]{10,20,30,50,50,50,70,80,90};
        sarr = new String[]{"a", "aabb", "aacc", "b", "bdc", "c", "z"};
        narr = new Node[]{
            new Node(0, "a"),
            new Node(1, "aabb"),
            new Node(2, "aabb"),
            new Node(3, "b"),
            new Node(4, "bdc"),
            new Node(5, "c"),
            new Node(6, "z")
        };
    }

    @After
    public void tearDown() {
        iarr = null;
        sarr = null;
        narr = null;
    }

    @Test
    public void testBinarySearch() {
        // basic cases
        assertEquals(Search.binarySearch(iarr, 10), 0);
        assertEquals(Search.binarySearch(iarr, 20), 1);
        assertEquals(Search.binarySearch(iarr, 100), -1);
        assertEquals(Search.binarySearch(sarr, "aabb"), 1);
        assertEquals(Search.binarySearch(sarr, "c"), 5);
        assertEquals(Search.binarySearch(sarr, "xb3"), -1);

        // exact node cases
        assertEquals(Search.binarySearch(narr, new Node(-1, "a")), 0);
        assertEquals(Search.binarySearch(narr, new Node(-1, "z")), 6);
        assertEquals(Search.binarySearch(narr, new Node(-1, "xb3")), -1);
    }

    @Test
    public void testBinarySearchAll() {
        assertEquals(
            Search.binarySearchAll(narr, new Node(-1, "b"), 100, new Node.SubstringComparator()).size(),
            2
        );
        assertEquals(
            Search.binarySearchAll(narr, new Node(-1, "f"), 100, new Node.SubstringComparator()).size(),
            0
        );
        assertEquals(
            new HashSet<Integer>(Search.binarySearchAll(narr, new Node(-1, "a"), 100, new Node.SubstringComparator())),
            new HashSet<Integer>(Arrays.asList(new Integer[]{0,1,2}))
        );
        assertEquals(
            new HashSet<Integer>(Search.binarySearchAll(narr, new Node(-1, "c"), 100, new Node.SubstringComparator())),
            new HashSet<Integer>(Arrays.asList(new Integer[]{5}))
        );
    }
}
