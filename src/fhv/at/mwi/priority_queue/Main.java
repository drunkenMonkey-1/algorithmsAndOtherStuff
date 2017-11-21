package fhv.at.mwi.priority_queue;

import fhv.at.mwi.tree_structure.MetaNode;
import fhv.at.mwi.tree_structure.OutputStrategy;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args){

        LeftistHeap<Integer> heap = new LeftistHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        heap.enqueue(11);
        heap.enqueue(10);
        heap.enqueue(12);
        heap.enqueue(100);
        heap.enqueue(1);
        heap.enqueue(3);

        System.out.println(heap.dequeue());

        LinkedList<MetaNode> nodes =  heap.getHeap().outputTree(OutputStrategy.LVLBYLVL);
        for(MetaNode n:nodes){
            if(n != null) {
                System.out.println("val: " + n.getValue() + " [" + n.getProperty() + "]");
            } else{
                System.out.println("null");
            }
        }

    }
}
