package model.rules;

import model.Block;
import model.Board;
import model.Candy;
import model.CandyMatrix;
import model.CombineRuleGenerator;
import model.Matrix;
import model.Rule;

public class Wrapped2 implements CombineRuleGenerator {
    @Override
    public Rule get(Candy candy1, Candy candy2) {
        return new Wrapped2Rule(candy1, candy2);
    }
}

class Wrapped2Rule implements Rule {
    public static final String name = "Wrapped2";
    private Candy candy1;
    private Candy candy2;
    public String getName() {
        return name;
    }
    @Override
    public boolean isMatched() {
        return candy1.is("Wrapped") && candy2.is("Wrapped");
    }
    @Override
    public void act() {
        Block block = candy1.getBlock();
        Board board = block.getBoard();
        Matrix.Bound bound =
            new Matrix.Bound(block.row-2, block.column-2, 5, 5);
        CandyMatrix candies =
            new CandyMatrix(CandyMatrix.getArea(bound), board);
            candy1.removeSpecialEffect();
            candy2.removeSpecialEffect();
        candies.remove();
    }
    public Wrapped2Rule(Candy candy1, Candy candy2) {
        this.candy1 = candy1;
        this.candy2 = candy2;
    }
}
