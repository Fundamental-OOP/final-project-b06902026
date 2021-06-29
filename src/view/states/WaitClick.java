package view.states;

import view.Board;
import view.State;

public class WaitClick implements State {
    private static final String name = "WaitClick";
    private Board board;
    public String getName() {
        return name;
    }
    private void unclickCandy(int id) {
        board.getClicked(id).getView().unclick();
    }
    public State run() {
        // TODO: System.out.println(board.nClicked());
        if (board.getModel().nClicked() == 2) {
            unclickCandy(0);
            unclickCandy(1);
            // TODO: board.clearClicked();
            return new Swap(board);
        }
        else
            return this;
    }
    public WaitClick(Board board) {
        this.board = board;
    }
}
