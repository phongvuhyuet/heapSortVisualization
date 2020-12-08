package sample.Animation;

import javafx.scene.text.Text;
import sample.Node.Node;
import sample.Algorithm.Sort;
import sample.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControlNodes {
    private static List<Text> texts = new ArrayList<>();

    public ControlNodes() {

    }

    public List<Text> getTexts() {
        return texts;
    }

    public static void setTextCoordinates(Node node) {
        Text text = node.getText();
        double textSize = text.getBoundsInLocal().getWidth();
        double delta = (node.getWidth() - textSize) / 2;
        text.setX(node.getX() + delta);
        text.setY(node.getY() - 5);
        node.setText(text);
    }

    public static List<Text> getTexts(ArrayList<Node> nodes) {
        List<Text> texts = new ArrayList<>();
        for (Node node : nodes) {
            texts.add(node.getText());
        }
        return texts;
    }

    public static Node[] randomNodes(int numOfNodes) {
        Node[] arr = new Node[numOfNodes];
        Random r = new Random();

        for (int i = 0; i < numOfNodes; i++) {
            arr[i] = new Node(1 + r.nextInt(numOfNodes));
            arr[i].setX(i * (Controller.WINDOW_WIDTH / numOfNodes));
            arr[i].setFill(Sort.START_COLOR);
            setNode(arr[i], numOfNodes, numOfNodes);
            setTextCoordinates(arr[i]);
        }
        return arr;
    }

    public static Node[] randomNodes(int numOfNodes, int min, int max) {
        Node[] arr = new Node[numOfNodes];
        Random r = new Random();

        for (int i = 0; i < numOfNodes; i++) {
            arr[i] = new Node(r.nextInt(max - min + 1) + min);
            arr[i].setX(i * (Controller.WINDOW_WIDTH / numOfNodes));
            arr[i].setFill(Sort.START_COLOR);
            setNode(arr[i], numOfNodes, max);
            setTextCoordinates(arr[i]);
        }
        return arr;
    }

    public static Node[] colorNodes(Node[] arr) {
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            arr[i].setX(i * (Controller.WINDOW_WIDTH / size));
            arr[i].setFill(Sort.START_COLOR);
            setNode(arr[i], size, getMaxValue(arr));
            setTextCoordinates(arr[i]);
        }
        return arr;
    }

    public static int getMaxValue(Node[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i].getValue() > max) {
                max = arr[i].getValue();
            }
        }
        return max;
    }

    public static void setNode(Node myNode, int n, int max) {
        int width = Controller.WINDOW_WIDTH;
        int height = Controller.WINDOW_HEIGHT;
        int xGap = Controller.XGAP;
        int bottom = Controller.BOTTOM_BOUNDARY;
        int top = Controller.TOP_BOUNDARY;

        myNode.setWidth(width / n - xGap);
        myNode.setHeight((height - bottom - top) / max * myNode.getValue());
        myNode.setY(Controller.BASE_LINE - myNode.getHeight());
    }
}
