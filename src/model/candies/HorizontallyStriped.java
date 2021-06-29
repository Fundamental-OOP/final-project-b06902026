package model.candies;

import model.Block;
import model.Board;
import model.Candy;
import model.CandyGenerator;
import model.CandyMatrix;
import model.Matrix;

public class HorizontallyStriped implements CandyGenerator {
    public Candy get(String color, Block prev, Block next) {
        return new HorizontallyStripedCandy(color, prev, next);
    }
    public Candy get(String color, Block block) {
        return new HorizontallyStripedCandy(color, block);
    }
}

class HorizontallyStripedCandy extends Candy {
    private static final String name = "HorizontallyStriped";
    @Override
    public void remove() {
        super.remove();
        if (!hasSpecialEffect) return;
        Board board = getBlock().getBoard();
        Matrix.Bound row =
            new Matrix.Bound(getBlock().row, 0, 1, Board.nColumns);
        CandyMatrix candies =
            new CandyMatrix(CandyMatrix.getArea(row), board);
        candies.remove();
    }
    public HorizontallyStripedCandy(String color, Block block) {
        super(name, color, block, "StripedH");
    }
    public HorizontallyStripedCandy(String color, Block prev, Block next) {
        super(name, color, prev, next, "StripedH");
    }
}
