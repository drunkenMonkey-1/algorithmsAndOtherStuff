package fhv.at.mwi.priority_queue;
import fhv.at.mwi.tree_structure.BinaryNode;
import fhv.at.mwi.tree_structure.BinaryTree;

import java.util.Comparator;

public class LeftistHeap<T extends Comparable> {

    private Comparator<T> _comparator;

    private BinaryTree _heap;

    public LeftistHeap(Comparator<T> c){
        _heap = new BinaryTree(null);
        _comparator = c;
    }

    public void enqueue(T value){
        BinaryNode<T, Boolean> newNode = new BinaryNode<>(value);
        _heap.setRoot(mergeTreesByNode(_heap.getRoot(), newNode));
    }

    public T dequeue(){
        BinaryNode<T, Boolean> dq = _heap.getRoot();
        if(dq == null){
            return null;
        }
        BinaryNode<T, Boolean> nodeRight = dq.getRight();
        BinaryNode<T, Boolean> nodeLeft = dq.getLeft();
        if(nodeRight == null){
            _heap.setRoot(nodeLeft);
        }
        else if (nodeLeft == null){
            _heap.setRoot(nodeRight);
        }
        else if(_comparator.compare(nodeRight.getValue(), nodeLeft.getValue()) > 0){
            _heap.setRoot(mergeTreesByNode(nodeRight, nodeLeft));
        }
        else {
            _heap.setRoot(mergeTreesByNode(nodeLeft, nodeRight));
        }
        return dq.getValue();
    }

    private BinaryNode<T, Boolean> mergeTreesByNode(BinaryNode<T, Boolean> root, BinaryNode<T, Boolean> newNode){
        BinaryNode<T, Boolean> parent;
        if(root == null){
            return newNode;
        }

        /* If the root is greater than the new node, the new node can be inserted at the right subtree */
        if(_comparator.compare(root.getValue(), newNode.getValue())  > 0){
            root.setRight(mergeTreesByNode(root.getRight(), newNode));
            parent = root;
        } else {
            /* If the new node is greater, it should be at the position of the root -> swap */
            newNode.setRight(mergeTreesByNode(newNode.getRight(), root));
            parent = newNode;
        }

        // after adding an element the path depth
        int leftDepth = getMinPathLength(parent.getLeft());
        int rightDepth = getMinPathLength(parent.getRight());
        /* Property for left-biased tree: left tree must be deeper than right tree
         * -> so, if this property is violated, the right and left tree are swapped */
        if(rightDepth > leftDepth){
            /* simple swap of right and left node of parent */
            BinaryNode tNode = parent.getLeft();
            parent.setLeft(parent.getRight());
            parent.setRight(tNode);
        }
        /* If there are a nodes left and right, add the shortest path of the left/right child */
        if(leftDepth > 0 && rightDepth > 0){
            parent.set_maxPathLength(1 + Math.min(leftDepth, rightDepth));
        }
        return parent;
    }


    public BinaryTree getHeap() {
        return _heap;
    }

    private int getMinPathLength(BinaryNode n){
        if(n!=null){
            return n.get_maxPathLength();
        } else {
            return 0;
        }
    }

    public boolean isEmpty(){
        return (_heap.getRoot()==null);
    }

}
