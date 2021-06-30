package model.rules;

import model.Block;
import model.Candy;
import model.CandyMatrix;
import model.MatchRuleGenerator;
import model.Matrix;
import model.Rule;
import model.candies.VerticallyStriped;

public class VerticallyStripedMatch implements MatchRuleGenerator {
    public Rule get(Candy candy) {
        return new VerticallyStripedRule(candy);
    }
}

class VerticallyStripedRule implements Rule {
    public static final String name = "VerticallyStriped";
    private Block pivot;
    private String color;
    private CandyMatrix candies;
    private static final boolean[][] vertical = {
        {true},
        {true},
        {true},
        {true},
    };
    private static final Matrix verticalMatrix = new Matrix(vertical);
    public String getName() {
        return name;
    }
    public boolean isMatched() {
        return candies.contains(verticalMatrix);
    }
    public void act() {
        candies.remove(verticalMatrix);
        Candy candy = (new VerticallyStriped()).get(color, pivot);
        pivot.getBoard().newCandy(candy, pivot.row, pivot.column);
    }
    public VerticallyStripedRule(Candy pivot) {
        this.candies = new CandyMatrix(
            CandyMatrix.getConnectedSameColor(pivot.getBlock()),
            pivot.getBlock().getBoard());
        this.pivot = pivot.getBlock();
        this.color = pivot.getColor();
    }
}
