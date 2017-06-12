package I_recursion;

public class Recursion<T> {
    public static void main( String[] args ) {
        System.out.println( writeNumbers( 5 ) );

        int[] list = { 1, 2, 3, 4, 5 };
        System.out.println( sum( list ) );
    }

    public static String writeNumbers( int n )  {
        if ( n < 1 ) {
            throw new IllegalArgumentException( n + " is obviously less than 0" );
        } else if ( n == 1 ) {
            return "1";
        } else {
            return writeNumbers( n - 1 ) + ", " + n;
        }
    }

    public static int sum( int[] list ) {
        return sum( list, 0 );
    }

    private static int sum( int[] list, int index ) {
        if( index == list.length ) {
            return 0;
        } else {
            return list[ index ] + sum( list, index + 1 );
        }
    }
}
