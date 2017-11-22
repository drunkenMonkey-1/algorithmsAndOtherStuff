package fhv.at.mwi.tree_structure;

import fhv.at.mwi.tree_visualizer.PropertyTranslator;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args){

        RBTree rbTree = new RBTree(new RBNode<>(6));
//        rbTree.autoInsert(new RBNode<>(10));
//        rbTree.autoInsert(new RBNode<>(5));
//        rbTree.autoInsert(new RBNode<>(7));
//        rbTree.autoInsert(new RBNode<>(8));
//        rbTree.autoInsert(new RBNode<>(77));
//        rbTree.autoInsert(new RBNode<>(9));
//        rbTree.autoInsert(new RBNode<>(14));
//        rbTree.autoInsert(new RBNode<>(90));
//        rbTree.autoInsert(new RBNode<>(12));
//        rbTree.autoInsert(new RBNode<>(1));
//        rbTree.autoInsert(new RBNode<>(77));

        // show javafx window
        rbTree.registerVisualizer("RB Tree");

        System.out.println("RB height: " + rbTree.getRBHeight());

        LinkedList<MetaNode> nodes = rbTree.outputTree(OutputStrategy.LVLBYLVL);
        for(MetaNode n:nodes){
            if(n != null) {
                System.out.println("val: " + n.getValue() + " [" + n.getProperty() + "]");
            } else{
                System.out.println("null");
            }
        }


    }
}
