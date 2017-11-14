package fhv.at.mwi.tree_structure;


public class BinaryNode<V extends Comparable> extends Node<V>{

    public BinaryNode(V value){
        super(value);
        _children = new BinaryNode[2];
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
}
