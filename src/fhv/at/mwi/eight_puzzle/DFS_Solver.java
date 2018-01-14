package fhv.at.mwi.eight_puzzle;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class DFS_Solver {


    private Stack<PuzzleNode> nodes = new Stack<>();
    private LinkedList<PuzzleNode> _closedList = new LinkedList<>();

    public void solve(PuzzleNode node){
        System.out.println(node.toString());
        _closedList.add(node);
        if(node.isIdeal(PuzzleNode._idealState)){
            System.out.println("Found: " + node.toString());
            return;
        }
        LinkedList<PuzzleNode> neighbours = new LinkedList<>(node.getNeighbourNodes());
        for(int i = 0; i < neighbours.size(); i++){
            Iterator<PuzzleNode> cli = _closedList.iterator();
            while(cli.hasNext()){
                PuzzleNode nextNode = cli.next();
                if(!nextNode.isIdeal(neighbours.get(i).getMatrix())){
                    nodes.push(neighbours.get(i));
                }
            }
        }
        while(!nodes.isEmpty()){
            solve(nodes.pop());
        }

    }

}
