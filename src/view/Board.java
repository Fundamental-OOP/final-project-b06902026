package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Game;
import view.states.Initial;

public class Board extends JPanel implements Runnable {
    private final int DELAY = 25;
    private Game game;
    private model.Board board;
    private Block[][] blocks =
        new Block[model.Board.nRows][model.Board.nColumns];
    private Thread animator;
    private long prev;
    private State state;
    public model.Board getModel() {
        return board;
    }
    public Game getGame() {
        return game;
    }
    private boolean validRow(int row) {
        return row >= 0 && row < model.Board.nRows;
    }
    private boolean validColumn(int column) {
        return column >= 0 && column < model.Board.nColumns;
    }
    public Block getBlock(int row, int column) {
        if (validRow(row) && validColumn(column))
            return blocks[row][column];
        else
            return new Block(new Dimension(column, row));
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
    private void newBlock(int row, int column) {
        blocks[row][column] = new Block(new Dimension(column, row));
        add(blocks[row][column]);
    }
    private void initBlocks() {
        for (int i = 0; i < model.Board.nRows; i++)
            for (int j = 0; j < model.Board.nColumns; j++)
                newBlock(i, j);
    }
    public Board(model.Board board, Game game) {
        this.board = board;
        this.game = game;
        setLayout(null);
        initBlocks();
    }
}