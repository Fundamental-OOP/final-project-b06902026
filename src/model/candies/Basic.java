package model.candies;

import model.Block;
import model.Candy;
import model.CandyGenerator;

public class Basic implements CandyGenerator {
    public Candy get(String color, Block prev, Block next) {
        return new Candy("basic", color, prev, next, "");
    }
    public Candy get(String color, Block block) {
        return new Candy("basic", color, block, block, "");
    }
}
