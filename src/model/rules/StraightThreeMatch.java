package model.rules;

import model.Candy;
import model.CandyMatrix;
import model.MatchRuleGenerator;
import model.Matrix;
import model.Rule;

public class StraightThreeMatch implements MatchRuleGenerator {
    public Rule get(Candy candy) {
        return new StraightThreeRule(candy);
    }
}

class StraightThreeRule implements Rule {
    public static final String name = "StraightThree";
    private CandyMatrix candies;
    private static final boolean[][] horizontal =
        {{true, true, true}};
    private static final boolean[][] vertical = {
        {true},
        {true},
        {true},
    };
    private static final Matrix horizontalMatrix = new Matrix(horizontal);
    private static final Matrix verticalMatrix = new Matrix(vertical);
    private boolean isHorizontal;
    private boolean isVertical;
    public String getName() {
        return name;
    }
    public boolean isMatched() {
        return (isHorizontal || isVertical);
    }
    public void act() {
        if (isHorizontal) candies.remove(horizontalMatrix);
        if (isVertical) candies.remove(verticalMatrix);
    }
    public StraightThreeRule(Candy pivot) {
        this.candies = new CandyMatrix(
            CandyMatrix.getConnectedSameColor(pivot.getBlock()),
            pivot.getBlock().getBoard());
        this.isHorizontal = candies.contains(horizontalMatrix);
        this.isVertical = candies.contains(verticalMatrix);
    }
}
