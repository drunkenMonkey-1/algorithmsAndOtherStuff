package fhv.at.mwi.tree_structure;

import fhv.at.mwi.general.EasyMath;

public class BinaryTree<V extends Comparable, T extends Node<V>> extends Tree<V, T>{

    private int _currentInsertLevel = 1;
    private int _lastInsertIndex = 1;

    public BinaryTree(V rootVal) {
        super((T) new BinaryNode(rootVal));
    }

    @Override
    protected int compareNodes(T nodeX) {
        return 0;
    }

    @Override
    protected void inorderOut(T root) {
        BinaryNode bRoot = (BinaryNode) root;
        if(bRoot != null) {
            preorderOut((T) bRoot.getLeft());
            outList.add((V) bRoot.getValue());
            preorderOut((T) bRoot.getRight());
        }
    }

    @Override
    protected void postorderOut(T root) {
        BinaryNode bRoot = (BinaryNode) root;
        if(bRoot != null) {
            preorderOut((T) bRoot.getLeft());
            preorderOut((T) bRoot.getRight());
            outList.add((V) bRoot.getValue());
        }
    }

    @Override
    protected void preorderOut(T root) {
        BinaryNode bRoot = (BinaryNode) root;
        if(bRoot != null) {
            outList.add((V) bRoot.getValue());
            preorderOut((T) bRoot.getLeft());
            preorderOut((T) bRoot.getRight());
        }
    }

    @Override
    protected void lvlbylvlout(T root) {

    }

    @Override
    public void autoInsert(V value) {
        insertAtPath(getNextPath(), value);
    }

    /**
     * Get the binary path by the last insert index (=decimal representation of the path) and
     * the current max. tree level
     * @return Bit path (0/false is right node, 1/true is left node)
     */
    private boolean[] getNextPath(){

        boolean[] bitArray = new boolean[_currentInsertLevel];
        int bitArraySize = bitArray.length;
        for(int i = 0; i < bitArraySize; i++) {
            bitArray[bitArraySize - 1 - i] = (1 << i & _lastInsertIndex) != 0;
        }

        if(_lastInsertIndex == 0){
           _currentInsertLevel++;
           _lastInsertIndex = EasyMath.power(_currentInsertLevel, 2) - 1;
        } else {
            _lastInsertIndex--;
        }
        return bitArray;
    }

    /**
     * Path insert
     * @param bits Path in bits (0/false is right node, 1/true is left node)
     * @param val Value to be insert at the given path
     */
    private void insertAtPath(boolean[] bits, V val){
        BinaryNode<V> newNode = new BinaryNode<>(val);
        int n = bits.length;
        T tempRoot =  _root;
        for(int i = 0; i < n-1; i++){
            if(bits[i] == false){
                _root = (T) ((BinaryNode) _root).getRight();
            } else {
                _root = (T) ((BinaryNode) _root).getLeft();
            }
        }
        if(bits[n-1] == false){
            ((BinaryNode) _root).setRight(newNode);
        } else {
            ((BinaryNode) _root).setLeft(newNode);
        }
        _root = tempRoot;
    }

    public V[] getSequence(){
        return null;
    }


}
