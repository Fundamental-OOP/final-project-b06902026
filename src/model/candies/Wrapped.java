package model.candies;

import model.Block;
import model.Candy;
import model.CandyGenerator;
import model.CandyMatrix;
import model.Matrix;

public class Wrapped implements CandyGenerator {
    public Candy get(String color, Block prev, Block next) {
        return new WrappedCandy(color, prev, next);
    }
    public Candy get(String color, Block block) {
        return new WrappedCandy(color, block);
    }
}

class WrappedCandy extends Candy {
    private static final String name = "Wrapped";
    @Override
    public void remove() {
        super.remove();
        if (!hasSpecialEffect) return;
        Matrix.Bound bound =
            new Matrix.Bound(getBlock().row-1, getBlock().column-1, 3, 3);
        CandyMatrix surrounding =
            new CandyMatrix(CandyMatrix.getArea(bound), getBlock().getBoard());
        surrounding.remove();
    }
    public WrappedCandy(String color, Block block) {
        super(name, color, block, "Wrapped");
    }
    public WrappedCandy(String color, Block prev, Block next) {
        super(name, color, prev, next, "Wrapped");
    }
    
}
