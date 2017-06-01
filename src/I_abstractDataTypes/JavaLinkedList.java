package I_abstractDataTypes;

import java.util.Iterator;
import java.util.LinkedList;

public class JavaLinkedList {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.add( "First" );
        linkedList.add( "Second" );
        linkedList.add( "Third" );

        System.out.println( linkedList );

        Iterator<String> iterator = linkedList.iterator();

        while( iterator.hasNext() ){
            System.out.println( iterator.next() );
        }
    }
}
