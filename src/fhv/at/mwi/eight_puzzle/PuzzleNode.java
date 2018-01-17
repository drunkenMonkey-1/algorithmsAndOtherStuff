package fhv.at.mwi.eight_puzzle;

import fhv.at.mwi.general.Position;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PuzzleNode {

    private int[] _matrix;
    public static int[] _idealState;
    private int _holePos;
    private int _matrixDimension;
    private int _costs = 0;

    private PuzzleNode _parent;

    public PuzzleNode getParent() {
        return _parent;
    }

    public void setParent(PuzzleNode _parent) {
        this._parent = _parent;
    }

    public int[] getMatrix(){
        return _matrix;
    }

    public int getCosts() {
        return _costs;
    }

    public void setCosts(int _costs) {
        this._costs = _costs;
    }

    public PuzzleNode(int[] matrix){
        _matrix = matrix;
        _holePos = getHole();
        _idealState = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 0 };
        _matrixDimension = (int) Math.sqrt(_matrix.length);
    }

    private PuzzleNode(int[] matrix, int holePos){
        _matrix = matrix;
        _holePos = holePos;
        _idealState = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 0 };
        _matrixDimension = (int) Math.sqrt(_matrix.length);

    }

    public void loadStateFromFile(String path){

    }

    public void loadEndStateFromFile(String path){

    }

    public List<PuzzleNode> getNeighbourNodes(){
        int[] neighbourPositions = new int[4];
        int leftBorder = (_holePos / _matrixDimension) * _matrixDimension;
        int rightBorder = (_matrixDimension-1) + leftBorder;

        neighbourPositions[0] = _holePos+1;
        neighbourPositions[1] = _holePos-1;
        neighbourPositions[2] = _holePos+_matrixDimension;
        neighbourPositions[3] = _holePos-_matrixDimension;
        if(_holePos == rightBorder){
            neighbourPositions[0] = -1;
        }
        if(_holePos == leftBorder){
            neighbourPositions[1] = -1;
        }

        LinkedList<PuzzleNode> neigbourNodes = new LinkedList<>();
        for(int i = 0; i < neighbourPositions.length; i++){
            if(neighbourPositions[i] >= 0 && neighbourPositions[i] < _matrix.length){
                int[] modifiedMatrix = copyMatrix(_matrix);
                modifiedMatrix[_holePos] = modifiedMatrix[neighbourPositions[i]];
                modifiedMatrix[neighbourPositions[i]] = 0;
                PuzzleNode n = new PuzzleNode(modifiedMatrix, neighbourPositions[i]);
                neigbourNodes.add(n);
            }
        }
        return neigbourNodes;
    }

    private int[] copyMatrix(int[] matrix){
        int[] newMatrix = new int[matrix.length];
        for(int i = 0; i < matrix.length; i++){
            newMatrix[i] = matrix[i];
        }
        return newMatrix;
    }

    public int getManhattanDistance(){
        int manhattanDistance = 0;
        for(int i = 0; i < _matrix.length; i++){
            if(_matrix[i] != _idealState[i]){
                int currentX = i % _matrixDimension;
                int currentY = i / _matrixDimension;

                int goalPos = _matrix[i];
                int goalX = goalPos % _matrixDimension;
                int goalY = goalPos / _matrixDimension;

                manhattanDistance += (Math.abs(currentX-goalX) + Math.abs(currentY-goalY));
            }
        }
        return manhattanDistance;
    }

    public int getHammingDistance(){
        int wrongPositions = 0;
        for(int i = 0; i < _matrix.length; i++){
            if(_matrix[i] != _idealState[i] ){
                wrongPositions++;
            }
        }
        return wrongPositions;
    }

    public int getHole(){
        for(int i = 0; i < _matrix.length; i++){
            if(_matrix[i] == 0){
                return i;
            }
        }
        return -1;
    }

    public boolean isIdeal(int[] compare){
        for(int i = 0; i < _matrix.length; i++){
            if(_matrix[i] != compare[i]){
                return false;
            }
        }
        return true;
    }


    @Override
    public String toString(){
        StringBuilder out = new StringBuilder();
        int lineBreakCounter = 0;
        for(int i = 0; i < _matrix.length; i++){
            out.append(_matrix[i]);
            if(lineBreakCounter == _matrixDimension-1){
                out.append("\n");
                lineBreakCounter = -1;
            }
            lineBreakCounter++;
        }
        return out.toString() + "Costs: " + _costs + "\n";
    }

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        } else{
            PuzzleNode pn = (PuzzleNode) o;
            return Arrays.equals(_matrix, pn.getMatrix());
        }
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(_matrix);
    }



}
