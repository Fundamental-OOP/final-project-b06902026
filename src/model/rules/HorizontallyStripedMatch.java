package model.rules;

import model.Block;
import model.Candy;
import model.CandyMatrix;
import model.MatchRuleGenerator;
import model.Matrix;
import model.Rule;
import model.candies.HorizontallyStriped;

public class HorizontallyStripedMatch implements MatchRuleGenerator {
    public Rule get(Candy candy) {
        return new HorizontallyStripedRule(candy);
    }
}

class HorizontallyStripedRule implements Rule {
    public static final String name = "HorizontallyStriped";
    private Block pivot;
    private String color;
    private CandyMatrix candies;
    private static final boolean[][] horizontal =
        {{true, true, true, true}};
    private static final Matrix horizontalMatrix = new Matrix(horizontal);
    public String getName() {
        return name;
    }
    public boolean isMatched() {
        return candies.contains(horizontalMatrix);
    }
    public void act() {
        candies.remove(horizontalMatrix);
        Candy candy = (new HorizontallyStriped()).get(color, pivot);
        pivot.getBoard().newCandy(candy, pivot.row, pivot.column);
    }
    public HorizontallyStripedRule(Candy pivot) {
        this.candies = new CandyMatrix(
            CandyMatrix.getConnectedSameColor(pivot.getBlock()),
            pivot.getBlock().getBoard());
        this.pivot = pivot.getBlock();
        this.color = pivot.getColor();
    }
}
