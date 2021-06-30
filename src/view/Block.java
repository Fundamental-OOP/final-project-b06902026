package view;

import java.awt.Color;
import javax.swing.JLabel;

public class Block extends JLabel {
    public static final int width = 40;
    public static final int height = 40;
    public final Dimension block;
    public final Dimension pos;
    private static Dimension getPos(Dimension block) {
        return new Dimension(width*block.x, height*block.y);
    }
    public void click() {
        setOpaque(true);
        setBackground(Color.lightGray);
    }
    public void unclick() {
        setOpaque(false);
    }
    public Block(Dimension block) {
        this.block = block;
        this.pos = getPos(block);
        setBounds(pos.x, pos.y, width, height);
    }
}
