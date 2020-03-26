

import java.util.ArrayList;

public class Sort {

    private static <T> void merge(ArrayList<T> a, ArrayList<T> aux, int lo, int mid, int hi)
    {
        //Pre-condition for merge
        //assert isSorted(a, lo, mid);    //Create isSorted function
        //assert isSorted(a, mid+1, hi);

        //Copies ArraList a into auxiliary array
        aux = new ArrayList<T>(a);

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) 
        {
            if (i > mid)
                a[k] = aux[j++];
            else if (j < hi)
                a[k] = aux[i++];
            else if (compareTo(aux[j], aux[i] < 0)) //Change to compareTo function
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }

        //Post-condition for merge
        //assert isSorted(a, lo, hi);
    }

    private static <T> void sort(ArrayList<T> a, ArrayList<T> aux, int lo, int hi)
    {
        if (hi <= lo)
            return; 
        int mid = lo + ((hi - lo) / 2);
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static <T> void sort(ArrayList<T> a)
    {
        ArrayList<T> aux = new ArrayList<T>();
        sort(a, aux, 0, a.size() - 1);
    }
}