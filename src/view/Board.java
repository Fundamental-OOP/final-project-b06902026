package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Game;
import view.states.Initial;

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
        state = new Initial(this);
        prev = System.currentTimeMillis();
        while (true) {
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