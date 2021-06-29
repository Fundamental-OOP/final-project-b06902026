package model;

public class Candy {
    private final String name;
    private final String color;
    private final String suffix;
    private view.Candy view;
    private Block block;
    public boolean hasSpecialEffect = true;
    public boolean isSameColor(String color) {
        return getColor().equals(color);
    }
    public boolean isSameColor(Candy candy) {
        return isSameColor(candy.getColor());
    }
    public boolean is(String name) {
        return this.name == name;
    }
    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }
    public Block getBlock() {
        return block;
    }
    public String getSuffix() {
        return suffix;
    }
    public view.Candy getView() {
        return view;
    }
    public void remove() {
        block.deleteCandy();
    }
    public void removeSpecialEffect() {
        hasSpecialEffect = false;
    }
    private boolean isNeighbor(Block a, Block b) {
        return a.row == b.row && a.column == b.column;
    }
    public boolean isNeighbor(Candy other) {
        if (isNeighbor(block.up(), other.getBlock())) return true;
        if (isNeighbor(block.down(), other.getBlock())) return true;
        if (isNeighbor(block.left(), other.getBlock())) return true;
        if (isNeighbor(block.right(), other.getBlock())) return true;
        return false;
    }
    public void switchPosition(Candy other) {
        Block tmp = getBlock();
        set(other.getBlock());
        other.set(tmp);
    }
    public void set(Block block) {
        this.block = block;
        if (block != null && !equals(block.getCandy()))
            block.set(this);
    }
    public Candy(String name, String color,
        Block prev, Block next, String suffix) {
        set(next);
        this.name = name;
        this.color = color;
        this.suffix = suffix;
        this.view = new view.Candy(this, prev.getView(), next.getView());
    }
    public Candy(String name, String color, Block block, String suffix) {
        this(name, color, block, block, suffix);
    }
    public Candy(String name, String color, Block prev, Block next) {
        this(name, color, prev, next, "");
    }
}