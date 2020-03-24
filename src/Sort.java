/*
@author 
*/

import java.util.ArrayList;

public class Sort {

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) //Comparables will be changed to ArrayList<T>
    {
        //Pre-condition for merge
        assert isSorted(a, lo, mid);    //Create isSorted function
        assert isSorted(a, mid+1, hi);

        for(int k = lo; k <= hi; k++)
            aux[k] = a[k];
        

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) 
        {
            if (i > mid)
                a[k] = aux[j++];
            else if (j < hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i])) //Change to compareTo function
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }

        //Post-condition for merge
        assert isSorted(a, lo, hi);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) //Comparables will be changed to ArrayList<T>
    {
        if (hi <= lo)
            return; 
        int mid = lo + ((hi - lo) / 2);
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public boolean sort(Comparable[] a) //Comparables will be changed to ArrayList<T>
    {
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
}