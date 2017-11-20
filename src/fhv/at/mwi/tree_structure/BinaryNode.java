package fhv.at.mwi.tree_structure;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryNode<V extends Comparable, K> extends Node<V, K>{

    public BinaryNode(V value){
        super(value);
        _children = new BinaryNode[2];
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

}
