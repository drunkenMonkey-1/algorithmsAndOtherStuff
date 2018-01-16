package fhv.at.mwi.eight_puzzle;

import fhv.at.mwi.graph_algorithms.GraphRequirementException;
import fhv.at.mwi.graph_algorithms.IAlgorithm;

import java.util.*;

public class AStarSolver extends Solver implements IAlgorithm<List<PuzzleNode>>{

    private PriorityQueue<PuzzleNode> _pqueue = new PriorityQueue<>(new Comparator<PuzzleNode>() {
        @Override
        public int compare(PuzzleNode o1, PuzzleNode o2) {
            return Integer.compare( _heuristic.getCosts(o1), _heuristic.getCosts(o2));
        }
    });
    private HashSet<PuzzleNode> _closedList = new HashSet<>();
    private IHeuristics _heuristic;

    private PuzzleNode _startNode = null;

    private List<PuzzleNode> solve(PuzzleNode node){
        _pqueue.add(node);
        HashMap<PuzzleNode, Integer> depthTable = new HashMap<>();
        depthTable.put(node, 0);
        PuzzleNode current = null;

        while(!_pqueue.isEmpty()){
            current = _pqueue.poll();
            //System.out.println("Chosen: \n" + current.toString());
            _closedList.add(current);
            if(current.isIdeal(PuzzleNode._idealState)){
                _pqueue.clear();
                // return -> remove else
            } else{
                LinkedList<PuzzleNode> neighbours = new LinkedList<>(current.getNeighbourNodes());
                for(PuzzleNode neighbourNode : neighbours){
                    if(!_closedList.contains(neighbourNode)){
                        int depth = depthTable.get(current) + 1;
                        if(_pqueue.contains(neighbourNode) && depth >= depthTable.get(neighbourNode)){

                        } else {
                            neighbourNode.setParent(current);
                            depthTable.put(neighbourNode, depth);

                            if (_pqueue.contains(neighbourNode)) {
                                _pqueue.remove(neighbourNode);
                                neighbourNode.setCosts(depth + _heuristic.getCosts(neighbourNode));
                                _pqueue.add(neighbourNode);
                            } else {
                                neighbourNode.setCosts(depth + _heuristic.getCosts(neighbourNode));
                                _pqueue.add(neighbourNode);
                            }
                        }
                        // if open list already contains neighbour -> distance update
                        // if open list already contains neighbour and no better cost is possible ignore him
                    }

                }
            }
        }
        return extractPath(current);

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
