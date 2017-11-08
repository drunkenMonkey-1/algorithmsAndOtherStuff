package fhv.at.mwi.search_tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T extends Comparable>  {

    private BinaryTree[] _nodes;
    private T _value;
    private int _nodeLimit = 2;
    private LinkedList outList;
    private int _leaf;


    public BinaryTree(T value) {
        _nodes = new BinaryTree[_nodeLimit];
        _value = value;
    }

    public void insert(T value){
        insertToBinaryTree(this, value);
    }

    private BinaryTree insertToBinaryTree(BinaryTree root, T value){
        if(root == null){
            return new BinaryTree(value);
        }
        if(value.compareTo(root.getValue()) == 1){
            root.setRight(insertToBinaryTree(root.getRight(), value));
        } else {
            root.setLeft(insertToBinaryTree(root.getLeft(), value));
        }
        return root;
    }

    /**
     * Set left node
     * @param n left node
     */
    public void setLeft(BinaryTree n){
        _nodes[0] = n;
    }

    /**
     * Set right node
     * @param n right node
     */
    public void setRight(BinaryTree n){
        _nodes[1] = n;
    }

    /**
     * get value of the node (key)
     * @return T value of the node
     */
    public T getValue(){
        return _value;
    }

    /**
     * Get left node of the root node(me)
     * @return Left node
     */
    public BinaryTree getLeft(){
        return _nodes[0];
    }

    /**
     * Get right node of the root node(me)
     * @return Right node
     */
    public BinaryTree getRight() {
        return _nodes[1];
    }

    /**
     * Convert the current binary tree to a binary search tree
     * @return Root node of the binary search tree
     */
    public BinaryTree<T> convertBinaryToSearchTree(){
        BinaryTree<T> searchTree = new BinaryTree<>(this.getValue());
        outList = new LinkedList();
        lvlbylvlout(this);
        Iterator lvlBylvlIterator = outList.iterator();
        lvlBylvlIterator.next();
        while(lvlBylvlIterator.hasNext()){
            searchTree.insert((T) lvlBylvlIterator.next());
        }
        return searchTree;
    }

    /**
     * Output the tree structure by given strategy (OutputStrategy)
     * @param s User defined output strategy (INORDER, POSTORDER, LVLBYLVL...)
     * @return Linked List with the the tree values in the correct order as specified with OutputStrategy
     */
    public LinkedList outputTree(OutputStrategy s){
        outList = new LinkedList();
        switch(s){
            case INORDER:
                inorderOut(this);
                break;
            case PREORDER:
                preorderOut(this);
                break;
            case POSTORDER:
                postorderOut(this);
                break;
            case LVLBYLVL:
                lvlbylvlout(this);
                break;

        }
        return outList;
    }

    /**
     * Number of leafs a bin. tree has.
     * @return Integer value of the number of leafs
     */
    public int leafCount(){
        _leaf = 0;
        countLeafs(this);
        return _leaf;
    }

    /**
     * Get max path length aka tree height
     * @return Int value of the tree height
     */
    public int getTreeHeight(){
        return treeHeight(this);
    }

    private int treeHeight(BinaryTree root){
        if(root == null){
            return 0;
        } else {
            return 1 + Math.max(treeHeight(root.getLeft()), treeHeight(root.getRight()));
        }


    }

    private void countLeafs(BinaryTree root){
        if(root.getLeft() != null){
            countLeafs(root.getLeft());
        }
        if(root.getRight() != null){
            countLeafs(root.getRight());
        }
        if(root.getRight() == null && root.getLeft() == null) {
            _leaf++;
        }
    }


    private void lvlbylvlout(BinaryTree root){
        Queue<BinaryTree> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            BinaryTree tempNode = q.remove();
            if(tempNode.getLeft() != null){
                q.add(tempNode.getLeft());
            }
            if(tempNode.getRight() != null){
                q.add((tempNode.getRight()));
            }
            outList.add(tempNode.getValue());
        }
    }

    private  void preorderOut(BinaryTree root){
        if(root != null) {
            outList.add((T) root.getValue());
            preorderOut(root.getLeft());
            preorderOut(root.getRight());
        }

    }
    public  void inorderOut(BinaryTree root){
        if(root != null) {
            preorderOut(root.getLeft());
            outList.add((T) root.getValue());
            preorderOut(root.getRight());
        }
    }
    public  void postorderOut(BinaryTree root){
        preorderOut(root.getLeft());
        preorderOut(root.getRight());
        outList.add((T) root.getValue());
    }


}
