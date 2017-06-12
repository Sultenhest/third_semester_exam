package VI_generics;

import java.util.ArrayList;

public class GenericQueue<E> {
    private ArrayList<E> queue = new ArrayList<E>();

    //Insert an element into the queue
    public boolean offer( E o ) {
        return queue.add( o );
    }

    //Retrieves and removes the head of this queue, or null if this queue is empty
    public E poll() {
        E o = queue.get( 0 );

        removeFirst();

        return o;
    }

    //Retrieves and removes the head of this queue, or throws an exception if this queue is empty
    public E remove() {
        E o = queue.get( 0 );

        if ( o == null ) {
            throw new NullPointerException();
        }

        removeFirst();

        return o;
    }

    //Retrieves but does not remove, the head of this queue, returning null if this queue is empty
    public E peek() {
        return queue.get( 0 );
    }

    //Retrieves but does not remove, the head of this queue, throws an exception if this queue is empty
    public E element() {
        E o = queue.get( 0 );

        if ( o == null ) {
            throw new NullPointerException();
        }

        return o;
    }

    //Move each item one to the left
    private void removeFirst() {
        queue.remove( 0 );
    }

    //Print
    public void print() {
        String str = "";

        for(E o : queue) {
            str += o + ", ";
        }

        System.out.println( str.substring( 0, str.length() - 2 ) );
    }

    //Size
    public int size() {
        return queue.size();
    }

    @Override
    public String toString() {
        return queue.toString();
    }

    public static <E> void printArays( GenericQueue<E> l1 ) {
        for(int i = 0; i < l1.size(); i++) {
            System.out.println( l1.poll() );
        }
    }

    public static void main(String[] args) {
        //String queue
        GenericQueue<String> queue1 = new GenericQueue();

        queue1.offer("a");
        queue1.offer("b");
        queue1.offer("c");
        queue1.offer("d");
        queue1.offer("e");
        queue1.offer("a");
        queue1.offer("f");
        queue1.offer("g");

        printArays( queue1 );

        System.out.println( "- - - - - - - - - - -" );

        //Integer queue
        GenericQueue<Integer> queue2 = new GenericQueue();

        queue2.offer(1);
        queue2.offer(43453);
        queue2.offer(7);
        queue2.offer(2);
        queue2.offer(4);

        printArays( queue2 );





    }
}
