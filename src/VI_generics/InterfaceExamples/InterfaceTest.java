package VI_generics.InterfaceExamples;

public class InterfaceTest {
    public static void main(String[] args) {
        InterfaceStringTest ist = new InterfaceStringTest();
        ist.print("Simon");

        String[] strArray = { "First", "Second", "Third" };
        ist.printList( strArray );

        System.out.println("- - - - - - - - - -");

        InterfaceIntegerTest iit = new InterfaceIntegerTest();
        iit.print(2);

        Integer[] intArray = { 10, 11, 12, 13 };
        iit.printList( intArray );

        System.out.println("- - - - - - - - - -");

        InterfacePersonTest ipt = new InterfacePersonTest();
        ipt.print( new Person("Simon", "Konstantyner") );

        Person[] personArray = { new Person("Daniel", "Liang"),
                                 new Person("Stuart", "Reges"),
                                 new Person("Marty", "Stepp") };
        ipt.printList( personArray );
    }
}
