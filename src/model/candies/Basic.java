package model.candies;

import model.Block;
import model.Candy;
import model.CandyGenerator;

public class Basic implements CandyGenerator {
    public Candy get(String color, Block prev, Block next) {
        return new Candy("Basic", color, prev, next);
    }
    public Candy get(String color, Block block) {
        return new Candy("Basic", color, block, block);
    }
}
