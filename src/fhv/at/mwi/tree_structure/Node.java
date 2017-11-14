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

    /**
     * Get the child of the current Node by a given index
     * @param index Index of the child (0 is usally the left most node)
     * @return Child node
     */
    public Node getChildAtIndex(int index){
        return _children[index];
    }

    /**
     * Get a node array of all children
     * @return An array consisting of node objects
     */
    public Node[] getChildren(){
        return _children;
    }

    /**
     * Get the nodes value
     * @return The nodes value as the given generic data type
     */
    public V getValue(){
        return _value;
    }

    /**
     * Compare two nodes by their value -> compare the node calling this method with nodeX
     * @param nodeX Node 1
     * @return returns zero if nodeX and this node are the same.
     */
    protected abstract int compareNodes(Node nodeX);

}
