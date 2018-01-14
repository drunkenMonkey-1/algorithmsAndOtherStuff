package tests.puzzle_test;

import fhv.at.mwi.eight_puzzle.DFS_Solver;
import fhv.at.mwi.eight_puzzle.PuzzleNode;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class EightPuzzleBruteforceTest {

    @Test
    public void testDFSSolutionFinder(){
        PuzzleNode pn = new PuzzleNode(new int[]{
                4, 1, 2, 0, 8, 7, 6, 3, 5
        });
        DFS_Solver ds = new DFS_Solver();

        ds.solve(pn);
    }
}