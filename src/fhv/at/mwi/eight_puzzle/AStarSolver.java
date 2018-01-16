package fhv.at.mwi.eight_puzzle;

import java.util.*;

public class AStarSolver {

    private PriorityQueue<PuzzleNode> _pqueue = new PriorityQueue<>(Comparator.comparingInt(PuzzleNode::getCosts));
    private HashSet<PuzzleNode> _closedList = new HashSet<>();

    public void solve(PuzzleNode node){
        _pqueue.add(node);

        PuzzleNode current = null;

        while(!_pqueue.isEmpty()){
            current = _pqueue.poll();
            _closedList.add(current);
            if(current.isIdeal(PuzzleNode._idealState)){
                _pqueue.clear();
                // return -> remove else
            } else{
                LinkedList<PuzzleNode> neighbours = new LinkedList<>(current.getNeighbourNodes());
                for(int i = 0; i < neighbours.size(); i++){
                    if(!_closedList.contains(neighbours.get(i))){
                        // if open list already contains neighbour -> distance update
                        // if open list already contains neighbour and no better cost is possible ignore him
                        neighbours.get(i).setCosts(neighbours.get(i).getHammingDistance());
                        _pqueue.add(neighbours.get(i));
                    }
                }
            }
        }

        Stack<PuzzleNode> _resultStack = new Stack<>();
        _resultStack.push(current);
        while(current.getParent()!=null){
            _resultStack.push(current.getParent());
            current = current.getParent();
        }
        _resultStack.push(current);

        while(!_resultStack.empty()){
            System.out.println(_resultStack.pop().toString());
        }
    }
}
