package view;

public class Block {
    public static final int width = 40;
    public static final int height = 40;
    public Dimension block;
    public Dimension pos;
    private static Dimension getPos(Dimension block) {
        return new Dimension(width*block.x, height*block.y);
    }
    public Block(Dimension block) {
        this.block = block;
        this.pos = getPos(block);
    }
}
