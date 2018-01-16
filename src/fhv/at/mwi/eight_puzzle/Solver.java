package fhv.at.mwi.eight_puzzle;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public abstract class Solver {

    protected List<PuzzleNode> extractPath(PuzzleNode startNode, int _computedSteps){
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
        System.out.printf("expanded\t visited\t eb-factor\t\n");
        System.out.printf("%s\t\t\t %s\t\t\t %s\t\t\t\n", _computedSteps, resultList.size(), Math.pow((double)_computedSteps, 1 / (double)resultList.size()));
        return resultList;
    }
}
