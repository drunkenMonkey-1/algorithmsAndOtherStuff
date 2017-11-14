package fhv.at.mwi.tree_structure;

public abstract class Node<V extends  Comparable> {

    protected Node[] _children;
    protected V _value;

    /**
     * Create a new Node with the value stored in it
     * After calling the super constructor, you need to init the protected _children variable.
     * @param value
     */
    public Node(V value){
        _value = value;
    }

    public Node[] getChildren(){
        return _children;
    }

    public V getValue(){
        return _value;
    }

}
