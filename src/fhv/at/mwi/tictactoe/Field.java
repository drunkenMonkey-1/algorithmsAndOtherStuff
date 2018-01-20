package fhv.at.mwi.tictactoe;

import fhv.at.mwi.general.EasyMath;
import fhv.at.mwi.general.Position;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Field class for TicTacToe with 2 Players and a quadratic field
 */
public class Field {

    /* actual representation of the field in an 1d array */
    private int[] _fieldState;
    /* _fieldStateSize is the array length, _fieldSize is the square root of _fieldStateSize */
    private int _fieldStateSize, _fieldSize;
    /* number of moves made so far (max 9 for tictactoe) */
    private int _movesMade;
    /* Binary patterns to determine winning states */
    public static final int[][] _winPatterns = new int[][]{
            // horizontal lines
            {1,1,1,0,0,0,0,0,0},
            {0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,1,1,1},
            // vertical lines
            {1,0,0,1,0,0,1,0,0},
            {0,1,0,0,1,0,0,1,0},
            {0,0,1,0,0,1,0,0,1},
            // diagonal lines
            {1,0,0,0,1,0,0,0,1},
            {0,0,1,0,1,0,1,0,0},
    };

    /**
     * Create a new quadratic field representation with a given size
     * @param qSize Size of the quadratic field qSize * qSize
     */
    public Field(int qSize){
        _movesMade = 0;
        _fieldStateSize = qSize * qSize;
        _fieldSize = qSize;
        _fieldState = new int[_fieldStateSize];
        Arrays.fill(_fieldState, 0);
    }

    /**
     * Create a new quadratic field with a given position for a "game move"
     * @param qSize Size of the quadratic field qSize * qSize
     * @param initPosition Initial Position of the first move
     * @param player Player who does the initial move
     * @throws GameFieldException In case an invalid player or init position is given this exception is thrown
     */
    public Field(int qSize, Position initPosition, int player) throws GameFieldException {
        this(qSize);
        _movesMade = 1;
        if(!setPosition(initPosition, player)){
            throw new GameFieldException("Invalid player or init position for Field constructor");
        }
    }

    /**
     * Get positions for all possible moves on the field
     * @return List of all possible positions that are available for the next move of any player
     */
    public List<Position> getPossibleMoves(){
        List<Position> positions = new LinkedList<>();
        for(int i = 0; i < _fieldStateSize; i++){
            if(_fieldState[i] == 0){
                positions.add(Position.arrayIndexToPosition(i, _fieldSize));
            }
        }
        return positions;
    }

    /**
     * Set the marker for the player at the given position
     * @param setPos X and Y values for the position where the marker is set
     * @param player Player number (1, -1, or 0). 0 removes the move at the position
     * @return If the position and player number is valid true is returned.
     */
    public boolean setPosition(Position setPos, int player){
        int arrayIndex = isMoveValid(setPos, player);
        if(arrayIndex != -1){
            _fieldState[arrayIndex] = player;
            _movesMade++;
            return true;
        }
        return false;
    }

    /**
     * Remove the move from a position
     * @param unsetPos Position where a previous move will be removed
     * @return If the position is valid true is returned.
     */
    public boolean unsetPosition(Position unsetPos){
        _movesMade-=2;
        return setPosition(unsetPos, 0);
    }

    /**
     * Check if there are any possible moves left. DOESN'T return the number of moves left!!!
     * @return returns zero if there are no more moves left.
     */
    public int movesLeft(){
        if(_movesMade == _fieldStateSize) return 0;
        return didSomeoneWin();
    }

    /**
     * Returns the number of the player who won. For draw it returns 0
     * @return -1 for human, +1 for computer, 0 for draw, 2 for there are still moves left
     */
    public int didSomeoneWin(){
        int[] player1Pattern = new int[_fieldStateSize];
        int[] player2Pattern = new int[_fieldStateSize];
        for(int i = 0; i < _fieldStateSize; i++){
            if(_fieldState[i] == 1){
                player1Pattern[i] = 1;
            } else if(_fieldState[i] == -1){
                player2Pattern[i] = 1;
            } else{
                player1Pattern[i] = player2Pattern[i] = 0;
            }
        }
        boolean win = false;
        /* Compare the player patterns with the winning patterns */
        for(int i = 0; i < _winPatterns.length; i++){
            win = comparePatterns(player1Pattern, _winPatterns[i]);
            if(win) return 1;
            win = comparePatterns(player2Pattern, _winPatterns[i]);
            if(win) return -1;
        }
        /* draw -> no winning moves detected, but field is full */
        if(_movesMade == _fieldStateSize) {
            return 0;
        } else {
            return 2;
        }
    }

    /**
     * Compares winning patterns with a given pattern
     * @param pattern Given binary pattern, while 0's are ignored and 1*s used a indicator for valid moves
     * @param winPattern Win pattern for the given pattern. Same binary pattern setup like the param "pattern" has
     * @return True if the given pattern partly matches the winning pattern
     */
    private boolean comparePatterns(int[] pattern, int[] winPattern){
        int counter = 0;
        for(int i = 0; i < _fieldStateSize; i++){
            /* Win Pattern must exactly match the pattern */
            if(winPattern[i] == 1 && pattern[i] == 0) return false;
            /* Only valid match .. if a 1 occurs in "pattern" but not in the winning pattern it is ignored */
            else if(winPattern[i] == 1 && pattern[i] == 1){
                counter++;
            }
        }
        return (counter == 3);
    }

    /**
     * Checks if a move is valid regarding array/position bounds and player index
     * @param pos Position to check
     * @param player Player that tries to make a move
     * @return -1 if the move is invalid, 1d index of the 2d position if the move is valid
     */
    private int isMoveValid(Position pos, int player){
        int index = pos.positionToArrayIndex(_fieldSize);
        if(player > 1 || player < -1) return -1;
        if(index < 0 || index >= _fieldStateSize) return -1;
        return index;
    }

    /**
     * Get a binary pattern of the moves the opponent already did till now.
     * @return Binary pattern of the opponent's moves. 1 represents the opponent's moves, while
     *         -1 represents your own moves. And 0 represents empty fields.
     */
    public int[] getHumanOpponentPattern(){
        int[] playerPattern = new int[_fieldStateSize];
        for(int i = 0; i < _fieldStateSize; i++){
             if(_fieldState[i] == -1){
                playerPattern[i] = 1;
            } else if(_fieldState[i] == 1){
                 playerPattern[i] = -1;
            } else{
                playerPattern[i] = 0;
            }
        }
        return playerPattern;
    }

    @Override
    public String toString(){
        return Arrays.toString(_fieldState);
    }

}
