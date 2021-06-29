package model.candies;

import model.Block;
import model.Board;
import model.Candy;
import model.CandyGenerator;

public class ColorBomb implements CandyGenerator {
    public Candy get(String color, Block prev, Block next) {
        return new ColorBombCandy(prev, next);
    }
    public Candy get(String color, Block block) {
        return new ColorBombCandy(block);
    }
}

class ColorBombCandy extends Candy {
    private static final String name = "ColorBomb";
    public void remove(String color) {
        super.remove();
        Board board = getBlock().getBoard();
        for (int i = 0; i < Board.nRows; i++)
            for (int j = 0; j < Board.nColumns; j++) {
                Candy candy = board.getBlock(i, j).getCandy();
                if (candy.isSameColor(color))
                    candy.remove();
            }
    }
    @Override
    public void remove() {
        // TODO: color of the most candies
        String color = "Blue";
        remove(color);
    }
    public void remove(Candy candy) {
        remove(candy.getColor());
    }
    public ColorBombCandy(Block block) {
        super(name, "", block, "ColorBomb");
    }
    public ColorBombCandy(Block prev, Block next) {
        super(name, "", prev, next, "ColorBomb");
    }
}
