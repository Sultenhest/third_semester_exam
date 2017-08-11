package VI_generics.InterfaceExamples;

public class InterfaceIntegerTest implements InterfaceExample<Integer> {
    public InterfaceIntegerTest(){}

    @Override
    public void print( Integer n ) {
        System.out.println( "The Integer: " + n );
    }

    @Override
    public void printList( Integer[] intArray ) {
        for( Integer n : intArray ) {
            System.out.println( n );
        }
    }
}
