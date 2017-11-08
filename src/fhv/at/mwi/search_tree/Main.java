package fhv.at.mwi.search_tree;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args){
        SequenceReader sr = new SequenceReader("sequence1.txt", "#");

        BinaryTree<Integer> res = sr.SequenceToTree();

        System.out.println("leafs: " + res.leafCount());

        System.out.println("height: " + res.getTreeHeight());

        BinaryTree st = res.convertBinaryToSearchTree();

        res.insert(2);
        res.insert(1);

        LinkedList<Integer> l = st.outputTree(OutputStrategy.LVLBYLVL);
        Iterator li = l.iterator();
        while(li.hasNext()){
            System.out.println(li.next());
        }

        BinaryTree<Character> decodeNode = new BinaryTree<>('+');
        BinaryTree lnode = new BinaryTree('+');
        lnode.setLeft(new BinaryTree('T'));
        lnode.setRight(new BinaryTree('S'));
        decodeNode.setLeft(lnode);
        decodeNode.setRight(new BinaryTree('E'));

        Decoder d = new Decoder();
        System.out.println(d.decode(decodeNode, "1110011"));
    }
}
