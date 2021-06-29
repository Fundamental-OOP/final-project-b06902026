package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Candy extends JLabel implements MouseListener {
    private static final int STEP_SIZE = 5;
    public static final int width = 40;
    public static final int height = 40;
    private model.Candy candy;
    private Dimension pos;
    // TODO: private
    public Block prev;
    public Block next;
    private static ImageIcon getIcon(model.Candy candy) {
        String path = String.format(
            "img/%s%s.png", candy.getColor(), candy.getSuffix());
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image newimg =
            image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }
    private Dimension getDirection() {
        return next.pos.minus(prev.pos).getUnit();
    }
    public void calculateNextPosition() {
        prev = next;
        next = candy.getBlock().getView();
        pos = prev.pos;
    }
    private void setLocation() {
        setBounds(pos.x, pos.y, width, height);
    }
    private boolean exceed() {
        Dimension destionationDirection = next.pos.minus(pos).getUnit();
        return !arrived() && !getDirection().equals(destionationDirection);
    }
    private void step(int size) {
        if (arrived()) return;
        Dimension delta = getDirection().multiply(size);
        pos.set(pos.add(delta));
        if (exceed()) pos.set(next.pos);
        setLocation();
    }
    public void step() {
        step(STEP_SIZE);
    }
    public boolean arrived() {
        return pos.equals(next.pos);
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    public void click() {
        System.out.printf("(%d,%d)\n", prev.block.x, prev.block.y);
        setOpaque(true);
        setBackground(Color.lightGray);
    }
    public void unclick() {
        setOpaque(false);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        click();
        candy.getBlock().getBoard().click(candy);
    }
    public Candy(model.Candy candy) {
        this(candy, candy.getBlock().getView(), candy.getBlock().getView());
    }
    public Candy(model.Candy candy, Block prev, Block next) {
        this.candy = candy;
        this.prev = prev;
        this.next = next;
        this.pos = prev.pos;
        setIcon(getIcon(candy));
        setLocation();
        addMouseListener(this);
    }
}