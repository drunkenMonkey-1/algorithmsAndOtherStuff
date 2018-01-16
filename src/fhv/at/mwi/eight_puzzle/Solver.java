package fhv.at.mwi.eight_puzzle;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public abstract class Solver {

    protected List<PuzzleNode> extractPath(PuzzleNode startNode){
        LinkedList<PuzzleNode> resultList = new LinkedList<>();
        Stack<PuzzleNode> _resultStack = new Stack<>();
        _resultStack.push(startNode);
        while(startNode.getParent()!=null){
            _resultStack.push(startNode.getParent());
            startNode = startNode.getParent();
        }
        _resultStack.push(startNode);

        while(!_resultStack.empty()){
            resultList.add(_resultStack.pop());
        }
        return resultList;
    }
}
