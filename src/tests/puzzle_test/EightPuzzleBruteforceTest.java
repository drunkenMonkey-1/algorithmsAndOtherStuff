package tests.puzzle_test;

import fhv.at.mwi.eight_puzzle.*;
import fhv.at.mwi.graph_algorithms.GraphRequirementException;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class EightPuzzleBruteforceTest {

    @Test
    public void testBFSSolutionFinder(){
        PuzzleNode pn = new PuzzleNode(new int[]{
                //4, 1, 2, 0, 8, 7, 6, 3, 5
                //3, 0, 7, 2, 8, 1, 6, 4, 5
                  5, 7, 8, 3, 0, 4, 2, 6, 1
        });


        AStarSolver ats = new AStarSolver();
        ats.setConfig(pn, new HammingDistance());
        try {
            Iterator<PuzzleNode> resultIterator = ats.operate().iterator();
            while(resultIterator.hasNext()){
                System.out.println(resultIterator.next());
            }
        } catch (GraphRequirementException | PuzzleSolverException e) {
            e.printStackTrace();
        }
    }
}