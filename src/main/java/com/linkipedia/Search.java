package com.linkipedia;

import java.util.Comparator;
import java.util.ArrayList;

public class Search {
    public static int binarySearch(Comparable[] arr, Comparable target) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target.compareTo(arr[mid]) < 0)
                hi = mid - 1;
            else if (target.compareTo(arr[mid]) > 0)
                lo = mid + 1;
            else // (target.compareTo(arr[mid]) == 0)
                return mid;
        }

        // If target not founds, returns -1
        return -1;
    }

    public static <T> int binarySearch(T[] arr, T target, Comparator<T> c) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (c.compare(target, arr[mid]) < 0)
                hi = mid - 1;
            else if (c.compare(target, arr[mid]) > 0)
                lo = mid + 1;
            else // (c.compareTo(target, arr[mid]) == 0)
                return mid;
        }

        // If target not founds, returns -1
        return -1;
    }

    public static ArrayList<Integer> binarySearchAll(Comparable[] arr, Comparable target, int N) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target.compareTo(arr[mid]) < 0)
                hi = mid - 1;
            else if (target.compareTo(arr[mid]) > 0)
                lo = mid + 1;
            else { // target.compareTo(arr[mid]) == 0)
                // add first target found to list
                int count = 1;
                list.add(mid);

                // preserve mid
                int tempmid = mid - 1;

                // adds all targets found to the left of mid
                while ((count < N) && (tempmid >= 0) && (target.compareTo(arr[tempmid]) == 0)) {
                    list.add(tempmid--);
                    count++;
                }

                // restore mid to temp var
                tempmid = mid + 1;

                // adds all targets found to the right of mid
                while ((count < N) && (tempmid < arr.length) && (target.compareTo(arr[tempmid]) == 0)) {
                    list.add(tempmid++);
                    count++;
                }
                break;
            }
        }
        return list;
    }

    public static <T> ArrayList<Integer> binarySearchAll (T[] arr, T target, int N, Comparator<T> c) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (c.compare(target, arr[mid]) < 0)
                hi = mid - 1;
            else if (c.compare(target, arr[mid]) > 0)
                lo = mid + 1;
            else { // c.compare(target, arr[mid]) == 0)
                // go to leftmost equivalent node
                while (mid >= 0 && c.compare(target, arr[mid]) == 0)
                    mid--;
                mid++;

                // add equivalent nodes
                int count = 0;
                while (count < N && mid < arr.length && c.compare(target, arr[mid]) == 0) {
                    list.add(mid++);
                    count++;
                }
                break;
            }
        }
        return list;
    }
}
