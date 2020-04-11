package com.linkipedia;

import java.util.Comparator;

public class Sort {
    public static void quickSort(Comparable[] arr) {
        //StdRandom.shuffle(arr); FIX ARRAY SHUFFLE
        quickSort(arr, 0, arr.length - 1);
    }
    private static void quickSort(Comparable[] arr, int lo, int hi) {
        final int CUTOFF = 10;

        //Pre-condition: high marker should be greater than lower
        if (hi <= lo) { // + CUTOFF - 1 (add after lo)
            //insertionSort(arr, lo, hi);
            return;
        }

        //Partitions array for quicksort and returns partition location
        int j = partition(arr, lo, hi);

        //Recursively sorts both partitions
        quickSort(arr, lo, j - 1);
        quickSort(arr, j+1, hi);
    }
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while (true) {
            while (a[++i].compareTo(a[lo]) < 0)
                if (i == hi)
                    break;

            while (a[lo].compareTo(a[--j]) < 0)
                if (j == lo)
                    break;

            if (i >= j)
                break;

            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }

    public static <T> void quickSort(T[] arr, Comparator<T> c) {
        //StdRandom.shuffle(arr); FIX ARRAY SHUFFLE
        quickSort(arr, 0, arr.length - 1, c);
    }
    private static <T> void quickSort(T[] arr, int lo, int hi, Comparator<T> c) {
        //Pre-condition: high marker should be greater than lower
        if (hi <= lo) return;

        //Partitions array for quicksort and returns partition location
        int j = partition(arr, lo, hi, c);

        //Recursively sorts both partitions
        quickSort(arr, lo, j - 1, c);
        quickSort(arr, j+1, hi, c);

    }
    private static <T> int partition(T[] a, int lo, int hi, Comparator<T> c) {
        int i = lo;
        int j = hi + 1;

        while (true) {
            while (c.compare(a[++i], a[lo]) < 0)
                if (i == hi)
                    break;

            while (c.compare(a[lo], a[--j]) < 0)
                if (j == lo)
                    break;

            if (i >= j)
                break;

            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        int N = hi - lo;
        for (int i = lo; i < N; i++)
            for (int j = i; j > 0; j--)
                if (a[j].compareTo(a[j-1]) < 0)
                    exch(a, j, j-1);
                else break;
    }

    private static <T> void insertionSort(T[] a, int lo, int hi, Comparator<T> c) {
        int N = hi - lo;
        for (int i = lo; i < N; i++)
            for (int j = i; j > 0; j--)
                if (c.compare(a[j], a[j-1]) < 0)
                    exch(a, j, j-1);
                else break;
    }

    // generic function that swaps two values of an array
    private static final <T> void exch(T[] a, int p1, int p2) {
        T t = a[p1];
        a[p1] = a[p2];
        a[p2] = t;
    }
}
