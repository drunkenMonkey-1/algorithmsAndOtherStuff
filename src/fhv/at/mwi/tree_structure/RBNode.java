package fhv.at.mwi.tree_structure;

import java.util.List;

public class RBNode <V extends Comparable> extends Node<V, Boolean> {


    private RBNode _parentNode;

    /**
     * Create a new Node with the value stored in it
     * After calling the super constructor, you need to init the protected _children variable.
     *
     * @param value
     */
    public RBNode(V value) {
        super(value);
        _children = new RBNode[2];
    }

    /**
     * Create a new node with the value and a property that is either true or false (red or black)
     * @param value Value of the node (aka key)
     * @param property Property of the node (red/black bit)
     */
    public RBNode(V value, boolean property){
        this(value);
        this.setProperty(property);
    }

    public RBNode getLeft(){
        return (RBNode) _children[0];
    }

    public RBNode getRight(){
        return (RBNode) _children[1];
    }

    public RBNode setLeft(RBNode node){
        _children[0] = node;
        return getLeft();
    }
    public RBNode setRight(RBNode node){
        _children[1] = node;
        return getRight();
    }


    @Override
    protected int compareNodes(Node nodeX) {
        return _value.compareTo(nodeX.getValue());
    }

    public RBNode getParentNode() {
        return _parentNode;
    }

    public void setParentNode(RBNode _parentNode) {
        this._parentNode = _parentNode;
    }
}
