package sample;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.Algorithm.HeapSort;
import sample.Algorithm.Sort;
import sample.Animation.ControlNodes;
import sample.Node.Node;

import java.util.ArrayList;

public class Controller {
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 600;
    public static final int XGAP = 10;
    public static final int BOTTOM_BOUNDARY = 50;
    public static final int NO_OF_NODES = 20;
    public static final int BASE_LINE = 500;
    public static final int TOP_BOUNDARY = 100;
    public static int DX = WINDOW_WIDTH / NO_OF_NODES;

    private HeapSort sort = new HeapSort();

    private ArrayList <Node> nodes = new ArrayList<>(0);
    private SequentialTransition sq;

    @FXML
    private AnchorPane Pane;

    @FXML
    private Button btnSort;

    @FXML
    private TextField numText;

    @FXML
    private Button btnAdd;

    @FXML
    void AddHandle(ActionEvent event) {
        String text = numText.getText();
        int value = Integer.parseInt(text);
        nodes.add(new Node(value));
        int max = -10000;
        for (int i =0; i<nodes.size(); i++) {
            if (nodes.get(i).getValue() > max) max = nodes.get(i).getValue();
        }
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).setX(i * (WINDOW_WIDTH / nodes.size()));
            nodes.get(i).setFill(Sort.START_COLOR);
            ControlNodes.setNode(nodes.get(i), nodes.size(), max);
            ControlNodes.setTextCoordinates(nodes.get(i));
        }
        Pane.getChildren().clear();
        Pane.getChildren().addAll(nodes);
        Pane.getChildren().addAll(ControlNodes.getTexts(nodes));
        DX = WINDOW_WIDTH / nodes.size();
    }

    @FXML
    void SortHandle(ActionEvent event) {
        sq = new SequentialTransition();
        sq.getChildren().addAll(sort.startSort(nodes));
        sq.play();
    }

}

