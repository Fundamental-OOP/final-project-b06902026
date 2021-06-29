package model;

public class Matrix {
    private boolean[][] matrix;
    public int nRow;
    public int nColumn;
    public static class Bound {
        int row, col, width, height;
        public Bound(int row, int col, int height, int width) {
            this.row = row;
            this.col = col;
            this.height = height;
            this.width = width;
        }
    }
    public void print() {
        System.out.println("matrix");
        for (int i = 0; i < nRow; i++)
            for (int j = 0; j < nColumn; j++) {
                int bool = matrix[i][j]? 1 : 0;
                String changeLine = (j == nColumn-1)? "\n" : ",";
                System.out.printf("%d%s", bool, changeLine);
            }
    }
    public boolean get(int row, int column) {
        return matrix[row][column];
    }
    public int nOnes() {
        int n = 0;
        for (int i = 0; i < nRow; i++)
            for (int j = 0; j < nColumn; j++)
                if (get(i, j)) n++;
        return n;
    }
    public boolean equals(Matrix other) {
        if (nRow != other.nRow) return false;
        if (nColumn != other.nColumn) return false;
        for (int i = 0; i < nRow; i++)
            for (int j = 0; j < nColumn; j++)
                if (get(i, j) != other.get(i, j)) return false;
        return true;
    }
    public Matrix getSubMatrix(int row, int column, int height, int width) {
        boolean[][] subMatrix = new boolean[height][width];
        for (int i = 0, y = row; i < height && y < nRow; i++, y++)
            for (int j = 0, x = column; j < width && x < nColumn; j++, x++)
                subMatrix[i][j] = matrix[y][x];
        return new Matrix(subMatrix);
    }
    public Matrix getSubMatrix(Bound bound) {
        return getSubMatrix(bound.row, bound.col, bound.height, bound.width);
    }
    public Bound find(Matrix filter) {
        for (int i = 0; i < nRow - filter.nRow + 1; i++)
            for (int j = 0; j < nColumn - filter.nColumn + 1; j++) {
                Bound bound = new Bound(i, j, filter.nRow, filter.nColumn);
                Matrix subMatrix = getSubMatrix(bound);
                if (subMatrix.equals(filter)) return bound;
            }
        return null;
    }
    public boolean contains(Matrix filter) {
        return find(filter) != null;
    }
    public Matrix(boolean[][] matrix) {
        this.matrix = matrix;
        this.nRow = matrix.length;
        this.nColumn = matrix[0].length;
    }
}
