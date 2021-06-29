package model.candies;

import model.Block;
import model.Candy;
import model.CandyGenerator;

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
        Block[] column = getBlock().getColumn();
        for (int i = 0; i < column.length; i++)
            column[i].removeCandy();
    }
    public VerticallyStripedCandy(String color, Block block) {
        super(name, color, block, "StripedV");
    }
    public VerticallyStripedCandy(String color, Block prev, Block next) {
        super(name, color, prev, next, "StripedV");
    }
}
