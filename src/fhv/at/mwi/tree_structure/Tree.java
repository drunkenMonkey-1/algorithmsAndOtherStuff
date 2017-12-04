package fhv.at.mwi.tree_structure;

import fhv.at.mwi.tree_visualizer.PropertyTranslator;
import fhv.at.mwi.tree_visualizer.TreeVisualizer;
import fhv.at.mwi.tree_visualizer.ValueParser;
import fhv.at.mwi.tree_visualizer.VisualizerWnd;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public abstract class Tree<T extends Node> {


    protected T _root;
    protected static LinkedList<MetaNode> outList;
    protected TreeVisualizer _vsz;
    protected PropertyTranslator propTranslator;

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
     * Register a tree visualizer. Every time you call autoInsert the RBTree is redrawn.
     * @param title
     */
    public void registerVisualizer(String title){
        _vsz = new TreeVisualizer(this, propTranslator,
                new ValueParser<Integer>() {
                    @Override
                    public Integer parse(String text) throws NumberFormatException {
                        return Integer.parseInt(text);
                    }

                    @Override
                    public String parseVtoString(Integer value) {
                        return Integer.toString(value);
                    }
                });
        String[] args = {""};
        VisualizerWnd.showWnd(args, _vsz, title);
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
        if(root != null) {
            root.lvlbylvlOut(root, outList);
        }
    }

    /**
     * Decides how a node is inserted to tree structure
     * @param value Vertex to insert
     */
    public abstract void autoInsert(T value);
    public abstract void autoInsert(Object v);
    public abstract int getRBHeight();

    public T getRoot() {
        return _root;
    }

    public void setRoot(T _root) {
        this._root = _root;
    }

}
