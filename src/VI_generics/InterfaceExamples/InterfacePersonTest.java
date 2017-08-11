package VI_generics.InterfaceExamples;

public class InterfacePersonTest implements InterfaceExample<Person> {
    public InterfacePersonTest(){}

    @Override
    public void print( Person person ) {
        System.out.println( "The Person: " + person );
    }

    @Override
    public void printList( Person[] personArray ) {
        for( Person person : personArray ) {
            System.out.println( person );
        }
    }
}
