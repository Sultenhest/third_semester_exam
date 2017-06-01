package V_searchingSorting;

public class BigO {
    public static void main(String[] args) {
        System.out.println( fibRecursive(7) );
        System.out.println( improvedFib(7) );
    }

    public static int fibRecursive( int n ) {
        if( n <= 1 ) {
            return n;
        } else {
            return fibRecursive( n - 2 ) + fibRecursive( n - 1 );
        }
    }

    public static int improvedFib( int n ) {
        int f0 = 0;
        int f1 = 1;
        int f2 = 1;

        if( n == 0 ) {
            return f0;
        } else if( n <= 2 ) {
            return f1;
        } else {
            for( int i = 3; i <= n; i++ ) {
                f0 = f1;
                f1 = f2;
                f2 = f0 + f1;
            }

            return f2;
        }
    }
}
