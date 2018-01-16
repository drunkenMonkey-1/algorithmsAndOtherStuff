package fhv.at.mwi.eight_puzzle;

public class HammingDistance implements IHeuristics {
    @Override
    public int getCosts(PuzzleNode pn) {
        return pn.getHammingDistance();
    }
}
