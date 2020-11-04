package mcts.tree;

import java.util.*;

import mcts.montecarlo.State;

public class Node {
    State state;
    Node parent;
    List<Node> childArray;
    UUID id = UUID.randomUUID();

    public Node() {
        this.state = new State();
        childArray = new ArrayList<>();
    }

    public Node(State state) {
        this.state = state;
        childArray = new ArrayList<>();
    }

    public Node(State state, Node parent, List<Node> childArray) {
        this.state = state;
        this.parent = parent;
        this.childArray = childArray;
    }

    public Node(Node node) {
        this.childArray = new ArrayList<>();
        this.state = new State(node.getState());
        if (node.getParent() != null)
            this.parent = node.getParent();
        List<Node> childArray = node.getChildArray();
        for (Node child : childArray) {
            this.childArray.add(new Node(child));
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildArray() {
        return childArray;
    }

    public void setChildArray(List<Node> childArray) {
        this.childArray = childArray;
    }

    public Node getRandomChildNode() {
        int noOfPossibleMoves = this.childArray.size();
        int selectRandom = (int) (Math.random() * noOfPossibleMoves);
        return this.childArray.get(selectRandom);
    }

    public Node getChildWithMaxScore() {
        System.out.println(this.id + ": " + this.state.getVisitCount() + " -> " + this.state.getWinScore());
        childArray.forEach(c -> System.out.println(c.id + ": " + c.getState().getVisitCount() + " -> "+ c.getState().getWinScore()));
        return Collections.max(this.childArray, Comparator.comparing(c -> c.getState().getVisitCount()));
    }

    public String toString() {
        return this.getState().getWinScore() + " -> " + this.getState().getVisitCount() + "\n" + Arrays.deepToString(this.getState().getBoard().getBoardValues()) + ":\n" + this.childArray;
    }

    public void printAncestry() {
        Node node = this;
        while (node != null) {
            System.out.print(node.id.toString() + " <- ");
            node = node.getParent();
        }
    }

}
