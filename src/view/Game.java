package view;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class Game extends JFrame {
    private JLayeredPane contentPane = new JLayeredPane();
    public Game(model.Board board, model.Game game) {
        setContentPane(contentPane);
        Board boardView = new Board(board, game, contentPane);
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
