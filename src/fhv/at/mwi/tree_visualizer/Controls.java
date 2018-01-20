package fhv.at.mwi.tree_visualizer;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;
import java.util.List;

public class Controls {

    private Group _controlsGroup;
    private GridPane ribbonGridPane;
    private int _counter = 0;

    public Controls(int width, int ribbonSize){

        _controlsGroup = new Group();

        //Layout
        ribbonGridPane = new GridPane();
        ribbonGridPane.setVgap(3);
        ribbonGridPane.setHgap(3);
        ribbonGridPane.setMinSize(width, ribbonSize);
        ribbonGridPane.setPadding(new Insets(3, 3, 3, 3));
    }

    public Button addButton(String btnText){
        Button newBtn = new Button(btnText);
        ribbonGridPane.add(newBtn, _counter++, 0);
        return newBtn;
    }
    public TextField addTextField(String fieldDefaultText){
        TextField newField = new TextField(fieldDefaultText);
        ribbonGridPane.add(newField, _counter++, 0);
        return newField;
    }

    public ChoiceBox addChoiceBox(List<String> options){
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(options));
        ribbonGridPane.add(cb, _counter++, 0);
        return cb;
    }

    public Group getControlsGroup(){
        _controlsGroup.getChildren().add(ribbonGridPane);
        return _controlsGroup;
    }
}
