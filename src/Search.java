import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

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
        //Creates an empty arraylist of size N with all values set to -1
        ArrayList<Integer> list = new ArrayList<Integer>(Collections.nCopies(N, -1));

        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target.compareTo(arr[mid]) < 0)
                hi = mid - 1;
            else if (target.compareTo(arr[mid]) > 0)
                lo = mid + 1;
            else { // target.compareTo(arr[mid]) == 0)
            	int count = 1;
                int tempmid = mid;

                //Moves to the first index value that equals the target
                while ((target.compareTo(arr[mid]) == 0) && (mid >= 0) && (count <= N)) {
                    list.add(mid--);
                    count++;
                }

                mid = tempmid;

                //Iterates arraylist position and mid until array is full of index values that match the target
                while ((target.compareTo(arr[mid]) == 0) && (mid < arr.length) && (count <= N)) {
                    list.add(mid++);
                    count++;
                }
            }
        }
        return list;
    }

    public static <T> ArrayList<Integer> binarySearchAll (T[] arr, T target, int N, Comparator<T> c) {
        //Creates an empty arraylist of size N with all values set to -1
        ArrayList<Integer> list = new ArrayList<Integer>();

        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (c.compare(target, arr[mid]) < 0)
                hi = mid - 1;
            else if (c.compare(target, arr[mid]) > 0)
                lo = mid + 1;
            else  { // target.compareTo(arr[mid]) == 0)
                int count = 1;
                int tempmid = mid;

                //Moves to the first index value that equals the target
                while ((c.compare(target, arr[mid]) == 0) && (mid >= 0) && (count <= N)) {
                    list.add(mid--);
                    count++;
                }

                mid = tempmid;

                // Iterates arraylist position and mid until array is full of index values that match the target
                while ((c.compare(target, arr[mid]) == 0) && (mid < arr.length) && (count <= N)) {

                    list.add(mid++);
                    count++;
                }
            }
        }
        return list;
    }
}
