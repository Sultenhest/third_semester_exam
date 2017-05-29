package IV_trees;

public class IntTreeNode {
    private int data;
    private IntTreeNode left;
    private IntTreeNode right;
                

    public IntTreeNode( int data ) {
        this( data, null, null );
    }

    public IntTreeNode( int data, IntTreeNode left, IntTreeNode right ) {
        setData( data );
        setLeft( left );
        setRight( right );
    }

    public int getData() {
        return data;
    }

    public IntTreeNode getLeft() {
        return left;
    }

    public IntTreeNode getRight() {
        return right;
    }

    public void setData( int data ) {
        this.data = data;
    }

    public void setLeft( IntTreeNode left ) {
        this.left = left;
    }

    public void setRight( IntTreeNode right ) {
        this.right = right;
    }

    public void removeLeft() {
        setLeft( null );
    }

    public void removeRight() {
        setRight( null );
    }
}
