package fhv.at.mwi.tree_structure;

import java.util.Iterator;

public class Main {

    public static void main(String[] args){
        BinaryTree binTree = new BinaryTree(new BinaryNode<>(4));
        binTree.autoInsert(new BinaryNode<>(7));
        binTree.autoInsert(new BinaryNode<>(1));
        binTree.autoInsert(new BinaryNode<>(3));

        Iterator outIt = binTree.outputTree(OutputStrategy.POSTORDER).iterator();
        while(outIt.hasNext()){
            System.out.println(outIt.next());
        }

    }
}
