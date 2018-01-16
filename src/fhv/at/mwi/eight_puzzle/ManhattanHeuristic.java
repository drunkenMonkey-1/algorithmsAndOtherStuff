package fhv.at.mwi.eight_puzzle;

public class ManhattanHeuristic implements IHeuristics{


    @Override
    public int getCosts(PuzzleNode pn) {
        return pn.getManhattanDistance();
    }
}
