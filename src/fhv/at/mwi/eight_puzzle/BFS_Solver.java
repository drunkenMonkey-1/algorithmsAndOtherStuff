package fhv.at.mwi.eight_puzzle;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class BFS_Solver {

    private LinkedList<PuzzleNode> _queue = new LinkedList<>();
    private HashSet<PuzzleNode> _closedList = new HashSet<>();

    public void solve(PuzzleNode node){
        _queue.add(node);
        PuzzleNode current = null;
        while(!_queue.isEmpty()){
            current = _queue.removeFirst();
            _closedList.add(current);
            if(current.isIdeal(PuzzleNode._idealState)){
                System.out.println("Found \n" + current.toString());
                _queue.clear();
            } else{
                LinkedList<PuzzleNode> neighbours = new LinkedList<>(current.getNeighbourNodes());
                for(int i = 0; i < neighbours.size(); i++){
                    if(!_closedList.contains(neighbours.get(i))){
                        _queue.add(neighbours.get(i));
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
