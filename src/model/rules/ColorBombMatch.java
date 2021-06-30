package model.rules;

import model.Block;
import model.Candy;
import model.CandyMatrix;
import model.MatchRuleGenerator;
import model.Matrix;
import model.Rule;
import model.candies.ColorBomb;

public class ColorBombMatch implements MatchRuleGenerator {
    public Rule get(Candy candy) {
        return new ColorBombRule(candy);
    }
}

class ColorBombRule implements Rule {
    public static final String name = "ColorBomb";
    private Block pivot;
    private CandyMatrix candies;
    private static final boolean[][] horizontal =
        {{true, true, true, true, true}};
    private static final boolean[][] vertical = {
        {true},
        {true},
        {true},
        {true},
        {true},
    };
    private static final Matrix horizontalMatrix = new Matrix(horizontal);
    private static final Matrix verticalMatrix = new Matrix(vertical);
    public String getName() {
        return name;
    }
    private boolean isHorizontal() {
        return candies.contains(horizontalMatrix);
    }
    private boolean isVertical() {
        return candies.contains(verticalMatrix);
    }
    public boolean isMatched() {
        return (isHorizontal() || isVertical());
    }
    public void act() {
        if (isHorizontal()) candies.remove(horizontalMatrix);
        if (isVertical()) candies.remove(verticalMatrix);
        Candy candy = (new ColorBomb()).get("", pivot);
        pivot.getBoard().newCandy(candy, pivot.row, pivot.column);
    }
    public ColorBombRule(Candy pivot) {
        this.candies = new CandyMatrix(
            CandyMatrix.getConnectedSameColor(pivot.getBlock()),
            pivot.getBlock().getBoard());
        this.pivot = pivot.getBlock();
    }
}
