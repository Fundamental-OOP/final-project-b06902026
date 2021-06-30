package model.candies;

import java.util.Random;

import model.Block;
import model.Candy;
import model.CandyGenerator;

public class Striped implements CandyGenerator {
    private final Random random = new Random();
    public Candy get(String color, Block prev, Block next) {
        if (random.nextInt(2) == 0)
            return new VerticallyStripedCandy(color, prev, next);
        else
            return new HorizontallyStripedCandy(color, prev, next);
    }
    public Candy get(String color, Block block) {
        if (random.nextInt(2) == 0)
            return new VerticallyStripedCandy(color, block);
        else
            return new HorizontallyStripedCandy(color, block);
    }
}