package fhv.at.mwi.tictactoe;

import fhv.at.mwi.general.Position;

public interface IGameStrategy {
    /**
     * Get the next move that the strategy calculates. It doesn't matter if this move is optimal.
     * @param in The current state of the game field.
     * @return The next position that should be set.
     */
    Position getStrategicMove(Field in);
}
