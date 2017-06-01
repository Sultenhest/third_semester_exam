package VI_generics;

public class GenericLinkedList {
    private GenericNode front;

    public GenericLinkedList() {
        setFront( null );
    }

    public GenericLinkedList( GenericNode front ) {
        setFront( front );
    }

    private GenericNode getFront() {
        return front;
    }

    private void setFront(GenericNode front) {
        this.front = front;
    }

    public int size() {
        int count = 0;
        GenericNode current = getFront();
        while (current != null) {
            current = current.getNext();
            count++;
        }
        return count;
    }

    public GenericNode getNodeAt(int index) {
        GenericNode current = getFront();

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current;
    }

    public void add( GenericNode node ) {
        if( getFront() == null ) {
            setFront( node );
        } else {
            GenericNode current = getFront();

            while( current.getNext() != null ) {
                current = current.getNext();
            }

            current.setNext( node );
        }
    }

    public void add( GenericNode node, int index ) {
        if (index == 0) {
            node.setNext( getFront() );
            setFront( node );
        } else {
            GenericNode current = getNodeAt(index - 1);

            node.setNext( current.getNext() );
            current.setNext( node );
        }
    }

    public void remove( int index ) {
        if( index == 0 ) {
            setFront( getFront().getNext() );
        } else {
            GenericNode current = getNodeAt(index - 1);
            current.setNext( current.getNext().getNext() );
        }
    }

    public String toString() {
        if( getFront() == null ) {
            return "[]";
        } else {
            String result = "[ " + getFront().getData();

            GenericNode current = getFront().getNext();

            while( current != null ) {
                result += ", " + current.getData();
                current = current.getNext();
            }

            result += " ]";

            return result;
        }
    }
}