package fhv.at.mwi.eight_puzzle;

import java.util.*;

public class DFS_Solver {


    private Stack<PuzzleNode> nodes = new Stack<>();
    private HashSet<PuzzleNode> _closedList = new HashSet<>();

    public void solve(PuzzleNode node){
        System.out.println(node.toString());
        _closedList.add(node);
        if(node.isIdeal(PuzzleNode._idealState)){
            System.out.println("Found: " + node.toString());
            return;
        }
        LinkedList<PuzzleNode> neighbours = new LinkedList<>(node.getNeighbourNodes());
        for(int i = 0; i < neighbours.size(); i++){
            if(!_closedList.contains(neighbours.get(i))){
                nodes.push(neighbours.get(i));
            }
        }
        while(!nodes.isEmpty()){
            solve(nodes.pop());
        }

    }

}
