package fhv.at.mwi.search_tree;

import java.util.LinkedList;
import java.util.List;

public abstract class TreeNode<T> {

    private List<TreeNode> _nodes;
    public static int _maxNodes;
    private T _value;

    public TreeNode(T value){
        _nodes = new LinkedList<TreeNode>();
        _value = value;
    }
    public T getValue(){
        return _value;
    };

    public abstract T insertNode(TreeNode root, T _value);

    public abstract int treeHeight(TreeNode root);

}
