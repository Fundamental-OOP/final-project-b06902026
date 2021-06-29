import java.awt.EventQueue;

import model.Block;
import model.Board;
import model.CandyGenerator;
import model.CombineRuleGenerator;
import model.Game;
import model.MatchRuleGenerator;
import model.candies.*;
import model.rules.*;
import view.states.Initial;

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
        new ColorBombBasic(),
        new ColorBomb2(),
        new WrappedStriped(),
        new Wrapped2(),
        new Striped2(),
    };
    private static final MatchRuleGenerator[] matchRules = {
        new StraightFive(),
        new StraightFour(),
        new StraightThree(),
        new NonStraightFive(),
    };
    private static final Basic basic = new Basic();
    private static final ColorBomb colorBomb = new ColorBomb();
    private static final HorizontallyStriped hStriped = new HorizontallyStriped();
    private static final VerticallyStriped vStriped = new VerticallyStriped();
    private static final Wrapped wrapped = new Wrapped();
    private static final CandyGenerator[][] initCandies =
        new CandyGenerator[][] {
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
        };
    private static final String[][] initColors =
        new String[][] {
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
        };
    private static final Game game = new Game(combineRules, matchRules);
    private static final Board board = new Board(colors);
    public static void main(String[] args) {
        Block.set(board);
        Initial.set(initCandies);
        Initial.set(initColors);
        EventQueue.invokeLater(() -> {
            view.Game ex = new view.Game(board, game);
            ex.setVisible(true);
        });
    }
}
