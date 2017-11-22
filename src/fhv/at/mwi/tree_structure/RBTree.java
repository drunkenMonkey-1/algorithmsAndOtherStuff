package fhv.at.mwi.tree_structure;

import fhv.at.mwi.tree_visualizer.*;
import javafx.scene.paint.Color;

public class RBTree extends Tree<RBNode<?>> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private static RBNode<?> _sentinel;

    /**
     * Create a new RBTree with a given root RBNode
     * (Sentinel and root color is set automatically)
     * @param root Root RB Node of the newly generated tree
     */
    public RBTree(RBNode<?> root) {
        super(root);
        _root.setProperty(BLACK);
        _sentinel = new RBNode<>("slim shady", BLACK);
        _root.setParentNode(_sentinel);
        _root.setRight(_sentinel);
        _root.setLeft(_sentinel);
        propTranslator = new PropertyTranslator<Boolean>();
        propTranslator.addColorForProperty(true, Color.RED);
        propTranslator.addColorForProperty(false, Color.BLACK);

    }

    /**
     * Get the RB height (number of lvls) of the tree, starting from the root/head node
     * @return Tree height
     */
    @Override
    public int getRBHeight(){
        return treeHeight(_root);
    }


    private int treeHeight(RBNode<?> n){
        if(n == _sentinel){
            return 0;
        }
        return Math.max(treeHeight(n.getRight()), treeHeight(n.getLeft())) + 1;
    }

    /**
     * Get number of black nodes starting from root node
     * @return Number of black nodes
     */
    public int getBlackDepth(){
        int bnodes = 0;
        RBNode<?> tempRoot = _root;
        while(tempRoot != _sentinel){
            tempRoot = tempRoot.getLeft();
            if(!tempRoot.getProperty()){
                bnodes++;
            }
        }
        return bnodes;
    }

    /**
     * RB Insert
     * brief description of the procedure:
     *  1. Insert a node just like in a binary search tree, but maintain the parent relations of each node
     *  2. Commit new node to tree by setting the left or right child of the insert position
     *  3. Fix up: fix the RB properties that might have been violated
     *  4. 2 properties might be violated:
     *          - root node must be black
     *          - the child of a red node is red
     *  5. Check if the second violation is present
     *  6. If so - check how to fix the violation by looking at the uncle
     *  7. Depending on the color of the uncle and in which subtree (left/right) of it's parent the inserted
     *     node is.
     *          - case 1:  uncle is red -> recolor fix
     *          - case 2a: node is right child and uncle is black:  -> double rotation + recolor fix
     *          - case 2b: node is a left child and uncle is black: -> simple rotation + recolor fix
     *  8. For case 1 the grandparent is the new node that needs to be check for RB properties
     *     For case 2a the new node is the parent, because of the left/right rotation, which moved
     *                 the parent down one lvl and the node up
     *     For case 2b the grand parent of the node is set to red. To fix this temporary violation a rotation
     *                 is performed. A case 2a always results in a case 2b!
     *  9. Since there is a red node left (in every case), that hasn't been checked on RB correctness yet
     *     the loop needs to be executed again, but this time with the red node that was added
     * 10. If the parent of the current node isn't red anymore the while loop is terminated.
     * 11. Set the root to black. So the second violation from above is fixed, even if the root is still black.
     * 12. End: RB Properties are fixed. RB Tree is rebalanced.
     * @param node Node to be inserted to the RB Tree
     */
    @Override
    public void autoInsert(RBNode<?> node) {
        node.setProperty(RED);
        node.setLeft(_sentinel);
        node.setRight(_sentinel);
        boolean lr = false;

        RBNode<?> currentNode = _root;
        RBNode<?> temp = _sentinel;

        // iterative binary search
        while(currentNode != _sentinel){
            temp = currentNode;
            if(node.compareNodes(currentNode) == 1){
                currentNode = currentNode.getRight();
                lr = true;
            } else {
                currentNode = currentNode.getLeft();
                lr = false;
            }
            currentNode.setParentNode(temp);
        }
        node.setParentNode(temp);
        // commit new node to tree
        if(lr) {
            currentNode.getParentNode().setRight(node);
        } else {
            currentNode.getParentNode().setLeft(node);
        }


        // fix up: Check if RB properties are violated
        System.out.println(node.getValue()+ " was inserted");         // debug infos
        while((Boolean)node.getParentNode().getProperty()){          // == RED
            // simplification
            RBNode<?> parent = node.getParentNode();
            RBNode<?> gparent = node.getParentNode().getParentNode();
            // parent in the left subtree
            if(gparent.getLeft() == parent){       // if(currentNode.getParentNode().getParentNode().getLeft() == currentNode.getParentNode()){
                RBNode<?> u = gparent.getRight();
                // case 1: recolor
                if(u.getProperty() == RED){
                    System.out.println("recolor fix up: " + node.getValue());
                    u.setProperty(BLACK);
                    parent.setProperty(BLACK);
                    gparent.setProperty(RED);
                    node = gparent;
                } else {                                //case 2: uncle u is black
                    //case 2a: uncle is black and current node is a right child (of it's parent node)
                    // transform case 2a to the case 2b
                    if(node == parent.getRight()){
                        System.out.println("left sub 2a: " + node.getValue());
                        node = parent;
                        rotateLeft(node);
                    }
                    //case 2b: uncle is black and current node is a left child (of it's parent node)
                    // if the uncle is black it must be a case 2b if it wasn't a case 2a
                    System.out.println("left sub 2b: " + node.getValue());
                    node.getParentNode().getParentNode().setProperty(RED);
                    node.getParentNode().setProperty(BLACK);
                    rotateRight(node.getParentNode().getParentNode());

                }
            } else {        // parent in right subtree
                RBNode<?> u = gparent.getLeft();
                if(u.getProperty() == RED){
                    System.out.println("recolor fix up: " + node.getValue());
                    u.setProperty(BLACK);
                    parent.setProperty(BLACK);
                    gparent.setProperty(RED);
                    node = gparent;
                } else {
                    if(node == parent.getLeft()){
                        System.out.println("right sub 2a: " + node.getValue());
                        node = parent;
                        rotateRight(node);
                    }
                    System.out.println("right sub 2b: " + node.getValue());
                    node.getParentNode().getParentNode().setProperty(RED);
                    node.getParentNode().setProperty(BLACK);
                    rotateLeft(node.getParentNode().getParentNode());
                }
            }
        }
        // when gparent is set to red and gparent is the root, then the parent of root is black (sentinel), so
        // there is no rb property violated. Here the _root is set to black to complete the fix up
        _root.setProperty(BLACK);

        // if a tree visualizer was registred, the tree will be redrawn, to make the changes visible
        if(_vsz != null){
            System.out.println("redraw");
            _vsz.redraw();
        }
    }

    @Override
    public void autoInsert(Object v) {
        autoInsert(new RBNode<Comparable>((Comparable) v));
    }

    /**
     * Rotate right by mid Node.
     * procedure: mid becomes the right child of swap (left child of mid)
     * the right child of swap becomes the left child of mid (since mid
     * doesn't need the right node anymore - swap was the left node of mid)
     * @param mid Node that is rotated around
     */
    private void rotateRight(RBNode mid){
        System.out.println("right rotate by: " + mid.getValue());
        RBNode swap = mid.getLeft();
        mid.setLeft(swap.getRight());
        if(swap.getRight() != _sentinel){         // if swap.right is a sentinel there is no need to set a parent node
            swap.getRight().setParentNode(mid);
        }
        swap.setParentNode(mid.getParentNode());
        if(mid.getParentNode() == _sentinel){
            _root = swap;
        }
        else if(mid.getParentNode().getRight() == mid){
            mid.getParentNode().setRight(swap);
        } else {
            mid.getParentNode().setLeft(swap);
        }
        swap.setRight(mid);
        mid.setParentNode(swap);
    }

    /**
     * Rotate left by mid Node.
     * procedure: mid becomes the left child of swap (right child of mid)
     * the left child of swap becomes the right child of mid (since mid
     * doesn't need the right node anymore - swap was the right node of mid)
     * @param mid Node that is rotated around
     */
    private void rotateLeft(RBNode mid){
        System.out.println("left rotate by: " + mid.getValue());
        RBNode swap = mid.getRight();
        mid.setRight(swap.getLeft());
        if(swap.getLeft() != _sentinel){
            swap.getLeft().setParentNode(mid);
        }
        swap.setParentNode(mid.getParentNode());
        if(mid.getParentNode() == _sentinel){
            _root = swap;
        }
        else if(mid.getParentNode().getRight() == mid){
            mid.getParentNode().setRight(swap);
        } else {
            mid.getParentNode().setLeft(swap);
        }
        swap.setLeft(mid);
        mid.setParentNode(swap);
    }


}
