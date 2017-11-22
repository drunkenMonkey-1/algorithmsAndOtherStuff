package fhv.at.mwi.tree_structure;

import fhv.at.mwi.general.EasyMath;
import fhv.at.mwi.tree_visualizer.PropertyTranslator;
import javafx.scene.paint.Color;


public class BinaryTree extends Tree<BinaryNode>{

    private int _currentInsertLevel = 1;
    private int _lastInsertIndex = 1;

    public BinaryTree(BinaryNode rootVal) {
        super(rootVal);
        propTranslator = new PropertyTranslator();
        propTranslator.addColorForProperty(null, Color.GREEN);
    }



    @Override
    public void autoInsert(BinaryNode inNode) {
        insertAtPath(getNextPath(), inNode);
        if(_vsz != null){
            _vsz.redraw();
        }
    }

    @Override
    public void autoInsert(Object v) {
        autoInsert(new BinaryNode<>((Comparable) v));
    }

    @Override
    public int getRBHeight() {
        return getHeight(_root);
    }

    private int getHeight(BinaryNode root){
        if(root == null){
            return 0;
        } else{
            return (Math.max(getHeight(root.getLeft()), getHeight(root.getRight())) + 1);
        }
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
    public void insertAtPath(boolean[] bits, BinaryNode val){
        int n = bits.length;
        BinaryNode tempRoot =  _root;
        for(int i = 0; i < n-1; i++){
            if(bits[i] == false){
                _root = _root.getRight();
            } else {
                _root = _root.getLeft();
            }
        }
        if(bits[n-1] == false){
            _root.setRight(val);
        } else {
            _root.setLeft(val);
        }
        _root = tempRoot;
    }

    public Object[] getSequence(){
        return null;
    }


}
