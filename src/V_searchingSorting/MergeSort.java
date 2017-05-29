package V_searchingSorting;

import java.util.Random;

public class MergeSort {
    private static int[] data = new int[ 1000 ];

    public static void main(String[] args) {
        populateIntArray();

        mergeSort( data );

        for ( int i = 0; i < data.length; i++ ) {
            System.out.print( data[ i ] + " " );
        }

        System.out.println( "\nArray is sorted: " + isSorted() );
    }

    private static void populateIntArray() {
        Random r = new Random();

        for ( int i = 0; i < data.length; i++ ) {
            data[ i ] = r.nextInt( 10000 ) + 1;
        }
    }

    private static void mergeSort( int[] list ) {
        if ( list.length > 1 ) {
            //Merge sort the first half
            int[] firstHalf = new int[ list.length / 2 ];
            System.arraycopy( list, 0, firstHalf, 0, list.length / 2 );
            mergeSort( firstHalf );

            //Merge sort the second half
            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[ secondHalfLength ];
            System.arraycopy( list, list.length / 2, secondHalf, 0, secondHalfLength );
            mergeSort( secondHalf );

            //Merge firstHalf with secondHalf into list
            merge( firstHalf, secondHalf, list );
        }
    }

    private static void merge( int[] list1, int[] list2, int[] temp ) {
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;

        while ( current1 < list1.length && current2 < list2.length ) {
            if ( list1[ current1 ] < list2[ current2 ] ) {
                temp[ current3++ ] = list1[ current1++ ];
            } else {
                temp[ current3++ ] = list2[ current2++ ];
            }
        }

        while ( current1 < list1.length ) {
            temp[ current3++ ] = list1[ current1++ ];
        }

        while ( current2 < list2.length ) {
            temp[ current3++ ] = list2[ current2++ ];
        }
    }

    private static boolean isSorted() {
        for ( int i = 0; i < data.length - 1; i++ ) {
            if ( data[ i ] > data[ i + 1 ] ) {
                return false;
            }
        }

        return true;
    }
}
