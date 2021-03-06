package IV_trees;

public class IntTree {
    private IntTreeNode root;

    public IntTree() {
        root = null;
    }

    public IntTree( int root ) {
        if( this.root == null) {
            this.root = new IntTreeNode(root);
        } else {
            add( root );
        }
    }

    public IntTree( IntTreeNode root ) {
        if( this.root == null) {
            this.root = root;
        } else {
            add( root.getData() );
        }
    }

    public void add( int data ) {
        root = add( root, data );
    }

    private IntTreeNode add( IntTreeNode root, int data ) {
        if ( root == null ) {
            root = new IntTreeNode( data );
        } else if ( data <= root.getData() ) {
            root.setLeft( add( root.getLeft(), data ) );
        } else {  //data > root.data
            root.setRight( add( root.getRight(), data ) );
        }

        return root;
    }

    public boolean search( int data ) {
        return search( root, data );
    }

    private boolean search( IntTreeNode root, int data ) {
        if ( root == null ) {
            return false;
        } else if ( root.getData() == data ) {
            return true;
        } else if ( data <= root.getData() ) {
            return search( root.getLeft(), data );
        } else {
            return search( root.getRight(), data );
        }
    }

    public void remove( int data ) {
        if ( search( data ) ) {
            remove( root, data );
        }
    }

    private IntTreeNode remove(IntTreeNode root, int data) {
        if (root == null) {
            return root;
        }

        /* Otherwise, recur down the tree */
        if ( data < root.getData() ) {
            root.setLeft( remove( root.getLeft(), data ) );
        } else if ( data > root.getData() ) {
            root.setRight( remove( root.getRight(), data) );

        // if key is same as root's key, then This is the node
        // to be deleted
        }else{
            // node with only one child or no child
            if ( root.getLeft() == null ) {
                return root.getRight();
            } else if ( root.getRight() == null ) {
                return root.getLeft();
            }

            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.setData( minValue( root.getRight() ) );

            // Delete the inorder successor
            root.setRight( remove( root.getRight(), root.getData() ) );
        }

        return root;
    }

    private int minValue( IntTreeNode root )
    {
        int minv = root.getData();
        while ( root.getLeft() != null ) {
            minv = root.getLeft().getData();
            root = root.getLeft();
        }
        return minv;
    }

    public void printSideways() {
        printSideways( root , 0 );
    }

    private void printSideways( IntTreeNode root, int level ) {
        if ( root != null ) {
            printSideways( root.getRight(), level + 1 );

            for ( int i = 0; i < level; i++ ) {
                System.out.print("\t");
            }

            System.out.println( root.getData() );
            printSideways( root.getLeft(), level + 1 );
        }
    }

    public String preorder() {
        return "Preorder:" + preorder( root );
    }

    private String preorder( IntTreeNode root ) {
        if( root == null ) {
            return "";
        } else {
            return " " + root.getData() + preorder( root.getLeft() ) + preorder( root.getRight() );
        }
    }

    public String inorder() {
        return "Inorder: " + inorder( root );
    }

    private String inorder( IntTreeNode root ) {
        if( root == null ) {
            return "";
        } else {
            return inorder( root.getLeft() ) + root.getData() + " " + inorder( root.getRight() );
        }
    }

    public String postorder() {
        return "Postorder:" + postorder( root );
    }

    private String postorder( IntTreeNode root ) {
        if( root == null ) {
            return "";
        } else {
            return postorder( root.getLeft() ) + postorder( root.getRight() ) + " " + root.getData();
        }
    }
}
