package sample.Algorithm;

import javafx.animation.Transition;
import javafx.scene.paint.Color;
import sample.Node.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapSort extends Sort{
    private static final Color ROOT_COLOR = Color.GREEN;
    private ArrayList<Transition> transitions;

    public HeapSort() {
        this.transitions = new ArrayList<>();
    }

    private void heapify(ArrayList<Node> arr, int i, int n) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;

        if (left < n && arr.get(max).getValue() < arr.get(left).getValue()) {
            max = left;
        }

        if (right < n && arr.get(max).getValue() < arr.get(right).getValue()) {
            max = right;
        }

        if (max != i) {
            transitions.add(swap(arr, i, max));
            heapify(arr, max, n);
        }

    }

    private void heapSort(ArrayList<Node> arr) {
        //build initial max heap
        transitions.add(colorNode(selectSubTree(arr, arr.size()), SELECT_COLOR));
        for (int i = arr.size() / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arr.size());
        }
        transitions.add(colorNode(selectSubTree(arr, arr.size()), START_COLOR));

        //swap root node with final elt, heapify subarray
        for (int i = arr.size() - 1; i > 0; i--) {
            transitions.add(colorNode(arr, ROOT_COLOR, 0));
            transitions.add(swap(arr, 0, i));
            transitions.add(colorNode(arr, START_COLOR, i));
            transitions.add(colorNode(selectSubTree(arr, i), SELECT_COLOR));
            heapify(arr, 0, i);
            transitions.add(colorNode(selectSubTree(arr, i), START_COLOR));
        }
    }

    private ArrayList<Node> selectSubTree(ArrayList<Node> arr, int n) {
        ArrayList<Node> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(arr.get(i));
        }

        return list;
    }

    @Override
    public ArrayList<Transition> startSort(ArrayList<Node> nodes) {
        heapSort(nodes);
        transitions.add(colorNode(nodes, START_COLOR));
        return transitions;
    }
}
