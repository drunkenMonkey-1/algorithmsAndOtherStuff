package tests.puzzle_test;

import fhv.at.mwi.eight_puzzle.*;
import fhv.at.mwi.graph_algorithms.GraphRequirementException;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class EightPuzzleSolverTest {

    @Test
    public void testAStarSolver(){
        PuzzleNode pn = new PuzzleNode(new int[]{
                //4, 1, 2, 0, 8, 7, 6, 3, 5
                //3, 0, 7, 2, 8, 1, 6, 4, 5
                //5, 7, 8, 3, 0, 4, 2, 6, 1
                0, 1, 3, 4, 2, 5, 7, 8, 6
        });


        AStarSolver ats = new AStarSolver();
        ats.setConfig(pn, new ManhattanHeuristic());
        try {
            Iterator<PuzzleNode> resultIterator = ats.operate().iterator();
            while(resultIterator.hasNext()){
                System.out.println(resultIterator.next());
            }
        } catch (GraphRequirementException | PuzzleSolverException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testAStarSolverHamming(){
        PuzzleNode pn = new PuzzleNode(new int[]{
                //4, 1, 2, 0, 8, 7, 6, 3, 5
                //3, 0, 7, 2, 8, 1, 6, 4, 5
                5, 7, 8, 3, 0, 4, 2, 6, 1
                //0, 1, 3, 4, 2, 5, 7, 8, 6
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
    @Test
    public void testBFSSolver() {
        PuzzleNode pn = new PuzzleNode(new int[]{
                //4, 1, 2, 0, 8, 7, 6, 3, 5
                //3, 0, 7, 2, 8, 1, 6, 4, 5
                //5, 7, 8, 3, 0, 4, 2, 6, 1
                0, 1, 3, 4, 2, 5, 7, 8, 6
        });

        BFS_Solver bfs = new BFS_Solver();
        bfs.setStartNode(pn);

        Iterator<PuzzleNode> resultIterator = null;
        try {
            resultIterator = bfs.operate().iterator();
        } catch (GraphRequirementException e) {
            e.printStackTrace();
        } catch (PuzzleSolverException e) {
            e.printStackTrace();
        }
        while(resultIterator.hasNext()){
            System.out.println(resultIterator.next());
        }
    }
}