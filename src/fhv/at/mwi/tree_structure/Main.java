package fhv.at.mwi.tree_structure;

import java.util.Iterator;

public class Main {

    public static void main(String[] args){
        BinaryTree<Integer, BinaryNode<Integer>> binTree = new BinaryTree<>(4);
        binTree.autoInsert(7);
        binTree.autoInsert(1);
        binTree.autoInsert(3);

        Iterator outIt = binTree.outputTree(OutputStrategy.PREORDER).iterator();
        while(outIt.hasNext()){
            System.out.println(outIt.next());
        }

    }
}
