package view;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Candy extends JLabel implements MouseListener {
    private static final int STEP_SIZE = 5;
    public static final int width = Block.width;
    public static final int height = Block.height;
    private static ArrayList<model.Candy> clicked = new ArrayList<model.Candy>();
    private model.Candy candy;
    private Dimension pos;
    private Block prev;
    private Block next;
    public static int nClicked() {
        return clicked.size();
    }
    public static void clearClicked() {
        getClicked(0).getView().unclick();
        getClicked(1).getView().unclick();
        clicked = new ArrayList<model.Candy>();
    }
    public static model.Candy getClicked(int id) {
        return clicked.get(id);
    }
    private static model.Block getClickedBlock(int id) {
        return getClicked(id).getBlock();
    }
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
        pos = new Dimension(pos.add(delta));
        if (exceed()) pos = new Dimension(next.pos);
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
    private void setHandCursor() {
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    private void setDefaultCursor() {
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));;
    }
    private boolean isNeighbor(model.Block other) {
        return candy.getBlock().isNeighbor(other);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        switch (nClicked()) {
            case 0: setHandCursor(); break;
            case 1:
                if (isNeighbor(getClickedBlock(0))) setHandCursor();
                else setDefaultCursor();
                break;
            default: setDefaultCursor(); break;
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        setDefaultCursor();
    }
    public void click() {
        candy.getBlock().getView().click();
    }
    public void unclick() {
        candy.getBlock().getView().unclick();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (nClicked() >= 2) return;
        if (nClicked() == 1 && !isNeighbor(getClickedBlock(0))) return;
        click();
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