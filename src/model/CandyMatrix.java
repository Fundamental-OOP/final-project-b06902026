package model;

public class CandyMatrix extends Matrix {
    private Board board;
    private Block getBlock(int row, int column) {
        return board.getBlock(row, column);
    }
    public void remove() {
        for (int i = 0; i < nRow; i++)
            for (int j = 0; j < nColumn; j++) {
                if (get(i, j))
                    getBlock(i, j).removeCandy();
            }
    }
    public void remove(Matrix filter) {
        remove(find(filter));
    }
    public void remove(Bound bound) {
        Matrix matrix = getSubMatrix(bound);
        for (int i = 0; i < matrix.nRow; i++)
            for (int j = 0; j < matrix.nColumn; j++) {
                if (matrix.get(i, j))
                    getBlock(bound.row+i, bound.col+j).removeCandy();
            }
    }
    private static boolean[][] getSameColor(Board board, String color) {
        boolean[][] isSameColor = new boolean[Board.nRows][Board.nColumns];
        for (int i = 0; i < Board.nRows; i++)
            for (int j = 0; j < Board.nColumns; j++) {
                Candy candy = board.getBlock(i, j).getCandy();
                isSameColor[i][j] = (candy == null)?
                    false : candy.isSameColor(color);
            }
        return isSameColor;
    }
    private static void walk(
        boolean[][] matrix, boolean[][] walked, int row, int col) {
            if (row < 0 || row >= matrix.length ||
                col < 0 || col >= matrix[0].length) return;
            if (!matrix[row][col]) return;
            if (walked[row][col]) return;
            else walked[row][col] = true;
            walk(matrix, walked, row+1, col);
            walk(matrix, walked, row-1, col);
            walk(matrix, walked, row, col+1);
            walk(matrix, walked, row, col-1);
        }
    private static boolean[][] getConnected(
        boolean[][] matrix, int row, int column) {
            boolean[][] connected =
                new boolean[matrix.length][matrix[0].length];
            walk(matrix, connected, row, column);
            return connected;
        }
    public static boolean[][] getConnectedSameColor(Block pivot) {
        if (pivot.getCandy() == null)
            return new boolean[Board.nRows][Board.nColumns];
        boolean[][] sameColor =
            CandyMatrix.getSameColor(
                pivot.getBoard(), pivot.getCandy().getColor());
        boolean[][] connected =
            CandyMatrix.getConnected(sameColor, pivot.row, pivot.column);
        return connected;
    }
    public static boolean[][] getArea(Bound bound) {
        int nRows = Board.nRows;
        int nCols = Board.nColumns;
        boolean[][] matrix = new boolean[nRows][nCols];
        for (int i = 0; i < bound.height && bound.row+i < nRows; i++)
            for (int j = 0; j < bound.width && bound.col+j < nCols; j++)
                matrix[bound.row+i][bound.col+j] = true;
        return matrix;
    }
    public CandyMatrix(boolean[][] matrix, Board board) {
        super(matrix);
        this.board = board;
    }
}
