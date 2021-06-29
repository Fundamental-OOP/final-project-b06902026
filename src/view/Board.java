package view;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Candy;
import model.Game;
import view.states.Fall;
import view.states.WaitClick;

public class Board extends JPanel implements Runnable {
    private final int DELAY = 25;
    private Game game;
    private model.Board board;
    private Thread animator;
    private long prev;
    private State state;
    public model.Board getModel() {
        return board;
    }
    public Game getGame() {
        return game;
    }
    public int getBlockWidth() {
        return Block.width;
    }
    public int getBlockHeight() {
        return Block.height;
    }
    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
    public Candy getClicked(int id) {
        return board.clicked.get(id);
    }
    private long getSleepTime() {
        long diff = System.currentTimeMillis() - prev;
        long sleep = DELAY - diff;
        if (sleep < 0) sleep = 2;
        return sleep;
    }
    private void step() {
        state = state.run();
    }
    @Override
    public void run() {
        state = new Fall(this);
        prev = System.currentTimeMillis();
        while (true) {
            // TODO: System.out.println(state.getName());
            step();
            repaint();
            try {
                Thread.sleep(getSleepTime());
            } catch (InterruptedException e) {
                String msg =
                    String.format("Thread interrupted: %s", e.getMessage());
                JOptionPane.showMessageDialog(this, msg, "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            prev = System.currentTimeMillis();
        }
    }
    public Board(model.Board board, Game game) {
        this.board = board;
        this.game = game;
        setLayout(null);
    }
}