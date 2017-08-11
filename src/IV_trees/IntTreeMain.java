package IV_trees;

public class IntTreeMain {
    public static void main(String[] args) {
        IntTree tree = new IntTree( 50 );
        tree.add(17);
        tree.add(72);

        tree.add(12);
        tree.add(23);
        tree.add(54);
        tree.add(76);

        tree.add(9);
        tree.add(14);
        tree.add(19);
        tree.add(67);

        tree.printSideways();

        System.out.println( tree.preorder() );

        System.out.println( tree.inorder() );

        System.out.println( tree.postorder() );
    }
}
