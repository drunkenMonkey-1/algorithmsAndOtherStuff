package fhv.at.mwi.tree_visualizer;

import fhv.at.mwi.general.EasyMath;
import fhv.at.mwi.general.Position;
import fhv.at.mwi.tree_structure.*;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.LinkedList;

public class TreeVisualizer implements Visualizer {

    private Button _insertButton;
    private TextField _valueTxtField;
    private Tree _tree;
    private PropertyTranslator _pt;
    private Group _treeGroup;
    private ValueParser<Comparable> _vp;
    private Position mid;
    private int xOffset = VisualizerWnd.NODE_RADIUS ;
    private int yOffset = VisualizerWnd.Y_OFFSET;

    private Rectangle _dummyShape = new Rectangle(0, 0, 0, 0);

    public TreeVisualizer(Tree initTree, PropertyTranslator pt, ValueParser vp){
        _treeGroup = new Group();
        _tree = initTree;
        _pt = pt;
        _vp = vp;
    }

    /**
     * Pretty f*cked up method... Here a small explanation so you don't need to read the code:
     * Goal: Simply draw a Binary Tree by a given Tree structure (_tree)
     * 1. Lvl-by-lvl output the the nodes of the tree (meta nodes) to a list
     * 2. Calculate the child nodes of the currently drawn node by adding and subtracting the
     *    xOffset * 2^(tree_height - current_level)
     * 3. Draw the root node after calculating the child nodes positions
     * 4. When finished drawing, add the connections to all possible following nodes
     */
    @Override
    public void redraw() {
        /* Remove all nodes from the previous tree */
        _treeGroup.getChildren().clear();
        _treeGroup.getChildren().add(_dummyShape);

        /* Add a listener to the insert button */
        if(_insertButton == null){
            _insertButton = VisualizerWnd._globalInsertButton;
            _valueTxtField = VisualizerWnd._globalValueText;
            _insertButton.setOnAction(event -> {
                if(!_valueTxtField.getText().equals("")) {
                    try {
                        _vp.parse(_valueTxtField.getText());
                        _tree.autoInsert(new RBNode<>(_vp.parse(_valueTxtField.getText())));
                    } catch (NumberFormatException e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Number Format Exception");
                        alert.setContentText("Invalid format (try using numbers)");
                        alert.showAndWait();
                    }
                }
            });
        }

        /* draw tree lvl by lvl */
        int lvls = _tree.getRBHeight();
        LinkedList<MetaNode> nodes = _tree.outputTree(OutputStrategy.LVLBYLVL);
        Position graphicPositions[] = new Position[EasyMath.power(2, lvls+1)];
        Position mid = new Position(VisualizerWnd.WND_WIDTH / 2, VisualizerWnd.TOP_OFFSET);
        graphicPositions[0] = mid;

        int currentLvl = 0;
        int lvlNodeIndex = 0;
        int nodeLimit = EasyMath.power(2, currentLvl);
        int previousLevelStartNode = 0;
        int gapOffset = VisualizerWnd.GAP_OFFSET;
        /* Iterate through the whole lvlbylvl output */
        for(MetaNode ignored :nodes){
            if(lvlNodeIndex == nodeLimit){

                currentLvl++;
                nodeLimit = EasyMath.power(2, currentLvl) + lvlNodeIndex;
                int i = 0;
                Position tmpGraphicPositions[] = new Position[EasyMath.power(2, lvls+1)];
                Position rootGraphicPositions[] = new Position[EasyMath.power(2, lvls+1)];
                int z = 0;
                while(i < EasyMath.power(2, lvls+1)){
                    if(graphicPositions[i] != null && nodes.get(i + previousLevelStartNode) != null
                            && nodes.get(i + previousLevelStartNode).getValue().equals("slim shady") == false) {

                        int multiplier = EasyMath.power(2, lvls-currentLvl);
                        // calculate pos for every sub node (child node)
                        tmpGraphicPositions[2 * i] = new Position(graphicPositions[i].get_x() - ((((multiplier) * xOffset) + gapOffset)), graphicPositions[i].get_y() + yOffset);
                        tmpGraphicPositions[2 * i + 1] = new Position(graphicPositions[i].get_x() + (((multiplier) * xOffset) + gapOffset), graphicPositions[i].get_y() + yOffset);

                        // draw line between current node and next node
                        Line lnL = new Line(graphicPositions[i].get_x(), graphicPositions[i].get_y(), tmpGraphicPositions[2 * i].get_x(), tmpGraphicPositions[2 * i].get_y());
                        Line lnR = new Line(graphicPositions[i].get_x(), graphicPositions[i].get_y(), tmpGraphicPositions[2 * i + 1].get_x(), tmpGraphicPositions[2 * i + 1].get_y());
                        _treeGroup.getChildren().add(lnL);
                        _treeGroup.getChildren().add(lnR);

                        // actually draw the node at x, y
                        setGraphicsNode(_vp.parseVtoString(nodes.get(i + previousLevelStartNode).getValue()), graphicPositions[i].get_x(),
                                                                                                              graphicPositions[i].get_y(),
                                        _pt.getColorByProperty(nodes.get(i + previousLevelStartNode).getProperty()));
                    }
                    i++;
                }
                graphicPositions = tmpGraphicPositions;
                previousLevelStartNode = lvlNodeIndex;
            }
            lvlNodeIndex++;
        }
    }

    /**
     * Simply draw a node with a given color, text and position
     * @param val Text in the middle of the node
     * @param x X Pos
     * @param y Y Pos
     * @param cl Color of the node (javafx.color)
     */
    private void setGraphicsNode(String val, int x, int y, Color cl){
        Circle c = new Circle(x, y, VisualizerWnd.NODE_RADIUS, cl);
        Text text = new Text(x - VisualizerWnd.TEXT_OFFSET, y + VisualizerWnd.TEXT_OFFSET, val);
        text.setFill(Color.WHITE);
        _treeGroup.getChildren().add(c);
        _treeGroup.getChildren().add(text);
    }

    /**
     * Return a group with all tree nodes and add the group to a window to actually draw them
     * @return Javafx Group with all nodes of the tree as "children"
     */
    public Group getTreeGroup(){
        return _treeGroup;
    }
}
