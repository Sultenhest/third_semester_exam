package V_searchingSorting;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
    private static int[] data = new int[ 50 ];

    public static void main( String[] args ) {
        populateIntArray();

        //Pick a random key
        int key = data[ new Random().nextInt( data.length ) ];

        //Sort array
        Arrays.sort(data);

        //Looking for
        System.out.println( "Looking for: " + key );

        //Search
        System.out.println( binarySearch( key ) );
    }

    private static void populateIntArray() {
        Random r = new Random();

        for ( int i = 0; i < data.length; i++ ) {
            data[ i ] = r.nextInt( data.length * 10 ) + 1;
        }
    }

    private static void print( int startPos, int endPos ) {
        for ( int i = startPos; i < endPos; i++ ) {
            System.out.print( data[i] + ", " );
        }

        System.out.println();
    }

    private static boolean binarySearch( int key ) {
        System.out.print( "Array before search init: " );
        print( 0, data.length );

        int low = 0;
        int high = data.length - 1;

        while ( high >= low ) {
            int searchField = high - low;

            System.out.print( "Search array has length " + searchField + ": " );
            print( low, high );

            int middle = ( low + high ) / 2;
            if ( data[ middle ] == key ) {
                if( searchField > 1 ) {
                    System.out.println("Found " + key + " in the middle");
                }
                return true;
            }
            if ( data[ middle ] < key ) {
                low = middle + 1;
            }
            if ( data[ middle ] > key ) {
                high = middle - 1;
            }
        }

        return false;
    }
}
