package fhv.at.mwi.tictactoe_fx;

import fhv.at.mwi.general.Position;
import fhv.at.mwi.tictactoe.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Optional;

public class GameController {

    private Game _game;
    private GridPane _gameFieldFx;
    private Button[] _buttons;
    private String _humanPlayerFxStyle = "-fx-background-color: #11e06b";
    private String _algoPlayerFxStyle = "-fx-background-color: #e0156a";
    private boolean _humanStarts;
    private IGameStrategy _strategy;

    /**
     * GameController, that changes graphic elements and logic game elements
     * @param humanStarts True if the opponent (human) starts
     * @param strategy Strategy (int[0-3]; 0 = Random; 1 = MiniMax; 2 = AlphaBeta; 3 = 0-depth-heuristic)
     * @throws GameControllerException
     */
    public GameController(boolean humanStarts, int strategy) throws GameControllerException {
        _gameFieldFx = new GridPane();
        _humanStarts = humanStarts;
        setStrategy(strategy);
        _buttons = new Button[9];
        for(int i = 0; i < _buttons.length; i++){
            int index = i;
            _buttons[i] = new Button();
            _buttons[i].setPrefSize(150, 150);
            _buttons[i].setStyle("-fx-background-color: #E0E0E0");
            _buttons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    _buttons[index].setStyle(_humanPlayerFxStyle);
                    _buttons[index].setDisable(true);
                    _game.doMove(Position.arrayIndexToPosition(index, 3));

                    int gameState = _game.whoWon();
                    if(gameState == 2) {
                        update();
                        gameState = _game.whoWon();
                    }
                    checkGameState(gameState);
                }
            });
            Position p = Position.arrayIndexToPosition(i, 3);
            _gameFieldFx.add(_buttons[i], p.get_y(),p.get_x(),1,1);
        }
    }

    private void checkGameState(int state){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game state info");
        alert.setHeaderText("Slim shady is back");

        if(state == 0){
            messageAlert("Draw");
        } else if(state == 1){
            messageAlert("Computer won");
        } else if(state == -1){
            messageAlert("You won! I bet you won't beat me with the AlphaBeta Algorithm selected");
        }
    }

    /**
     * Set who starts with the game
     * @param humanStarts If set to true buttons are listening for human input,
     *                    If set to false the computer does the first move
     */
    public void setStart(boolean humanStarts){
        _humanStarts = humanStarts;
        resetGame(humanStarts, _strategy);
    }

    /**
     * Set the strategy for the current game the computer will use
     * @param strategy Strategy number (int[0-3]; 0 = Random; 1 = MiniMax; 2 = AlphaBeta; 3 = 0-depth-heuristic)
     * @throws GameControllerException
     */
    public void setStrategy(int strategy) throws GameControllerException {
        switch (strategy){
            case 0:
                _game = new Game(_humanStarts, new RandomStrategy());
                _strategy = new RandomStrategy();
                break;
            case 1:
                _game = new Game(_humanStarts, new MinMaxStrategy());
                _strategy = new MinMaxStrategy();
                break;
            case 2:
                _game = new Game(_humanStarts, new AlphaBetaStrategy());
                _strategy = new AlphaBetaStrategy();
                break;
            case 3:
                _game = new Game(_humanStarts, new ZeroDepthHeuristicStrategy());
                _strategy = new ZeroDepthHeuristicStrategy();
                break;
            default:
                throw new GameControllerException("Invalid strategy choice");
        }
    }

    /**
     * Check the game state and if someone won show the matching text in an alert box
     * @param text
     */
    private void messageAlert(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game state info");
        alert.setHeaderText("Slim shady is back");
        alert.setContentText(text);

        ButtonType exitButtonType = new ButtonType("Give up");
        alert.getButtonTypes().add(exitButtonType);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            resetGame(_humanStarts, _strategy);
        } else if(result.get() == exitButtonType){
            Stage stage = (Stage) _buttons[0].getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Resets the game and all graphic elements to the start state.
     * @param humanStarts  If set to true buttons are listening for human input,
     *                    If set to false the computer does the first move
     * @param strategy Interface for the chosen game strategy. Calls "getStrategicMove" after every human made move.
     */
    private void resetGame(boolean humanStarts, IGameStrategy strategy){
        for(int i = 0; i < _buttons.length; i++){
            _buttons[i].setStyle("-fx-background-color: #E0E0E0");
            _buttons[i].setDisable(false);
        }
        _game = new Game(humanStarts, strategy);
        if(!humanStarts){
            update();
        }
    }

    /**
     * Update the graphic elements and game elements according to the next calculated move
     */
    private void update(){
            int index = _game.doMove().positionToArrayIndex(3);
            _buttons[index].setStyle(_algoPlayerFxStyle);
            _buttons[index].setDisable(true);
    }

    public GridPane getGameFieldFx() {
        return _gameFieldFx;
    }

    /**
     * Reset the game with the previous configuration.
     */
    public void reset(){
        resetGame(_humanStarts, _strategy);
    }
}
