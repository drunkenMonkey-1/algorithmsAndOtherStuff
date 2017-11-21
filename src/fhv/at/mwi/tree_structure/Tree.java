package fhv.at.mwi.tree_structure;

import java.util.LinkedList;

public abstract class Tree<T extends Node> {


    protected T _root;
    protected static LinkedList<MetaNode> outList;

    public Tree(T root){
        _root = root;
    }


    /**
     * Output the tree structure by given strategy (OutputStrategy)
     * @param s User defined output strategy (INORDER, POSTORDER, LVLBYLVL...)
     * @return Linked List with the the tree values in the correct order as specified with OutputStrategy
     */
    public LinkedList<MetaNode> outputTree(OutputStrategy s){
        outList = new LinkedList<>();
        switch(s){
            case INORDER:
                inorderOut(_root);
                break;
            case PREORDER:
                preorderOut(_root);
                break;
            case POSTORDER:
                postorderOut(_root);
                break;
            case LVLBYLVL:
                lvlbylvlout(_root);
                break;

        }
        return outList;
    }

    /**
     * Inorder output
     * @param root Root node of the tree or subtree.
     */
    private void inorderOut(Node root) {
        root.inorderOut(root, outList);
    }

    /**
     * Postorder output
     * @param root Root node of the tree or subtree.
     */
    private void postorderOut(Node root) {
        root.postorderOut(root, outList);
    }

    /**
     * Preorder output
     * @param root Root node of the tree or subtree.
     */
    private void preorderOut(Node root) {
        root.preorderOut(root, outList);
    }

    /**
     * Level by level output
     * @param root Root node of the tree or subtree.
     */
    private void lvlbylvlout(Node root) {
        root.lvlbylvlOut(root, outList);
    }

    /**
     * Decides how a node is inserted to tree structure
     * @param value Node to insert
     */
    public abstract void autoInsert(T value);
    public abstract int getRBHeight();

    public T getRoot() {
        return _root;
    }

    public void setRoot(T _root) {
        this._root = _root;
    }

}
