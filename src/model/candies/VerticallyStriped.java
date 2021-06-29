package model.candies;

import model.Block;
import model.Board;
import model.Candy;
import model.CandyGenerator;
import model.CandyMatrix;
import model.Matrix;

public class VerticallyStriped implements CandyGenerator {
    public Candy get(String color, Block prev, Block next) {
        return new VerticallyStripedCandy(color, prev, next);
    }
    public Candy get(String color, Block block) {
        return new VerticallyStripedCandy(color, block);
    }
}

class VerticallyStripedCandy extends Candy {
    private static final String name = "VerticallyStriped";
    @Override
    public void remove() {
        super.remove();
        if (!hasSpecialEffect) return;
        Board board = getBlock().getBoard();
        Matrix.Bound column =
            new Matrix.Bound(0, getBlock().column, Board.nRows, 1);
        CandyMatrix candies =
            new CandyMatrix(CandyMatrix.getArea(column), board);
        candies.remove();
    }
    public VerticallyStripedCandy(String color, Block block) {
        super(name, color, block, "StripedV");
    }
    public VerticallyStripedCandy(String color, Block prev, Block next) {
        super(name, color, prev, next, "StripedV");
    }
}
