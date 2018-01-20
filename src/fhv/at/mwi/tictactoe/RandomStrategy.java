package fhv.at.mwi.tictactoe;

import fhv.at.mwi.general.Position;

import java.util.List;
import java.util.Random;

/**
 * Generates a random move. Pretty useless.
 */
public class RandomStrategy implements IGameStrategy {
    @Override
    public Position getStrategicMove(Field in) {
        List<Position> nextMoves = in.getPossibleMoves();
        Random random = new Random();
        int randomIndex = random.nextInt(nextMoves.size()) ;
        return nextMoves.get(randomIndex);
    }
}
