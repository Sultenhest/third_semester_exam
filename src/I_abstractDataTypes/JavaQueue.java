package I_abstractDataTypes;

import java.util.LinkedList;
import java.util.Queue;

public class JavaQueue {
    public static void main( String[] args ) {
        char[] data = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I' };
        Queue<Character> q = new LinkedList<Character>();

        for( char c : data ) {
            q.add(c);
        }

        System.out.println( "Queue = " + q );

        while( !q.isEmpty() ) {
            System.out.println( q.remove() );
        }
    }
}
