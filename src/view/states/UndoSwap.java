package view.states;

import view.Board;
import view.State;

public class UndoSwap extends Swap {
    private static final String name = "UndoSwap";
    public String getName() {
        return name;
    }
    @Override
    public State run() {
        if (!allArrived()) step();
        if (!allArrived()) return this;
        view.Candy.clearClicked();
        return new WaitClick(board);
    }
    public UndoSwap(Board board) {
        super(board);
    }
}
