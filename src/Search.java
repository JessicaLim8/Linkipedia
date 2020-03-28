/*
LEXIGRAPHICAL SEARCH

INPUT
ArrayList of type T
SearchTerm of type T
Comparator
Integer n (how many terms)

RETURNS
position of hit (-1 if not found)
*/

import java.util.Comparator;
import java.util.ArrayList;

public class Search { 

    //Boilerplate binary search code with compareTo
    public static int binarySearch (Comparable [] arr, Comparable target) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if      (target.compareTo(arr[mid]) < 0)
                hi = mid - 1;
            else if (target.compareTo(arr[mid]) > 0) 
                lo = mid + 1;
            else return mid;
        }

        //If target not founds, returns -1
        return -1;
    }


    public static <T> int binarySearchAll (T[] arr, T target, Comparator<T> c) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if      (c.compareTo(target, arr[mid]) < 0)
                hi = mid - 1;
            else if (c.compareTo(target, arr[mid]) > 0) 
                lo = mid + 1;
            else return mid;
        }

        //If target not founds, returns -1
        return -1;
    }

    //added <T> in response to error: T cannot be resolved to a type
    public static <T> ArrayList<Integer> binarySearchAll(T[] arr, T target, int N, Comparator<T> c) {

    }

    

}