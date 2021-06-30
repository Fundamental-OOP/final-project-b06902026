package model;

public class Block {
    private static Board board;
    private Candy candy = null;
    public int row;
    public int column;
    public static void set(Board board) {
        Block.board = board;
    }
    public boolean isNeighbor(Block other) {
        if (other == null) return false;
        if (row == other.row && column == other.column) return true;
        if (row == other.row)
            return column+1 == other.column || column-1 == other.column;
        if (column == other.column)
            return row+1 == other.row || row-1 == other.row;
        return false;
    }
    public Block left(int n) {
        return board.getBlock(row, column-n);
    }
    public Block right(int n) {
        return board.getBlock(row, column+n);
    }
    public Block up(int n) {
        return board.getBlock(row-n, column);
    }
    public Block down(int n) {
        return board.getBlock(row+n, column);
    }
    public Block left() {
        return left(1);
    }
    public Block right() {
        return right(1);
    }
    public Block up() {
        return up(1);
    }
    public Block down() {
        return down(1);
    }
    public void set(Candy candy) {
        this.candy = candy;
        if (candy != null && !equals(candy.getBlock()))
            candy.set(this);
    }
    public Candy getCandy() {
        return candy;
    }
    public boolean hasCandy() {
        return getCandy() != null;
    }
    public void removeCandy() {
        if (candy != null) candy.remove();
    }
    public void deleteCandy() {
        board.deleteCandy(row, column);
        set((Candy) null);
    }
    public Board getBoard() {
        return board;
    }
    public view.Block getView() {
        return getBoard().getView().getBlock(row, column);
    }
    public void fillBlankWithCandyAbove() {
        if (hasCandy()) return;
        Block block = up();
        while (!(block instanceof NullBlock) && !block.hasCandy())
            block = block.up();
        if (!(block instanceof NullBlock))
            set(block.getCandy());
        block.set((Candy) null);
    }
    public Block(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
