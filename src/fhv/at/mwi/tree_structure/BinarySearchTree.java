package fhv.at.mwi.tree_structure;

public class BinarySearchTree extends Tree<BinaryNode>{

    public BinarySearchTree(BinaryNode root) {
        super(root);
    }



    @Override
    public void autoInsert(BinaryNode value) {

    }

    @Override
    public int getRBHeight() {
        return 0;
    }
}
