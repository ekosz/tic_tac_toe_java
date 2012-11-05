package org.ekoslow.tictactoe;

import org.ekoslow.tictactoe.Board;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/25/12
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class BoardTest {
    private Board board;

    @Before
    public void setup() {
        this.board = new Board();
    }

    @Test
    public void gridCanBeSet() {
        String[] array = new String[]{"a"};
        this.board.grid = array;
        assertEquals(array, this.board.grid);
    }

    @Test
    public void aNewBoardIsEmpty() {
        // Board empty when created
        assertEquals(true, this.board.isEmpty());
    }

    @Test
    public void aBoardAfterPlayedIsNoLongerEmpty() {
        // Board not longer empty after letter placed
        this.board.playAt(0, 0, "o");
        assertEquals(false, this.board.isEmpty());
    }

    @Test
    public void aNewBoardIsNotEmpty() {
        assertEquals(false, this.board.isFull());
    }

    @Test
    public void full() {
        // Board full when no cells are null
        this.board.grid = new String[]{"x", "o", "x", "o", "x", "o", "x", "o", "x"};
        assertEquals(true, this.board.isFull());
    }

    @Test
    public void aEmptyBoardIsNotOnlyOne() {
        // False when empty
        assertEquals(false, this.board.isOnlyOne());
    }

    @Test
    public void aBoardKnowsWhenOnlyOnePeice() {
        // True when one
        this.board.grid = new String[]{"x", null, null, null, null, null, null, null, null};
        assertEquals(true, this.board.isOnlyOne());
    }

    @Test
    public void aBoardWithMoreThanOnePlaceIsNotOnlyOne() {
        // False when more than one
        this.board.grid = new String[]{"x", "x", null, null, null, null, null, null, null};
        assertEquals(false, this.board.isOnlyOne());
    }

    @Test
    public void getCellReturnsNullWhenNothingThere() {
        // Returns null when nothing in cell
        assertEquals(null, this.board.getCell(0, 0));
    }

    @Test
    public void playAt() {
        // Cells are proper set and override null values
        this.board.playAt(1, 1, "x");
        assertEquals("x", this.board.getCell(1, 1));
    }

    @Test
    public void getCell() {
        // Returns the right letter after they've been played
        this.board.playAt(0, 0, "o");
        assertEquals("o", this.board.getCell(0, 0));

        this.board.playAt(1, 2, "o");
        assertEquals("o", this.board.getCell(1, 2));
    }

    @Test
    public void canNotOverrideValues() {
        assertEquals(null, this.board.getCell(1, 1));
        this.board.playAt(1, 1, "x");
        assertEquals("x", this.board.getCell(1, 1));
        this.board.playAt(1, 1, "o");
        assertEquals("x", this.board.getCell(1, 1));
    }

    @Test
    public void solvedAcross() {
        assertEquals(false, this.board.isSolved());

        this.board.grid = new String[]{"x", "x", "x", null, null, null, null, null, null};
        assertEquals(true, this.board.isSolved());

        this.board.grid = new String[]{null, null, "x", null, "o", null, "x", "x", "x"};
        assertEquals(true, this.board.isSolved());

        this.board.grid = new String[]{"x", "o", "x", null, null, null, null, null, null};
        assertEquals(false, this.board.isSolved());
    }

    @Test
    public void solvedUpAndDown() {
        assertEquals(false, this.board.isSolved());

        this.board.grid = new String[]{"x", "o", "o", "x", null, null, "x", null, null};
        assertEquals(true, this.board.isSolved());

        this.board.grid = new String[]{null, null, "x", null, null, "x", null, null, "x"};
        assertEquals(true, this.board.isSolved());

        this.board.grid = new String[]{"x", "o", "o", "x", null, null, "o", null, null};
        assertEquals(false, this.board.isSolved());
    }

    @Test
    public void wonDiagonally() {
        assertEquals(false, this.board.isSolved());

        this.board.grid = new String[]{"x", "o", "x", "x", "x", null, "o", null, "x"};
        assertEquals(true, this.board.isSolved());

        this.board.grid = new String[]{null, null, "x", null, "x", null, "x", null, null};
        assertEquals(true, this.board.isSolved());

        this.board.grid = new String[]{null, null, "x", null, "x", null, "o", null, null};
        assertEquals(false, this.board.isSolved());
    }

    @Test
    public void winner() {
        board.grid = new String[] { "x", "x", "x", null, null, null, null, null, null};
        assertEquals("x", board.winner());
        board.grid = new String[] { null, "o", null, null, "o", null, null, "o", null};
        assertEquals("o", board.winner());
    }
}
