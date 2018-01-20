package fhv.at.mwi.tictactoe;

import fhv.at.mwi.general.Position;

/**
 * OO layer between the Field class and the "TicTacToe" game.
 */
public class Game {

    private Field _currentFieldState;
    private int _currentPlayer;
    private IGameStrategy _strategy;

    /**
     * Create a new TicTacToe game with the configuration as constructor parameters
     * @param humanStarts False if the computer should start
     * @param gameStrategy Inteface for computer strategy
     */
    public Game(boolean humanStarts, IGameStrategy gameStrategy){
        _currentFieldState = new Field(3);
        if(humanStarts){
            _currentPlayer = -1;
        } else{
            _currentPlayer = 1;
        }
        _strategy = gameStrategy;
    }

    /**
     * Automatic calculation of the next move by the chosen algorithm aka "Game strategy"
     * @return Calculated Position
     */
    public Position doMove(){
        if(_currentPlayer == 1) {
            _currentPlayer = -_currentPlayer;
            Position algorithmMove = _strategy.getStrategicMove(_currentFieldState);
            _currentFieldState.setPosition(algorithmMove, 1);
            return algorithmMove;
        }
        return null;
    }

    /**
     * Execute a move from a given Position (usually coming from the graphic Controller)
     * @param pos Position where the next move of the human player is set
     * @return True if it didn't violate the game rules
     */
    public boolean doMove(Position pos){
        if(_currentPlayer == -1) {
            _currentPlayer = -_currentPlayer;
            return _currentFieldState.setPosition(pos, -1);
        }
        return false;
    }

    public int getCurrentPlayer() {
        return _currentPlayer;
    }

    public String printField(){
        return _currentFieldState.toString();
    }

    /**
     * Returns who won the game
     * @return -1 = human; +1 = computer; 0 = draw; 2 = moves left -> no clear result
     */
    public int whoWon(){
        return  _currentFieldState.didSomeoneWin();
    }
}
