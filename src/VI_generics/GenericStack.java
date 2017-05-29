package VI_generics;

public class GenericStack<E> {
    private int initialLength = 5;
    private int firstEmptySpot = 0;

    private E[] stack = (E[]) new Object[ initialLength ];

    public int getSize() {
        return firstEmptySpot;
    }

    public E peek() {
        if( !isEmpty() ) {
            return stack[ firstEmptySpot - 1 ];
        } else {
            return null;
        }
    }

    public void push(E o) {
        if ( initialLength == getSize() ) {
            extendArray();
        }

        stack[ firstEmptySpot++ ] = o;
    }

    public E pop() {
        E o = stack[ firstEmptySpot - 1 ];
        stack[ firstEmptySpot - 1 ] = null;
        firstEmptySpot--;
        return o;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public String toString() {
        if( isEmpty() ) {
            return "The Stack is empty";
        } else {
            String str = stack[0] + "";

            for (int i = 1; i < getSize(); i++) {
                str += ", " + stack[i];
            }

            return str;
        }
    }

    private void extendArray() {
        int newLength = initialLength * 2;
        initialLength = newLength;

        E[] temp = (E[]) new Object[ newLength ];

        System.arraycopy(stack, 0, temp, 0, stack.length);

        stack = temp;
    }

    public static void main(String[] args) {
        GenericStack stack = new GenericStack();

        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        stack.push("E");
        stack.push("F");
        stack.push("G");

        System.out.println( stack );

        System.out.println( "Øverste element(pop) : " + stack.pop() );
        System.out.println( "Øverste element(peek): " + stack.peek() );

        System.out.println( stack );
    }
}
