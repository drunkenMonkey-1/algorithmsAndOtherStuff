package fhv.at.mwi.tictactoe;

import fhv.at.mwi.general.Position;

/**
 * AlphaBeta Pruning which is an optimization for the MinMaxStrategy. It allows us to "cut off" parts of our
 * implicit tree. So we don't need to go in more depth, since we can use alpha and beta to find out that no better
 * solution will be found if we traverse through the tree we want to cut off.
 * For example we are currently in the min method and now call max for all our neighbours. The first time we call
 * max all subtrees are searched in depth. After that, we got one minimum value of 10. That value was retrieved by the
 * max method called from the min method. So now we know that 10 is currently our "best" opportunity. Moving on to the
 * next neighbour in our min method, we pass 10 as beta. So now the max method will use the 10 as orientation. Max is
 * now getting all maximum neighbours. If the first neighbour for example is 12 we compare that value with 10. We see
 * that 12 is greater than 10, so that means for the next neighbour, that no smaller value will be retrieved and if a
 * greater value is retrieved that doesn't actually matter, since the min method, which is still in execution a level
 * above, only wants the smallest value. And that is 10. So if max retrieves a greater value that beta, we don't need
 * to continue our search.
 */
public class AlphaBetaStrategy implements IGameStrategy {
    private Position _nextMove;
    private Field _currentField;
    private int _returnDepth;

    @Override
    public Position getStrategicMove(Field in) {
        _currentField = in;
        _nextMove = null;
        _returnDepth = 8;
        int evaluation = max(1, _returnDepth, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return _nextMove;
    }

    /* Pretty much the same like the MinMaxStrategy. Uses alpha and beta as "interval"  */
    private int max(int player, int depth, int alpha, int beta){
        int maxValue = alpha;
        int heuristic = _currentField.didSomeoneWin();
        if(heuristic != 2 || depth == 0){
            return heuristic;
        } else{
            for(Position next : _currentField.getPossibleMoves()){
                _currentField.setPosition(next, player);
                int val = min(-player, depth-1, maxValue, beta);
                _currentField.unsetPosition(next);
                if(val > maxValue){
                    maxValue = val;
                    if(maxValue >= beta){
                        break;
                    }
                    if(depth == _returnDepth){
                        _nextMove = next;
                    }
                }
            }
        }
        return maxValue;
    }
    private int min(int player, int depth, int alpha, int beta){
        int minValue = beta;
        int heuristic = _currentField.didSomeoneWin();
        if(heuristic != 2 || depth == 0){
            return heuristic;
        } else{
            for(Position next : _currentField.getPossibleMoves()){
                _currentField.setPosition(next, player);
                int val = max(-player, depth-1, alpha, minValue);
                _currentField.unsetPosition(next);
                if(val < minValue){
                    minValue = val;
                    if(minValue <= alpha){
                        break;
                    }
                }
            }
        }
        return minValue;
    }
}
