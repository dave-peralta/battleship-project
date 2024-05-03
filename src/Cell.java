public enum Cell {
    EMPTY('~'), MISS('*'), HIT('X'), SHIP('S');

    private final char symbol;

    Cell(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }
}
