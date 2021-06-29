package model.rules;

import model.Block;
import model.Candy;
import model.CombineRuleGenerator;
import model.Rule;

public class WrappedHorizontallyStriped implements CombineRuleGenerator {
    @Override
    public Rule get(Candy candy1, Candy candy2) {
        return new WrappedHorizontallyStripedRule(candy1, candy2);
    }
}

class WrappedHorizontallyStripedRule implements Rule {
    public static final String name = "WrappedHorizontallyStriped";
    private Candy candy1;
    private Candy candy2;
    public String getName() {
        return name;
    }
    public boolean isMatched(Candy candy1, Candy candy2) {
        return candy1.is("Wrapped") && candy2.is("HorizontallyStriped");
    }
    @Override
    public boolean isMatched() {
        if (isMatched(candy1, candy2)) return true;
        if (isMatched(candy2, candy1)) return true;
        return false;
    }
    @Override
    public void act() {
        Block[][] rows = {
            candy1.getBlock().up().getRow(),
            candy1.getBlock().getRow(),
            candy1.getBlock().down().getRow(),
        };
        for (int i = 0; i < rows.length; i++)
            for (int j = 0; j < rows[i].length; j++) {
                rows[i][j].removeCandy();
            }
    }
    public WrappedHorizontallyStripedRule(Candy candy1, Candy candy2) {
        this.candy1 = candy1;
        this.candy2 = candy2;
    }
}
