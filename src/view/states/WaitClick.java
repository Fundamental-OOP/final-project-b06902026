package view.states;

import view.Board;
import view.Candy;
import view.State;

public class WaitClick implements State {
    private static final String name = "WaitClick";
    private Board board;
    public String getName() {
        return name;
    }
    public State run() {
        if (Candy.nClicked() == 2) return new Swap(board);
        else return this;
    }
    public WaitClick(Board board) {
        this.board = board;
    }
}
