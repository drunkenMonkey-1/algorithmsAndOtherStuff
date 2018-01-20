package fhv.at.mwi.tictactoe;

import fhv.at.mwi.general.Position;

/**
 * Basic MinMax Algorithm without any optimization.
 * How it works: max retrieves all game states that return a maximized score. A score of +1 means that we (the computer)
 * win. So thats the goal we want to reach. If we can't reach +1, 0 is the next greater value for evaluating the next
 * best move. -1 means that the opponent will win, so that's something the max method avoids. While the min value
 * searches for game states that would mean that the opponent wins. That means that this algorithm searches for
 * the maximum score the computer can get in it's next move, assuming that the opponent plays perfect.
 */
public class MinMaxStrategy implements IGameStrategy {

    private Position _nextMove;
    private Field _currentField;
    private int _returnDepth;

    private int max(int player, int depth){
        /* Set to the smallest possible value, so every greater value can be found */
        int maxValue = Integer.MIN_VALUE;
        /* Returns +1 if the computer wins, -1 if the human wins, 0 for draw */
        int heuristic = _currentField.didSomeoneWin();
        /* 2 means that there are no more moves left, if the depth is 0 the maximum search depth was reached */
        if(heuristic != 2 || depth == 0){
            /* Return the score of the leaf */
            return heuristic;
        } else{
            /* Iterate over all possible neighbours from the current game state */
            for(Position next : _currentField.getPossibleMoves()){
                /* Set the next position and calculate the score recursive */
                _currentField.setPosition(next, player);
                /* Recursive call of min, so the minimum score of the next position is calculated */
                /* Decrease depth since we go down one layer */
                int val = min(-player, depth-1);
                /* Backtracking like removal, since we want to maintain the original game field state */
                _currentField.unsetPosition(next);
                /* Simple max value finder. If there is any greater value than the one before we will use */
                /* the greater one, since we are in the max method */
                if(val > maxValue){
                    maxValue = val;
                    /* When we iterated through all neighbours from the root node, we are back the "starting" level */
                    /* So we know a possible next state and save it */
                    if(depth == _returnDepth){
                        _nextMove = next;
                    }
                }
            }
        }
        return maxValue;
    }

    private int min(int player, int depth){
        /* minValue needs to be the integer maximum so any smaller can be found */
        int minValue = Integer.MAX_VALUE;
        int heuristic = _currentField.didSomeoneWin();
        if(heuristic != 2 || depth == 0){
            return heuristic;
        } else{
            for(Position next : _currentField.getPossibleMoves()){
                _currentField.setPosition(next, player);
                /* Call max now, since we want the maximum score our neighbours will retrieve */
                int val = max(-player, depth-1);
                _currentField.unsetPosition(next);
                if(val < minValue){
                    minValue = val;
                }
            }
        }
        /* Return the smallest value from all the neighbours (the leafs will be the first ones who return something) */
        return minValue;
    }

    @Override
    public Position getStrategicMove(Field in) {
        _currentField = in;
        _nextMove = null;
        _returnDepth = 8;
        /* int value saved for debugging, calls max with a maximum search depth of "_returnDepth" */
        int evaluation = max(1, _returnDepth);
        /* If the start field has possible neighbours the maximum score move is returned */
        return _nextMove;
    }
}
