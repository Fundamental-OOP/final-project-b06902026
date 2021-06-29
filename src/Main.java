import java.awt.EventQueue;

import model.Block;
import model.Board;
import model.CombineRuleGenerator;
import model.Game;
import model.MatchRuleGenerator;
import model.rules.*;

public class Main {
    private static final String[] colors = {
        "Red",
        "Orange",
        "Yellow",
        "Green",
        "Blue",
        "Purple",
    };
    private static final CombineRuleGenerator[] combineRules = {
        new WrappedHorizontallyStriped(),
    };
    private static final MatchRuleGenerator[] matchRules = {
        new StraightFive(),
        new NonStraightFive(),
        new StraightFour(),
        new StraightThree(),
    };
    private static final Game game = new Game(combineRules, matchRules);
    private static final Board board = new Board(colors);
    public static void main(String[] args) {
        Block.set(board);
        EventQueue.invokeLater(() -> {
            view.Game ex = new view.Game(board, game);
            ex.setVisible(true);
        });
    }
}
