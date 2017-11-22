package tests.tree_structure;

import fhv.at.mwi.tree_structure.RBNode;
import fhv.at.mwi.tree_structure.RBTree;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class RBTreeTest {
    private RBTree rbTree = new RBTree(new RBNode<>(6));

    @Test
    void getRBHeight() {
        assertEquals(1, rbTree.getRBHeight());
        rbTree.autoInsert(new RBNode<>(10));
        rbTree.autoInsert(new RBNode<>(4));
        assertEquals(2, rbTree.getRBHeight());
        rbTree.autoInsert(new RBNode<>(1));
        assertEquals(3, rbTree.getRBHeight());
    }

    @Test
    void getBlackDepth() {
        rbTree = new RBTree(new RBNode<>(6));
        rbTree.autoInsert(new RBNode<>(10));
        rbTree.autoInsert(new RBNode<>(4));
        rbTree.autoInsert(new RBNode<>(1));
        assertEquals(2, rbTree.getBlackDepth());
    }

}