package model;

public interface CandyGenerator {
    public Candy get(String color, Block prev, Block next);
    public Candy get(String color, Block block);
}
