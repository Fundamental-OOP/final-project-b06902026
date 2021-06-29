package view.states;

import view.Board;
import view.State;

public class ClearMatched implements State {
    private static final String name = "ClearMatched";
    private Board board;
    public String getName() {
        return name;
    }
    public State run() {
        return new Fall(board);
    }
    public ClearMatched(Board board) {
        this.board = board;
        board.getGame().clear(board.getModel());
    }
}
