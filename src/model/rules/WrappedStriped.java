package model.rules;

import model.Block;
import model.Board;
import model.Candy;
import model.CandyMatrix;
import model.CombineRuleGenerator;
import model.Matrix;
import model.Rule;

public class WrappedStriped implements CombineRuleGenerator {
    @Override
    public Rule get(Candy candy1, Candy candy2) {
        return new WrappedStripedRule(candy1, candy2);
    }
}

class WrappedStripedRule implements Rule {
    public static final String name = "WrappedStriped";
    private Candy candy1;
    private Candy candy2;
    public String getName() {
        return name;
    }
    public boolean isMatched(Candy candy1, Candy candy2) {
        return candy1.is("Wrapped") &&
            (candy2.is("VerticallyStriped") || candy2.is("HorizontallyStriped"));
    }
    @Override
    public boolean isMatched() {
        if (isMatched(candy1, candy2)) return true;
        if (isMatched(candy2, candy1)) return true;
        return false;
    }
    @Override
    public void act() {
        Block block = candy1.getBlock();
        Board board = block.getBoard();
        Matrix.Bound rows =
            new Matrix.Bound(block.row-1, 0, 3, Board.nColumns);
        Matrix.Bound columns =
            new Matrix.Bound(0, block.column-1, Board.nRows, 3);
        CandyMatrix candies =
            new CandyMatrix(CandyMatrix.getArea(rows, columns), board);
        candy1.removeSpecialEffect();
        candy2.removeSpecialEffect();
        candies.remove();
    }
    public WrappedStripedRule(Candy candy1, Candy candy2) {
        this.candy1 = candy1;
        this.candy2 = candy2;
    }
}
