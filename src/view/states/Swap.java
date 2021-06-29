package view.states;

import model.Candy;
import model.Game;
import view.Board;
import view.State;

public class Swap implements State {
    private static final String name = "Swap";
    protected Board board;
    private Game game;
    private Candy candy1, candy2;
    public String getName() {
        return name;
    }
    protected boolean allArrived() {
        return candy1.getView().arrived() && candy2.getView().arrived();
    }
    protected void step() {
        candy1.getView().step();
        candy2.getView().step();
    }
    private void print() {
        System.out.print("blank: ");
        for (int i = 0; i < board.getModel().nRows; i++)
            for (int j = 0; j < board.getModel().nColumns; j++) {
                if (!board.getModel().getBlock(i, j).hasCandy())
                    System.out.printf("(%d,%d)", i, j);
            }
        System.out.println("");
    }
    public State run() {
        if (!allArrived()) step();
        if (!allArrived()) return this;
        if (game.isValidSwap(candy1, candy2)) {
            board.getModel().clearClicked();
            if (game.isCombinable(candy1, candy2))
                game.combine(candy1, candy2);
            else
                game.clear(candy1, candy2);
            //return new WaitClick(board);
            return new Fall(board);
        }
        else
            return new UndoSwap(board);
    }
    public Swap(Board board) {
        this.board = board;
        this.game = board.getGame();
        this.candy1 = board.getClicked(0);
        this.candy2 = board.getClicked(1);
        candy1.switchPosition(candy2);
        candy1.getView().calculateNextPosition();
        candy2.getView().calculateNextPosition();
    }
}
