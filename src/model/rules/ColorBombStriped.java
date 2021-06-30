package model.rules;

import model.Board;
import model.Candy;
import model.CandyMatrix;
import model.CombineRuleGenerator;
import model.Rule;
import model.candies.Striped;

public class ColorBombStriped implements CombineRuleGenerator {
    @Override
    public Rule get(Candy candy1, Candy candy2) {
        return new ColorBombStripedRule(candy1, candy2);
    }
}

class ColorBombStripedRule implements Rule {
    public static final String name = "ColorBombStriped";
    private Candy candy1;
    private Candy candy2;
    public String getName() {
        return name;
    }
    public boolean isStriped(Candy candy) {
        return candy.is("VerticallyStriped") || candy.is("HorizontallyStriped");
    }
    public boolean isMatched(Candy candy1, Candy candy2) {
        return candy1.is("ColorBomb") && isStriped(candy2);
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
        Candy striped = isStriped(candy1)? candy1 : candy2;
        Board board = candy1.getBlock().getBoard();
        CandyMatrix candies =
            new CandyMatrix(
                CandyMatrix.getSameColor(board, striped.getColor()), board);
        colorBomb.removeSpecialEffect();
        colorBomb.remove();
        candies.set(new Striped(), striped.getColor());
        candies.remove();
    }
    public ColorBombStripedRule(Candy candy1, Candy candy2) {
        this.candy1 = candy1;
        this.candy2 = candy2;
    }
}
