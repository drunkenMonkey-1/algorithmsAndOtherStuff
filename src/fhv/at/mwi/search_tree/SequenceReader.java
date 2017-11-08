package fhv.at.mwi.search_tree;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class SequenceReader {

    private Integer[] _sequence;

    /**
     * Convert sequential view of a tree to a linked tree. Constructor loads file and data into memory
     * @param filename Your filename
     * @param separator Separator character between every element in the sequential tree
     */
    public SequenceReader(String filename, String separator){
        BufferedReader bIN;
        try {
            bIN = new BufferedReader(new FileReader(filename));
            String fLine = "";
            if((fLine = bIN.readLine()) != null){       // for test purposes read only the first line
                String[] sequence = fLine.split(separator);
                Integer[] numSequence = new Integer[sequence.length+1];
                for(int i = 0; i < sequence.length; i++) {
                    if (!sequence[i].equals("*")){
                        numSequence[i + 1] = Integer.parseInt(sequence[i]);
                    } else {
                        numSequence[i + 1] = null;
                    }
                }
                _sequence = numSequence;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert the array loaded from file to a linked tree
     * @return Root node of the tree
     */
    public BinaryTree SequenceToTree(){
       return setTree(1);
    }


    /**
     * Private method to set the tree structure recursive.
     * @param index Index in the sequential tree
     * @return Root node
     */
    private BinaryTree setTree(int index) {
        BinaryTree<Integer> tRoot = new BinaryTree<>(_sequence[index]);
        if (index < _sequence.length){
            int leftChild = 2 * index;
            int rightChild = (2 * index) + 1;
            if (leftChild < _sequence.length) {
                tRoot.setLeft(setTree(leftChild));
            }
            if (rightChild < _sequence.length) {
                tRoot.setRight(setTree(rightChild));
            }
        }
        return tRoot;
    }

}
