// Java implementation of QuickSort
package util;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class quicksort {
	
	public static void quicksortalgo(List<Double> list, List<Integer> index, int left, int right) {
	    int q;
	    if (right > left) {
	        q = partition(list, index, left, right);
	        // after ‘partition’
	        // list[left..q-1] ≤ list[q] ≤ list[q+1..right]
	        quicksortalgo(list, index, left, q - 1);
	        quicksortalgo(list, index, q + 1, right);
	    }
	}

	static int partition(List<Double> list, List<Integer> index, int left, int right) {
	    double P = list.get(left);
	    int i = left;
	    int j = right + 1;
	    for (;;) { // infinite for-loop, break to exit
	        while (list.get(++i) < P)
	            if (i >= right)
	                break;
	        // Now, list[i]≥P
	        while (list.get(--j) > P)
	            if (j <= left)
	                break;
	        // Now, list[j]≤P
	        if (i >= j)
	            break; // break the for-loop
	        else {
	            // swap(list[i],list[j]);
	            Collections.swap(list, i, j);
	        	Collections.swap(index, i, j);
	        }
	    }
	    if (j == left)
	        return j;
	    // swap (list[left],list[j]);
	    Collections.swap(list, left, j);
	    Collections.swap(index, left, j);
	    return j;
	}
}