package model.rules;

import model.Block;
import model.Board;
import model.Candy;
import model.CandyMatrix;
import model.CombineRuleGenerator;
import model.Matrix;
import model.Rule;

public class Striped2 implements CombineRuleGenerator {
    @Override
    public Rule get(Candy candy1, Candy candy2) {
        return new Striped2Rule(candy1, candy2);
    }
}

class Striped2Rule implements Rule {
    public static final String name = "Striped2";
    private Candy candy1;
    private Candy candy2;
    public String getName() {
        return name;
    }
    private boolean isStriped(Candy candy) {
        return candy.is("VerticallyStriped") || candy.is("HorizontallyStriped");
    }
    @Override
    public boolean isMatched() {
        return isStriped(candy1) && isStriped(candy2);
    }
    @Override
    public void act() {
        Block block = candy1.getBlock();
        Board board = block.getBoard();
        Matrix.Bound row =
            new Matrix.Bound(block.row, 0, 1, Board.nColumns);
        Matrix.Bound column =
            new Matrix.Bound(0, block.column, Board.nRows, 1);
        CandyMatrix candies =
            new CandyMatrix(CandyMatrix.getArea(row, column), board);
        candy1.removeSpecialEffect();
        candy2.removeSpecialEffect();
        candies.remove();
    }
    public Striped2Rule(Candy candy1, Candy candy2) {
        this.candy1 = candy1;
        this.candy2 = candy2;
    }
}
