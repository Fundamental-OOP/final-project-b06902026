package model.rules;

import model.Block;
import model.Candy;
import model.CandyMatrix;
import model.MatchRuleGenerator;
import model.Matrix;
import model.Rule;
import model.candies.Wrapped;

public class WrappedMatch implements MatchRuleGenerator {
    public Rule get(Candy candy) {
        return new WrappedRule(candy);
    }
}

class WrappedRule implements Rule {
    public static final String name = "Wrapped";
    private Block pivot;
    private String color;
    private CandyMatrix candies;
    private Matrix match = null;
    private static final boolean[][][] matches = {{
            {true, true, true},
            {true, false, false},
            {true, false, false},
        },{
            {true, true, true},
            {false, true, false},
            {false, true, false},
        },{
            {true, true, true},
            {false, false, true},
            {false, false, true},
        },{
            {false, false, true},
            {true, true, true},
            {false, false, true},
        },{
            {false, false, true},
            {false, false, true},
            {true, true, true},
        },{
            {false, true, false},
            {false, true, false},
            {true, true, true},
        },{
            {true, false, false},
            {true, false, false},
            {true, true, true},
        },{
            {true, false, false},
            {true, true, true},
            {true, false, false},
        },
    };
    public String getName() {
        return name;
    }
    public boolean isMatched() {
        return getMatch() != null;
    }
    public Matrix getMatch() {
        if (match != null) return match;
        for (int i = 0; i < matches.length; i++) {
            Matrix matrix = new Matrix(matches[i]);
            if (candies.contains(matrix)) {
                match = matrix;
                return matrix;
            }
        }
        return null;
    }
    public void act() {
        candies.remove(match);
        Candy candy = (new Wrapped()).get(color, pivot);
        pivot.getBoard().newCandy(candy, pivot.row, pivot.column);
    }
    public WrappedRule(Candy pivot) {
        this.candies = new CandyMatrix(
            CandyMatrix.getConnectedSameColor(pivot.getBlock()),
            pivot.getBlock().getBoard());
        this.pivot = pivot.getBlock();
        this.color = pivot.getColor();
    }
}
