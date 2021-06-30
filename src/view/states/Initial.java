package view.states;

import model.CandyGenerator;
import view.Board;

public class Initial extends Fall {
    private static final String name = "Initial";
    private static CandyGenerator[][] candies =
        new CandyGenerator[model.Board.nRows][model.Board.nColumns];
    private static String[][] colors =
        new String[model.Board.nRows][model.Board.nColumns];
    public static void set(CandyGenerator[][] candies) {
        Initial.candies = candies;
    }
    public static void set(String[][] colors) {
        Initial.colors = colors;
    }
    public String getName() {
        return name;
    }
    public Initial(Board board) {
        super(board, candies, colors);
    }
}

