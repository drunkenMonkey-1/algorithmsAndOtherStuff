package fhv.at.mwi.tree_structure;


public class BinaryNode<V extends Comparable, K> extends Node<V, K>{

    private int _maxPathLength;

    public BinaryNode(V value){
        super(value);
        _children = new BinaryNode[2];
        _maxPathLength = 1;
    }

    public BinaryNode(V value, K property){
        this(value);
        this.setProperty(property);
    }


    public BinaryNode getLeft(){
        return (BinaryNode) _children[0];
    }

    public BinaryNode getRight(){
        return (BinaryNode) _children[1];
    }

    public BinaryNode setLeft(BinaryNode node){
        _children[0] = node;
        return getLeft();
    }
    public BinaryNode setRight(BinaryNode node){
        _children[1] = node;
        return getRight();
    }


    @Override
    protected int compareNodes(Node nodeX) {
        return _value.compareTo(nodeX.getValue());
    }

    public int get_maxPathLength() {
        return _maxPathLength;
    }

    public void set_maxPathLength(int _maxPathLength) {
        this._maxPathLength = _maxPathLength;
    }
}
