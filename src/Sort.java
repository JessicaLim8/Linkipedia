import java.util.Comparator;
//import java.util.Random;


//Quicksort function
public class Sort {

    public static void sort(Comparable[] arr) 
    {
        //StdRandom.shuffle(arr); FIX ARRAY SHUFFLE
        sort(arr, 0, arr.length - 1); 
    }

    private static void sort(Comparable[] arr, int lo, int hi) 
    {
        //Pre-condition: high marker should be greater than lower
        if (hi <= lo) return;

        //Partitions array for quicksort and returns partition location
        int j = partition(arr, lo, hi);

        //Recursively sorts both partitions
        sort(arr, lo, j - 1);
        sort(arr, j+1, hi);

    }

    private static int partition(Comparable[] a, int lo, int hi) 
    {
        int i = lo;
        int j = hi + 1; 

        while (true)
        {
            while (a[++i].compareTo(a[lo]) > 0)
                if (i == hi) 
                    break;

            while (a[lo].compareTo(a[--j]) > 0)
                if (j == lo)
                    break;

            if (i >= j)
                break;
            
            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }

    //Generic function that swaps two values of an array
    private static final <T> void exch(T[] a, int p1, int p2) {
        T t = a[p1];
        a[p1] = a[p2];
        a[p2] = t;
    }
    
    public static <T> void sort(T[] arr, Comparator<T> c) {
        //StdRandom.shuffle(arr); FIX ARRAY SHUFFLE
        sort2(arr, 0, arr.length - 1, c); 
    }

    private static <T> void sort2(T[] arr, int lo, int hi, Comparator<T> c) 
    {
        //Pre-condition: high marker should be greater than lower
        if (hi <= lo) return;

        //Partitions array for quicksort and returns partition location
        int j = partition2(arr, lo, hi, c);

        //Recursively sorts both partitions
        sort2(arr, lo, j - 1, c);
        sort2(arr, j+1, hi, c);

    }

    private static <T> int partition2(T[] a, int lo, int hi, Comparator<T> c) 
    {
        int i = lo;
        int j = hi + 1; 

        while (true)
        {
            while (c.compare(a[++i], a[lo]) > 0)
                if (i == hi) 
                    break;

            while (c.compare(a[lo], a[--j]) > 0)
                if (j == lo)
                    break;

            if (i >= j)
                break;
            
            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }
}