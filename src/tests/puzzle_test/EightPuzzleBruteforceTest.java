package tests.puzzle_test;

import fhv.at.mwi.eight_puzzle.AStarSolver;
import fhv.at.mwi.eight_puzzle.BFS_Solver;
import fhv.at.mwi.eight_puzzle.DFS_Solver;
import fhv.at.mwi.eight_puzzle.PuzzleNode;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class EightPuzzleBruteforceTest {

    @Test
    public void testBFSSolutionFinder(){
        PuzzleNode pn = new PuzzleNode(new int[]{
                //4, 1, 2, 0, 8, 7, 6, 3, 5
                3, 0, 7, 2, 8, 1, 6, 4, 5
        });


        AStarSolver bfs = new AStarSolver();

        bfs.solve(pn);
    }
}