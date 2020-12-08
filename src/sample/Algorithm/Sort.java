package sample.Algorithm;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Node.Node;
import sample.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Sort {
    public static final Color START_COLOR = Color.web("#ADD8E6");
    public static final Color SELECT_COLOR = Color.web("#FFA500");
    public static final Color SORTED_COLOR = START_COLOR;

    static int DX;

    ParallelTransition colorNode(ArrayList<Node> arr, Color color, int a) {
        ParallelTransition pt = new ParallelTransition();
        for (int i = 0; i < arr.size(); i++) {
            FillTransition ft = new FillTransition();
            ft.setShape(arr.get(a));
            ft.setToValue(color);
            ft.setDuration(Duration.millis(Node.TIME_SWAP));
            pt.getChildren().add(ft);
        }
        return pt;
    }

    ParallelTransition colorNode(List<Node> list, Color color) {
        ParallelTransition pt = new ParallelTransition();
        for (Node myNode : list) {
            FillTransition ft = new FillTransition();
            ft.setShape(myNode);
            ft.setToValue(color);
            ft.setDuration(Duration.millis(100));
            pt.getChildren().add(ft);
        }

        return pt;
    }

    ParallelTransition swap(ArrayList<Node> arr, int i, int j) {
        ParallelTransition pt = new ParallelTransition();
        DX = Controller.DX;
        int dxFactor = j - i;

        pt.getChildren().addAll(arr.get(i).moveX(DX * dxFactor), arr.get(j).moveX(-DX * dxFactor));
        pt.getChildren().addAll(arr.get(i).moveTextX(DX * dxFactor), arr.get(j).moveTextX(-DX * dxFactor));

        Node tmp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, tmp);

        return pt;
    }


    public abstract ArrayList<Transition> startSort(ArrayList <Node> nodes);


    public static int[] randomArray(int size) {
        Random rd = new Random();
        int arr[] = new int[size];
        for (int i = 0; i < size; ++i) {
            arr[i] = rd.nextInt(size * 2 + 1);
        }
        return arr;
    }
}
