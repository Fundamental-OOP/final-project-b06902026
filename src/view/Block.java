package view;

import java.awt.Color;
import javax.swing.JLabel;

public class Block extends JLabel {
    public static final int width = 40;
    public static final int height = 40;
    public static final int depth = 1;
    public final Dimension block;
    public final Dimension pos;
    private boolean clicked = false;
    private static Dimension getPos(Dimension block) {
        return new Dimension(width*block.x, height*block.y);
    }
    public void click() {
        if (!clicked) {
            clicked = true;
            setOpaque(true);
            setBackground(Color.lightGray);
            repaint();
        }
        else unclick();
    }
    public void unclick() {
        clicked = false;
        setOpaque(false);
        repaint();
    }
    public Block(Dimension block) {
        this.block = block;
        this.pos = getPos(block);
        setBounds(pos.x, pos.y, width, height);
    }
}
