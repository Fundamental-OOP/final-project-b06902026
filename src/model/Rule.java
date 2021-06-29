package model;

public interface Rule {
    public String getName();
    public boolean isMatched();
    public void act();
}
