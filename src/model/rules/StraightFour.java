package model.rules;

import model.Block;
import model.Candy;
import model.CandyMatrix;
import model.MatchRuleGenerator;
import model.Matrix;
import model.Rule;
import model.candies.HorizontallyStriped;
import model.candies.VerticallyStriped;

public class StraightFour implements MatchRuleGenerator {
    public Rule get(Candy candy) {
        return new StraightFourRule(candy);
    }
}

class StraightFourRule implements Rule {
    public static final String name = "StraightFour";
    private Block pivot;
    private String color;
    private CandyMatrix candies;
    private static final boolean[][] horizontal =
        {{true, true, true, true}};
    private static final boolean[][] vertical = {
        {true},
        {true},
        {true},
        {true},
    };
    private static final Matrix horizontalMatrix = new Matrix(horizontal);
    private static final Matrix verticalMatrix = new Matrix(vertical);
    private boolean isHorizontal() {
        return candies.contains(horizontalMatrix);
    }
    private boolean isVertical() {
        return candies.contains(verticalMatrix);
    }
    public String getName() {
        return name;
    }
    public boolean isMatched() {
        return (isHorizontal() || isVertical());
    }
    public void act() {
        if (isHorizontal()) {
            candies.remove(horizontalMatrix);
            Candy candy = (new HorizontallyStriped()).get(color, pivot);
            pivot.getBoard().newCandy(candy, pivot.row, pivot.column);
        }
        if (isVertical()) {
            candies.remove(verticalMatrix);
            Candy candy = (new VerticallyStriped()).get(color, pivot);
            pivot.getBoard().newCandy(candy, pivot.row, pivot.column);
        }
    }
    public StraightFourRule(Candy pivot) {
        this.candies = new CandyMatrix(
            CandyMatrix.getConnectedSameColor(pivot.getBlock()),
            pivot.getBlock().getBoard());
        this.pivot = pivot.getBlock();
        this.color = pivot.getColor();
    }
}
