package fhv.at.mwi.tictactoe;

import fhv.at.mwi.general.Position;

import java.util.PriorityQueue;

/**
 * Every move the opponent did is monitored to a 1d binary array.
 * This array is compared with all possible winning moves. At exactly the position
 * where differences between the opponent pattern and the winning pattern occur, these positions
 * are saved and marked as potential moves the opponent could do next. So one of these positions is taken
 * and that's where the computer set's the next move.
 * It doesn't calculates possible states for the next moves like the MinMax Algorithm does. That's why it's called
 * "Zero-depth". The heuristic is actually not a winning strategy, it's more like a passive defense strategy, since
 * it tries to block possible win streaks of the opponent.
 * Therefore it filters differences between winning patterns and the current pattern of the opponent. These
 * differences are evaluated with a heuristic that counts the number of matches between these two patterns. Differences
 * are saved as possible positions the algorithm should maybe block. After finding all possible block positions the
 * position with the best heuristic is taken and executed. (Execution actually takes place in the GameController)
 */
public class ZeroDepthHeuristicStrategy implements IGameStrategy{

    @Override
    public Position getStrategicMove(Field in) {
        /* Priority Queue to pick the position with the best heuristic (high heuristic is better) */
        PriorityQueue<Position> _predictedBlockingPositions = new PriorityQueue<>((o1, o2) -> (o2.getProperty() - o1.getProperty()));
        /* inverted pattern of the field that marks the opponents moves with 1 and your own moves with -1 */
        int[] opponentPattern = in.getHumanOpponentPattern();
        /* Check for positions and their heuristic that occur in difference of the opponent pattern and the winning
           pattern */
        for(int i = 0; i < Field._winPatterns.length; i++){
            /* Possible position to block with fitting heuristic */
            int[] evaluatedPosition = getBinaryDifferences(opponentPattern, Field._winPatterns[i]);
            int highestProbabilityPredictedPosition = evaluatedPosition[0];
            int heuristic = evaluatedPosition[1];
            /* If it is a valid position it will be added to the PQ */
            if(highestProbabilityPredictedPosition != -1){
                Position convertedPosition = Position.arrayIndexToPosition(highestProbabilityPredictedPosition, 3);
                convertedPosition.setProperty(heuristic);
                _predictedBlockingPositions.add(convertedPosition);
            }
        }
        /* Return the position with the best heuristic (highest number of the position property) */
        return _predictedBlockingPositions.poll();
    }

    private int[] getBinaryDifferences(int[] actualPattern, int[] predictedPattern){
        /* "patternMatchCount" counts how many positions of the winning pattern match the current pattern */
        int patternMatchCount = 0;
        /* the best position is pretty much a random position that the computer could set to prevent further
         * winning moves of the opponent */
        int bestPosition = -1;
        for(int i = 0; i < actualPattern.length; i++){
            /* If the opponent is trying to achieve the predictedPattern, the heuristic is increased
             * since it's more likely that the opponent will try to finish the winning pattern he started */
            if(actualPattern[i] == 1 && predictedPattern[i] == 1){
                patternMatchCount++;
            } else if(actualPattern[i] == 0 && predictedPattern[i] == 1){
                /* If a difference occurs, that means, that at this position could be the opponents next move,
                 * because it would (partly) complete the winning pattern */
                bestPosition = i;
            }
        }
        /* Return a possible position with the heuristic */
        return new int[]{
                bestPosition,
                patternMatchCount
        };
    }
}
