package fhv.at.mwi.tree_visualizer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VisualizerWnd extends Application {

    /**
     * Launch the java fx application
     * @param args Arguments to pass to javafx window. Probably empty for most cases
     * @param vsBoard Tree Visualizer you want to draw on the javafx wnd
     * @param title Window title
     */
    public static void showWnd(String[] args, TreeVisualizer vsBoard, String title) {
        _vsBoard = vsBoard;
        _title = title;
        launch(args);
    }

    /* Window and tree meta infos -> should be out-sourced to a config file */
    public static final int WND_WIDTH = 1080;               /* Window width... obviously */
    public static final int WND_HEIGHT = 720;               /* Window height */
    public static final int NODE_RADIUS = 10;               /* Radius of each tree node. 10 is good */
    public static final int TOP_OFFSET = 50;                /* Distance from the top to the root node */
    public static final int Y_OFFSET = 60;                  /* Distance between parent and child on y-Axis */
    public static final int GAP_OFFSET = 0;                 /* Additional gap offset between child nodes*/
    public static final int TEXT_OFFSET = 4;                /* Text offset for default font size and place
                                                               of text in the node mid. 4 should do the job*/

    /* Graphic Elements */
    private static TreeVisualizer _vsBoard;
    private static String _title;
    /* Button needs to accessible from Tree Visualizer so a listener can be added and the state can be queried */
    public static Button _globalInsertButton;
    public static TextField _globalValueText;

    @Override
    public void start(Stage primaryStage) {

        Controls ribbonControls = new Controls(WND_WIDTH, 30);
        _globalValueText = ribbonControls.addTextField("");
        _globalInsertButton = ribbonControls.addButton("Add");

        _vsBoard.redraw();

        Group controlGroup = ribbonControls.getControlsGroup();
        Group board =_vsBoard.getTreeGroup();

        ScrollPane scrollPane = new ScrollPane(board);
        scrollPane.setPrefSize(WND_WIDTH, WND_HEIGHT);
        scrollPane.setFitToWidth(true);



        VBox combi = new VBox(controlGroup, scrollPane);

        Scene scene = new Scene(combi, WND_WIDTH, WND_HEIGHT, Color.TRANSPARENT);

        scrollPane.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override public void handle(ScrollEvent event) {
                event.consume();

                if (event.getDeltaY() == 0) {
                    return;
                }

                double scaleFactor =
                        (event.getDeltaY() > 0)
                                ? 1.1
                                : 1/1.1;


                board.setScaleX(board.getScaleX() * scaleFactor);
                board.setScaleY(board.getScaleY() * scaleFactor);
            }
        });

        primaryStage.setTitle(_title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
