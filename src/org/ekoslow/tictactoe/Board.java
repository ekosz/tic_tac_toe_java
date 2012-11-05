package org.ekoslow.tictactoe;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/25/12
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class Board {
    public String[] grid;

    public Board() {
        grid = new String[9];
    }

    public boolean isEmpty() {
        for (String letter : grid) {
            if (letter != null)
                return false;
        }
        return true;
    }

    public boolean isFull() {
        for (String letter : grid) {
            if (letter == null)
                return false;
        }
        return true;
    }

    public boolean isOnlyOne() {
        int count = 0;

        for (String letter : grid)
            if (letter != null && ++count > 1) return false;

        if (count == 1) return true;
        return false;
    }

    public String getCell(int x, int y) {
        return grid[cordToNum(x, y) - 1];
    }

    public Board playAt(int x, int y, String letter) {
        return playAt(cordToNum(x, y), letter);
    }

    public Board playAt(int num, String letter) {
        if (grid[num - 1] == null)
            grid[num - 1] = letter;
        return this;
    }

    public boolean isSolved() {
        return isSolved(0, 1, 2) || isSolved(3, 4, 5) || isSolved(6, 7, 8) ||
                isSolved(0, 3, 6) || isSolved(1, 4, 7) || isSolved(2, 5, 8) ||
                isSolved(0, 4, 8) || isSolved(2, 4, 6);
    }

    private boolean isSolved(int i, int i1, int i2) {
        return (grid[i] != null && grid[i].equals(grid[i1]) && grid[i].equals(grid[i2]));
    }

    private boolean isSolved(String letter) {
        return isSolved(0, 1, 2, letter) || isSolved(3, 4, 5, letter) || isSolved(6, 7, 8, letter) ||
                isSolved(0, 3, 6, letter) || isSolved(1, 4, 7, letter) || isSolved(2, 5, 8, letter) ||
                isSolved(0, 4, 8, letter) || isSolved(2, 4, 6, letter);
    }

    private boolean isSolved(int i, int i1, int i2, String letter) {
        return (grid[i] != null && grid[i].equals(letter) && grid[i].equals(grid[i1]) && grid[i].equals(grid[i2]));
    }

    private int cordToNum(int x, int y) {
        return x + (y * 3) + 1;
    }

    public Board clone() {
        Board board = new Board();
        board.grid = this.grid.clone();
        return board;
    }

    public String winner() {
        if (!isSolved()) return null;

        if (isSolved("x")) return "x";
        return "o";
    }
}
