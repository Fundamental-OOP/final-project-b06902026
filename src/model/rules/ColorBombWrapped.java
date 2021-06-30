package model.rules;

import model.Board;
import model.Candy;
import model.CandyMatrix;
import model.CombineRuleGenerator;
import model.Rule;
import model.candies.Wrapped;

public class ColorBombWrapped implements CombineRuleGenerator {
    @Override
    public Rule get(Candy candy1, Candy candy2) {
        return new ColorBombWrappedRule(candy1, candy2);
    }
}

class ColorBombWrappedRule implements Rule {
    public static final String name = "ColorBombWrapped";
    private Candy candy1;
    private Candy candy2;
    public String getName() {
        return name;
    }
    public boolean isMatched(Candy candy1, Candy candy2) {
        return candy1.is("ColorBomb") && candy2.is("Wrapped");
    }
    @Override
    public boolean isMatched() {
        if (isMatched(candy1, candy2)) return true;
        if (isMatched(candy2, candy1)) return true;
        return false;
    }
    @Override
    public void act() {
        Candy colorBomb = candy1.is("ColorBomb")? candy1 : candy2;
        Candy wrapped = candy1.is("Wrapped")? candy1 : candy2;
        Board board = candy1.getBlock().getBoard();
        CandyMatrix candies =
            new CandyMatrix(
                CandyMatrix.getSameColor(board, wrapped.getColor()), board);
        colorBomb.removeSpecialEffect();
        colorBomb.remove();
        candies.set(new Wrapped(), wrapped.getColor());
        candies.remove();
    }
    public ColorBombWrappedRule(Candy candy1, Candy candy2) {
        this.candy1 = candy1;
        this.candy2 = candy2;
    }
}
