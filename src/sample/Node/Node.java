package sample.Node;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Node extends Rectangle implements Comparable<Node> {
    private final Font font = Font.font("Arial", FontWeight.BOLD, 14);
    public static int TIME_SWAP = 1000;  //ms

    private int value;
    private Text text;

    public Node() {

    }

    public Node(int value) {
        this.value = value;
        text = this.createText();
    }

    public int getValue() {
        return this.value;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Text createText() {
        Text text = new Text(String.valueOf(value));
        text.setFont(font);
        return text;
    }

    public TranslateTransition moveX(double x) {
        TranslateTransition t = new TranslateTransition();
        t.setNode(this);
        t.setDuration(Duration.millis(TIME_SWAP));
        t.setByX(x);
        return t;
    }

    public TranslateTransition moveTextX(double x) {
        TranslateTransition t = new TranslateTransition();
        t.setNode(this.text);
        t.setDuration(Duration.millis(TIME_SWAP));
        t.setByX(x);
        return t;
    }

    public TranslateTransition moveY(double y) {
        TranslateTransition t = new TranslateTransition();
        t.setNode(this);
        t.setDuration(Duration.millis(TIME_SWAP));
        t.setByY(y);
        return t;
    }
    public TranslateTransition moveTextY(double y) {
        TranslateTransition t = new TranslateTransition();
        t.setNode(this.text);
        t.setDuration(Duration.millis(TIME_SWAP));
        t.setByY(y);
        return t;
    }

    public ParallelTransition moveTo(double x, double y) {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(moveX(x), moveY(y));
        pt.getChildren().addAll(moveTextX(x), moveTextY(y));
        return pt;
    }

//    public TranslateTransition moveShapeTo(double x, double y) {
//        TranslateTransition t = new TranslateTransition();
//        t.setNode(this);
//        t.setDuration(Duration.millis(TIME_SWAP));
//        t.setByX(x - getX());
//        t.setByY(y - getY());
//        return t;
//    }
//
//    public TranslateTransition moveTextTo(double x, double y) {
//        TranslateTransition t = new TranslateTransition();
//        t.setNode(this.text);
//        t.setDuration(Duration.millis(TIME_SWAP));
//        t.setByX(x - getX());
//        t.setByY(y - getY());
//        return t;
//    }

    public Node clone() {
        Node newNode = new Node();
        newNode.value = this.value;
        newNode.text = this.getText();
        newNode.setX(getX());
        newNode.setY(getY());
        newNode.setWidth(getWidth());
        newNode.setHeight(getHeight());
        return newNode;
    }

    public ParallelTransition swap(Node other) {
        double deltaX = other.getX() - getX();
        double deltaY = other.getY() + other.getHeight() - (getY() + getHeight());
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(moveX(deltaX), moveY(deltaY));
        pt.getChildren().addAll(moveTextX(deltaX), moveTextY(deltaY));
        pt.getChildren().addAll(other.moveX(-deltaX), other.moveY(-deltaY));
//        pt.getChildren().addAll(other.moveTextX(-deltaX), other.moveTextY(-deltaY));
        return pt;
    }

    public String toString() {
        return "[" + value + "," + getX() + "," + getY() + "]";
    }

    public int compareTo(Node o) {
        if (getValue() < o.getValue()) {
            return -1;
        } else if (getValue() > o.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}
