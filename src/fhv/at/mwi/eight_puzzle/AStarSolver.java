package fhv.at.mwi.eight_puzzle;

import fhv.at.mwi.graph_algorithms.GraphRequirementException;
import fhv.at.mwi.graph_algorithms.IAlgorithm;

import java.util.*;

public class AStarSolver extends Solver implements IAlgorithm<List<PuzzleNode>>{

    private PriorityQueue<PuzzleNode> _pqueue = new PriorityQueue<>(Comparator.comparingInt(PuzzleNode::getCosts));
    private HashSet<PuzzleNode> _closedList = new HashSet<>();
    private IHeuristics _heuristic;
    private int _estimatedExpandOperations = 0;

    private PuzzleNode _startNode = null;

    private List<PuzzleNode> solve(PuzzleNode node){
        _pqueue.add(node);
        _estimatedExpandOperations = 0;
        HashMap<PuzzleNode, Integer> depthTable = new HashMap<>();
        depthTable.put(node, 0);
        PuzzleNode current = null;

        while(!_pqueue.isEmpty()){
            current = _pqueue.poll();
            _closedList.add(current);
            if(current.isIdeal(PuzzleNode._idealState)){
                _pqueue.clear();
                // return -> remove else
            } else{
                LinkedList<PuzzleNode> neighbours = new LinkedList<>(current.getNeighbourNodes());
                _estimatedExpandOperations++;
                for(PuzzleNode neighbourNode : neighbours){
                    if(!_closedList.contains(neighbourNode)){
                        int depth = depthTable.get(current) + 1;
                        // if open list already contains neighbour and no better cost is possible ignore him
                        if(_pqueue.contains(neighbourNode) && depth >= depthTable.get(neighbourNode)){

                        } else {
                            neighbourNode.setParent(current);
                            depthTable.put(neighbourNode, depth);

                            // if open list already contains neighbour -> distance update
                            if (_pqueue.contains(neighbourNode)) {
                                _pqueue.remove(neighbourNode);
                                neighbourNode.setCosts(depth + _heuristic.getCosts(neighbourNode));
                                _pqueue.add(neighbourNode);
                            } else {
                                neighbourNode.setCosts(depth + _heuristic.getCosts(neighbourNode));
                                _pqueue.add(neighbourNode);
                            }
                        }
                    }

                }
            }
        }
        return extractPath(current, _estimatedExpandOperations);

    }

    public void setConfig(PuzzleNode _startNode, IHeuristics heuristics) {
        this._startNode = _startNode;
        _heuristic = heuristics;
    }

    @Override
    public List<PuzzleNode> operate() throws GraphRequirementException, PuzzleSolverException {
        if(_startNode!=null){
            return solve(_startNode);
        } else{
            throw new PuzzleSolverException("No start node was set for solving the puzzle");
        }
    }
}
