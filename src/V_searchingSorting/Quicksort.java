package V_searchingSorting;

import java.util.Arrays;
import java.util.Random;

public class Quicksort {
    private static int[] data = new int[ 10 ];

    public static void main(String[] args) {
        populateIntArray();

        System.out.println( "before: "+ Arrays.toString(data) );

        quickSort(data);

        System.out.println( "after:  " + Arrays.toString(data) );
    }

    private static void populateIntArray() {
        Random r = new Random();

        for ( int i = 0; i < data.length; i++ ) {
            data[ i ] = r.nextInt( 10000 ) + 1;
        }
    }

    private static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    private static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    private static int partition(int[] list, int first, int last) {
        int pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search

        while (high > low) {
            // Search forward from left
            while (low <= high && list[low] <= pivot)
                low++;

            // Search backward from right
            while (low <= high && list[high] > pivot)
                high--;

            // Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot)
            high--;

        // Swap pivot with list[high]
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }
        else {
            return first;
        }
    }
}
