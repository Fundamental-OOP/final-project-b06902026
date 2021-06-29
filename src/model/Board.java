package model;

import java.util.ArrayList;
import java.util.Random;

import model.candies.Basic;

public class Board {
    public static final int nRows = 10;
    public static final int nColumns = 6;
    private String[] colors;
    private final Random random = new Random();
    private Block[][] blocks = new Block[nRows][nColumns];
    public ArrayList<Candy> clicked = new ArrayList<Candy>();
    private view.Board view;
    public void print() {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                System.out.printf("%c ",
                    getBlock(i, j).getCandy().getColor().toCharArray()[0]);
            }
            System.out.println("");
        }
    }
    public void setView(view.Board view) {
        this.view = view;
    }
    public view.Board getView() {
        return view;
    }
    public int nClicked() {
        return clicked.size();
    }
    public void click(Candy candy) {
        clicked.add(candy);
    } 
    public void clearClicked() {
        clicked = new ArrayList<Candy>();
    }
    private boolean validRow(int row) {
        return row >= 0 && row < nRows;
    }
    private boolean validColumn(int column) {
        return column >= 0 && column < nColumns;
    }
    public Block getBlock(int row, int column) {
        if (validRow(row) && validColumn(column))
            return blocks[row][column];
        else
            return new NullBlock(row, column);
    }
    public Block[] getRow(int rowIdx) {
        if (!validRow(rowIdx)) return new Block[0];
        return blocks[rowIdx];
    }
    public Block[] getColumn(int columnIdx) {
        if (!validColumn(columnIdx)) return new Block[0];
        Block[] column = new Block[nRows];
        for (int i = 0; i < nRows; i++)
            column[i] = blocks[i][columnIdx];
        return column;
    }
    public String getRandomColor() {
        return colors[random.nextInt(colors.length)];
    }
    public void newCandy(int r1, int c1, int r2, int c2) {
        newCandy(new Basic(), getRandomColor(), r1, c1, r2, c2);
    }
    public void newCandy(
        CandyGenerator candyGen, String color, int r1, int c1, int r2, int c2) {
        Candy candy =
            candyGen.get(color, getBlock(r1, c1), getBlock(r2, c2));
        newCandy(candy, r2, c2);
    }
    public void newCandy(Candy candy, int row, int column) {
        getBlock(row, column).set(candy);
        getView().add(candy.getView());
    }
    public void deleteCandy(int row, int column) {
        Candy candy = getBlock(row, column).getCandy();
        getView().remove(candy.getView());
    }
    public void removeAllSpecialEffect() {
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nColumns; j++) {
                Candy candy = getBlock(i, j).getCandy();
                if (candy != null) candy.removeSpecialEffect();
            }
    }
    public String getColorWithMostCandies() {
        int maxNumCandies = 0;
        String selectedColor = "";
        for (String color: colors) {
            int nCandies =
                (new Matrix(CandyMatrix.getSameColor(this, color))).nOnes();
            if (nCandies > maxNumCandies) {
                maxNumCandies = nCandies;
                selectedColor = color;
            }
        }
        return selectedColor;
    }
    public void fillBlank() {
        fillBlank(
            new CandyGenerator[nRows][nColumns], new String[nRows][nColumns]);
    }
    public void fillBlank(CandyGenerator[][] candies, String[][] colors) {
        int[] nBlanks = new int[nColumns];
        for (int j = 0; j < nColumns; j++)
            for (int i = 0; i < nRows; i++)
                nBlanks[j] += getBlock(i, j).hasCandy()? 0 : 1;
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nColumns; j++)
                if (!getBlock(i, j).hasCandy()) {
                    if (candies[i][j] == null || colors[i][j] == null)
                        newCandy(i - nBlanks[j], j, i, j);
                    else
                        newCandy(
                            candies[i][j], colors[i][j], i-nBlanks[j], j, i, j);
                }
    }
    public void fall() {
        for (int i = nRows-1; i >= 0; i--)
            for (int j = 0; j < nColumns; j++)
                getBlock(i, j).fillBlankWithCandyAbove();
        calculateNextPosition();
    }
    public void calculateNextPosition() {
        for (int i = 0; i < nRows; i++)
            for (int j = 0; j < nColumns; j++) {
                Candy candy = getBlock(i, j).getCandy();
                if (candy != null)
                    candy.getView().calculateNextPosition();
            }
    }
    public Board(String[] colors) {
            this.colors = colors;
            for (int i = 0; i < nRows; i++)
                for (int j = 0; j < nColumns; j++) {
                    blocks[i][j] = new Block(i, j);
                }
        }
}
