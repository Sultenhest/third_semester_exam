package VI_generics.InterfaceExamples;

public class InterfaceStringTest implements InterfaceExample<String> {
    public InterfaceStringTest(){}

    @Override
    public void print( String str ) {
        System.out.println( "The String: " + str );
    }

    @Override
    public void printList( String[] strArray ) {
        for( String str : strArray ) {
            System.out.println( str );
        }
    }
}
