package fhv.at.mwi.tree_structure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Node<V extends  Comparable, K> {

    protected Node[] _children;
    protected V _value;
    protected K _property;

    /**
     * Create a new Vertex with the value stored in it
     * After calling the super constructor, you need to init the protected _children variable.
     * @param value
     */
    public Node(V value){
        _value = value;
    }

    /**
     * Get the child of the current Vertex by a given index
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
     *
     * @param val
     */
    public void setValue(V val) {
        _value = val;
    }

    /**
     * Get the node property
     * @return K value of the set property
     */
    public K getProperty(){
        return this._property;
    }

    /**
     * Set property
     * @param val K value of the property to set
     */
    public void setProperty(K val){
        this._property = val;
    }


    protected void inorderOut(Node root, List outList){
        if(root != null) {
            inorderOut(root.getChildAtIndex(0), outList);
            outList.add(root.getMetaNode());
            inorderOut(root.getChildAtIndex(1), outList);
        }
    }
    protected void postorderOut(Node root, List outList){
        if(root != null) {
            postorderOut( root.getChildAtIndex(0), outList);
            postorderOut( root.getChildAtIndex(1), outList);
            outList.add( root.getMetaNode());
        }
    }
    protected void preorderOut(Node root, List outList){
        if(root != null) {
            outList.add(root.getMetaNode());
            preorderOut(root.getChildAtIndex(0), outList);
            preorderOut(root.getChildAtIndex(1), outList);
        }
    }


    protected void lvlbylvlOut(Node root, List outList){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            Node tempNode = q.remove();
            if(tempNode != null) {
                if (tempNode.getChildAtIndex(0) != null) {
                    q.add(tempNode.getChildAtIndex(0));
                }
                if (tempNode.getChildAtIndex(1) != null) {
                    q.add((tempNode.getChildAtIndex(1)));
                } if (tempNode.getChildAtIndex(1) == null) {
                    q.add(null);
                } if (tempNode.getChildAtIndex(0) == null){
                    q.add(null);
                }
            }
            if(tempNode == null){
                outList.add(null);
                if(!checkForNull(q)) {
                    q.add(null);
                    q.add(null);
                }
            } else {
                outList.add(tempNode.getMetaNode());
            }
        }
    }

    private boolean checkForNull(Queue<Node> q){
        Iterator qt = q.iterator();
        while(qt.hasNext()){
            if(qt.next() != null){
                return false;
            }
        }
        return true;
    }

    public MetaNode getMetaNode(){
        return (new MetaNode<V, K>(_value, _property));
    }

    /**
     * Compare two nodes by their value -> compare the node calling this method with nodeX
     * @param nodeX Vertex 1
     * @return returns zero if nodeX and this node are the same.
     */
    protected abstract int compareNodes(Node nodeX);


}
