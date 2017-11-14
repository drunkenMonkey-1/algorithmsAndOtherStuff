package fhv.at.mwi.tree_structure;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public abstract class Tree<T extends Node> {

    protected T _root;
    protected LinkedList outList;

    public Tree(T root){
        _root = root;
    }


    /**
     * Output the tree structure by given strategy (OutputStrategy)
     * @param s User defined output strategy (INORDER, POSTORDER, LVLBYLVL...)
     * @return Linked List with the the tree values in the correct order as specified with OutputStrategy
     */
    public LinkedList outputTree(OutputStrategy s){
        outList = new LinkedList();
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


    protected abstract void inorderOut(T root);
    protected abstract void postorderOut(T root);
    protected abstract void preorderOut(T root);
    protected abstract void lvlbylvlout(T root);
    public abstract void autoInsert(T value);


}
