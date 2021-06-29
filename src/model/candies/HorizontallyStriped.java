package model.candies;

import model.Block;
import model.Candy;
import model.CandyGenerator;

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
        Block[] row = getBlock().getRow();
        for (int i = 0; i < row.length; i++)
            row[i].removeCandy();
    }
    public HorizontallyStripedCandy(String color, Block block) {
        super(name, color, block, "StripedH");
    }
    public HorizontallyStripedCandy(String color, Block prev, Block next) {
        super(name, color, prev, next, "StripedH");
    }
}
