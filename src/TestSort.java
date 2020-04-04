import org.junit.*;
import static org.junit.Assert.*;

import java.util.Arrays;

public class TestSort {
    private Integer[] iarr;
    private String[] sarr;
    private Node[] narr;

    @Before
    public void setUp() {
        iarr = new Integer[]{5,6,7,9,3,4,8,1,0,2};
        sarr = new String[]{"g", "c", "b", "f", "a", "d", "e"};
        narr = new Node[]{
            new Node(0, "a"),
            new Node(0, "d"),
            new Node(0, "a"),
            new Node(0, "b"),
            new Node(0, "c"),
            new Node(0, "e"),
            new Node(0, "b")
        };
    }

    @After
    public void tearDown() {
        iarr = null;
        sarr = null;
        narr = null;
    }

    @Test
    public void testQuickSort() {
        // correct sorted order
        Integer[] correct_iarr = new Integer[]{0,1,2,3,4,5,6,7,8,9};
        String[] correct_sarr = new String[]{"a", "b", "c", "d", "e", "f", "g"};
        Node[] correct_narr = new Node[]{
            new Node(0, "a"),
            new Node(0, "a"),
            new Node(0, "b"),
            new Node(0, "b"),
            new Node(0, "c"),
            new Node(0, "d"),
            new Node(0, "e")
        };

        // sort arrays
        Sort.quickSort(iarr);
        Sort.quickSort(sarr);
        Sort.quickSort(narr);

        // test if sorted
        assertArrayEquals(correct_iarr, iarr);
        assertArrayEquals(correct_sarr, sarr);
        assertArrayEquals(correct_narr, narr);
    }
}
