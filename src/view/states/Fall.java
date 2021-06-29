package view.states;

import model.Candy;
import view.Board;
import view.State;

public class Fall implements State {
    private static final String name = "Fall";
    private Board board;
    public String getName() {
        return name;
    }
    protected boolean allArrived() {
        for (int i = 0; i < model.Board.nRows; i++)
            for (int j = 0; j < model.Board.nColumns; j++) {
                Candy candy = board.getModel().getBlock(i, j).getCandy();
                if (!candy.getView().arrived()) return false;
            }
        return true;
    }
    protected void step() {
        for (int i = 0; i < model.Board.nRows; i++)
            for (int j = 0; j < model.Board.nColumns; j++) {
                Candy candy = board.getModel().getBlock(i, j).getCandy();
                candy.getView().step();
            }
    }
    public State run() {
        if (!allArrived()) step();
        if (!allArrived()) return this;
        return new WaitClick(board);
    }
    public Fall(Board board) {
        this.board = board;
        board.getModel().fall();
        board.getModel().fillBlank();
    }
}