package I_abstractDataTypes;

import java.util.Stack;

public class JavaStack {
    public static void main( String[] args ) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        Stack<Character> s = new Stack<Character>();

        for( char c : data ) {
            s.add(c);
        }

        System.out.println( "JavaStack = " + s );

        while( !s.isEmpty() ) {
            System.out.println( s.pop() );
        }
    }
}
