public class Board {
    private Cell[][] grid;
    public static final int SIZE = 10;

    public Board() {
        grid = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = Cell.EMPTY;
            }
        }
    }

    public boolean placeShip(Position position, int size, boolean horizontal) {
        int row = position.row;
        int col = position.col;

        if (horizontal) {
            if (col + size > SIZE) return false;
            for (int i = col; i < col + size; i++) {
                if (grid[row][i] != Cell.EMPTY) return false;
            }
            for (int i = col; i < col + size; i++) {
                grid[row][i] = Cell.SHIP;
            }
        } else {
            if (row + size > SIZE) return false;
            for (int i = row; i < row + size; i++) {
                if (grid[i][col] != Cell.EMPTY) return false;
            }
            for (int i = row; i < row + size; i++) {
                grid[i][col] = Cell.SHIP;
            }
        }
        return true;
    }

    public boolean fireAt(Position position) {
        if (grid[position.row][position.col] == Cell.SHIP) {
            grid[position.row][position.col] = Cell.HIT;
            return true;
        } else if (grid[position.row][position.col] == Cell.EMPTY) {
            grid[position.row][position.col] = Cell.MISS;
        }
        return false;
    }

    public void display() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }

    public boolean hasRemainingShips() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == Cell.SHIP) {
                    return true;
                }
            }
        }
        return false;
    }
}
