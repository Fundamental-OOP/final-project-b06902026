package view;

public final class Dimension {
    public final int x;
    public final int y;
    public Dimension add(Dimension other) {
        return new Dimension(x + other.x, y + other.y);
    }
    public Dimension minus(Dimension other) {
        return new Dimension(x - other.x, y - other.y);
    }
    public Dimension multiply(int n) {
        return new Dimension(x * n, y * n);
    }
    public boolean equals(Dimension other) {
        return x == other.x && y == other.y;
    }
    public Dimension getUnit() {
        int unitX = (x == 0)? 0 : x / Math.abs(x);
        int unitY = (y == 0)? 0 : y / Math.abs(y);
        return new Dimension(unitX, unitY);
    }
    public Dimension(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Dimension(Dimension dimension) {
        this.x = dimension.x;
        this.y = dimension.y;
    }
}
