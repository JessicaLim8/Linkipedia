package com.linkipedia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class TestSearch {
    private Integer[] iarr;
    private String[] sarr;
    private Node[] narr;

    @BeforeEach
    public void setUp() {
        iarr = new Integer[]{10,20,30,50,50,50,70,80,90};
        sarr = new String[]{"a", "aabb", "aabb", "aabb", "aabb", "c", "z"};
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

    @AfterEach
    public void tearDown() {
        iarr = null;
        sarr = null;
        narr = null;
    }

    @Test
    public void testBinarySearch() {
        // basic cases
        assertEquals(0, Search.binarySearch(iarr, 10));
        assertEquals(1, Search.binarySearch(iarr, 20));
        assertEquals(-1, Search.binarySearch(iarr, 100));
        assertEquals(5, Search.binarySearch(sarr, "c"));
        assertEquals(-1, Search.binarySearch(sarr, "xb3"));

        // multiple entries
        int i = Search.binarySearch(sarr, "aabb");
        assertTrue(i == 1 || i == 2 || i == 3);

        // exact node cases
        assertEquals(0, Search.binarySearch(narr, new Node(-1, "a")));
        assertEquals(6, Search.binarySearch(narr, new Node(-1, "z")));
        assertEquals(-1, Search.binarySearch(narr, new Node(-1, "xb3")));
    }

    @Test
    public void testBinarySearchAll() {
        assertEquals(3, Search.binarySearchAll(iarr, 50, 100).size());
        assertEquals(
            new HashSet<Integer>(Arrays.asList(new Integer[]{3,4,5})),
            new HashSet<Integer>(Search.binarySearchAll(iarr, 50, 100))
        );

        assertEquals(4, Search.binarySearchAll(sarr, "aabb", 100).size());
        assertEquals(
            new HashSet<Integer>(Arrays.asList(new Integer[]{1,2,3,4})),
            new HashSet<Integer>(Search.binarySearchAll(sarr, "aabb", 100))
        );

        assertEquals(2, Search.binarySearchAll(narr, new Node(-1, "aabb"), 100).size());
        assertEquals(
            new HashSet<Integer>(Arrays.asList(new Integer[]{1,2})),
            new HashSet<Integer>(Search.binarySearchAll(narr, new Node(-1, "aabb"), 100))
        );

        // substring comparator tests
        Comparator<Node> c = new Node.SubstringComparator();

        assertEquals(narr.length, Search.binarySearchAll(narr, new Node(-1, ""), 100, c).size());
        assertEquals(
            new HashSet<Integer>(Arrays.asList(new Integer[]{0,1,2,3,4,5,6})),
            new HashSet<Integer>(Search.binarySearchAll(narr, new Node(-1, ""), 100, c))
        );

        assertEquals(2, Search.binarySearchAll(narr, new Node(-1, "b"), 100, c).size());
        assertEquals(
            new HashSet<Integer>(Arrays.asList(new Integer[]{3,4})),
            new HashSet<Integer>(Search.binarySearchAll(narr, new Node(-1, "b"), 100, c))
        );

        assertEquals(0, Search.binarySearchAll(narr, new Node(-1, "f"), 100, c).size());
        assertEquals(
            new HashSet<Integer>(Arrays.asList(new Integer[]{})),
            new HashSet<Integer>(Search.binarySearchAll(narr, new Node(-1, "f"), 100, c))
        );

        assertEquals(3, Search.binarySearchAll(narr, new Node(-1, "a"), 100, c).size());
        assertEquals(
            new HashSet<Integer>(Arrays.asList(new Integer[]{0,1,2})),
            new HashSet<Integer>(Search.binarySearchAll(narr, new Node(-1, "a"), 100, c))
        );

        assertEquals(2, Search.binarySearchAll(narr, new Node(-1, "aabb"), 100, c).size());
        assertEquals(
            new HashSet<Integer>(Arrays.asList(new Integer[]{1,2})),
            new HashSet<Integer>(Search.binarySearchAll(narr, new Node(-1, "aabb"), 100, c))
        );

        assertEquals(1, Search.binarySearchAll(narr, new Node(-1, "bd"), 100, c).size());
        assertEquals(
            new HashSet<Integer>(Arrays.asList(new Integer[]{4})),
            new HashSet<Integer>(Search.binarySearchAll(narr, new Node(-1, "bd"), 100, c))
        );

        assertEquals(1, Search.binarySearchAll(narr, new Node(-1, "bdc"), 100, c).size());
        assertEquals(
            new HashSet<Integer>(Arrays.asList(new Integer[]{4})),
            new HashSet<Integer>(Search.binarySearchAll(narr, new Node(-1, "bdc"), 100, c))
        );
    }
}
