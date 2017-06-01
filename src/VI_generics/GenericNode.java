package VI_generics;

public class GenericNode<E> {
    private E data;
    private GenericNode next;

    public GenericNode( E data ) {
        setData( data );
        setNext( null );
    }

    public GenericNode( E data, GenericNode next ) {
        setData( data );
        setNext( next );
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public GenericNode getNext() {
        return next;
    }

    public void setNext(GenericNode next) {
        this.next = next;
    }
}