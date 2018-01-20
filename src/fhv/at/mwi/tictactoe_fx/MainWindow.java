package fhv.at.mwi.tictactoe_fx;

import fhv.at.mwi.tree_visualizer.Controls;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedList;

public class MainWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static final int WND_WIDTH = 450;                /* Window width... obviously */
    public static final int WND_HEIGHT = 480;               /* Window height */
    private static final LinkedList<String> OPTION_LIST = new LinkedList<>(); /* Option list for ChoiceBox */

    private GameController _gameController;
    private boolean _buttonState = true;

    @Override
    public void start(Stage primaryStage) {
        try {
            _gameController = new GameController(true, 1);
        } catch (GameControllerException e) {
            e.printStackTrace();
        }

        OPTION_LIST.add("Random");
        OPTION_LIST.add("MinMax");
        OPTION_LIST.add("AlphaBeta");
        OPTION_LIST.add("ZeroDepthHeuristic");
        Controls ribbonControls = new Controls(WND_WIDTH, 30);
        Button newGameButton = ribbonControls.addButton("Reset game");
        Button whoStartsButton = ribbonControls.addButton("Human starts");
        ChoiceBox modeBox = ribbonControls.addChoiceBox(OPTION_LIST);

        whoStartsButton.setOnAction(event -> {
            _buttonState = !_buttonState;
            if(_buttonState){
                whoStartsButton.setText("Human starts");
            } else{
                whoStartsButton.setText("Computer starts");
            }
            _gameController.setStart(_buttonState);
        });

        modeBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            try {
                _gameController.setStrategy(newValue.intValue());
            } catch (GameControllerException e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Game Controller Exception");
                error.setHeaderText("Invalid strategy");
                error.setContentText("Strategy must be a number between 0 and 3");
                error.showAndWait();
                e.printStackTrace();
            }
        });

        newGameButton.setOnAction(event -> _gameController.reset());

        Group controlGroup = ribbonControls.getControlsGroup();
        GridPane gameFieldGroup = _gameController.getGameFieldFx();


        VBox combi = new VBox(controlGroup, gameFieldGroup);

        Scene scene = new Scene(combi, WND_WIDTH, WND_HEIGHT, Color.TRANSPARENT);
        primaryStage.setTitle("TicTacToe Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
