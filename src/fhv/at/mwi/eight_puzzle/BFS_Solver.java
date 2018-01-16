package fhv.at.mwi.eight_puzzle;

import fhv.at.mwi.graph_algorithms.GraphRequirementException;
import fhv.at.mwi.graph_algorithms.IAlgorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BFS_Solver extends Solver implements IAlgorithm<List<PuzzleNode>>{

    private LinkedList<PuzzleNode> _queue = new LinkedList<>();
    private HashSet<PuzzleNode> _closedList = new HashSet<>();
    private int _estimatedExpandOperations = 0;

    public List<PuzzleNode> solve(PuzzleNode node){
        _queue.add(node);
        _estimatedExpandOperations = 0;
        PuzzleNode current = null;
        while(!_queue.isEmpty()){
            current = _queue.removeFirst();
            _closedList.add(current);
            if(current.isIdeal(PuzzleNode._idealState)){
                _queue.clear();
            } else{
                _estimatedExpandOperations++;
                LinkedList<PuzzleNode> neighbours = new LinkedList<>(current.getNeighbourNodes());
                for(int i = 0; i < neighbours.size(); i++){
                    if(!_closedList.contains(neighbours.get(i))){
                        _queue.add(neighbours.get(i));
                        neighbours.get(i).setParent(current);
                    }
                }
            }
        }
        return extractPath(current, _estimatedExpandOperations);
    }

    public void setStartNode(PuzzleNode _startNode) {
        this._startNode = _startNode;
    }

    private PuzzleNode _startNode = null;

    @Override
    public LinkedList<PuzzleNode> operate() throws GraphRequirementException, PuzzleSolverException {
        if(_startNode != null){
            return (LinkedList<PuzzleNode>) solve(_startNode);
        }
        return null;
    }
}
