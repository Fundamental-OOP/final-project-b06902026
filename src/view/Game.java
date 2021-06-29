package view;

import javax.swing.JFrame;

public class Game extends JFrame {
    public Game(model.Board board, model.Game game) {
        Board boardView = new Board(board, game);
        board.setView(boardView);
        add(boardView);
        int width = model.Board.nColumns * Block.width + 15;
        int height = model.Board.nRows * Block.height + 40;
        setSize(width, height);
        setTitle("Candy Crush");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
