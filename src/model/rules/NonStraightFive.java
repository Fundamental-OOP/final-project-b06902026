package model.rules;

import model.Block;
import model.Candy;
import model.CandyMatrix;
import model.MatchRuleGenerator;
import model.Rule;
import model.candies.Wrapped;

public class NonStraightFive implements MatchRuleGenerator {
    public Rule get(Candy candy) {
        return new NonStraightFiveRule(candy);
    }
}

class NonStraightFiveRule implements Rule {
    public static final String name = "NonStraightFive";
    private Block pivot;
    private String color;
    private CandyMatrix candies;
    public String getName() {
        return name;
    }
    public boolean isMatched() {
        return candies.nOnes() >= 5;
    }
    public void act() {
        candies.remove();
        Candy candy = (new Wrapped()).get(color, pivot);
        pivot.getBoard().newCandy(candy, pivot.row, pivot.column);
    }
    public NonStraightFiveRule(Candy pivot) {
        this.candies = new CandyMatrix(
            CandyMatrix.getConnectedSameColor(pivot.getBlock()),
            pivot.getBlock().getBoard());
        this.pivot = pivot.getBlock();
        this.color = pivot.getColor();
    }
}
