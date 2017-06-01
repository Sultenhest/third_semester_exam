package VI_generics;

public class GenericLinkedListTest {
    public static void main( String[] args ) {
        GenericNode<String> firstString = new GenericNode("First");
        GenericNode<String> secondString = new GenericNode("Second", firstString);
        GenericNode<String> thirdString = new GenericNode("Third", secondString);

        GenericLinkedList stringList = new GenericLinkedList(thirdString);
        GenericLinkedList intList = new GenericLinkedList();

        System.out.println( stringList.toString() );

        intList.add( new GenericNode(3) );
        intList.add( new GenericNode(7777) );

        stringList.add( new GenericNode("Zero"), 0 );

        System.out.println( intList );

        System.out.println( stringList );
    }
}
