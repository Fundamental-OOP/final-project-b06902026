package model.rules;

import model.Board;
import model.Candy;
import model.CandyMatrix;
import model.CombineRuleGenerator;
import model.Matrix;
import model.Rule;

public class ColorBomb2 implements CombineRuleGenerator {
    @Override
    public Rule get(Candy candy1, Candy candy2) {
        return new ColorBomb2Rule(candy1, candy2);
    }
}

class ColorBomb2Rule implements Rule {
    public static final String name = "ColorBomb2";
    private Candy candy1;
    private Candy candy2;
    public String getName() {
        return name;
    }
    @Override
    public boolean isMatched() {
        return candy1.is("ColorBomb") && candy2.is("ColorBomb");
    }
    @Override
    public void act() {
        Board board = candy1.getBlock().getBoard();
        board.removeAllSpecialEffect();
        Matrix.Bound bound =
            new Matrix.Bound(0, 0, Board.nRows, Board.nColumns);
        CandyMatrix candies =
            new CandyMatrix(CandyMatrix.getArea(bound), board);
        candies.remove();
    }
    public ColorBomb2Rule(Candy candy1, Candy candy2) {
        this.candy1 = candy1;
        this.candy2 = candy2;
    }
}
