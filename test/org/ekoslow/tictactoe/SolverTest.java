package org.ekoslow.tictactoe;

import org.ekoslow.tictactoe.Board;
import org.ekoslow.tictactoe.Solver;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/25/12
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class SolverTest {

    private Board board;
    private Solver solver;

    @Before
    public void setup() {
        this.board = new Board();
        this.solver = new Solver(board, "x");
    }

    @Test
    public void testWinHorizontally() {
        // Will win, when there is a winning move
        board.grid = new String[]{"x", "x", null, "o", "o", null, "x", "o", null};
        assertEquals(false, board.isSolved()); // Is not solved yet
        solve();
        assertEquals(true, board.isSolved()); // Solved it
        assertEquals("x", board.getCell(2, 0)); // Placed the right letter
    }

    @Test
    public void testWinsAcross() {
        board.grid = new String[]{"x", "o", null, null, "x", null, "o", null, null};
        assertEquals(false, board.isSolved());
        solve();
        assertEquals(true, !!board.isSolved());
        assertEquals("x", board.getCell(2, 2));
    }

    @Test
    public void testWinsVertically() {
        board.grid = new String[]{"x", null, "o", null, "o", "o", "x", null, null};
        assertEquals(false, board.isSolved());
        solve();
        assertEquals(true, !!board.isSolved());
        assertEquals("x", board.getCell(0, 1));
    }

    @Test
    public void testDoesNotWinUnsolvableBoard() {
        // Can not win when there is no winning move
        board.grid = new String[]{"o", null, null, null, null, null, "o", null, null};
        assertEquals(false, board.isSolved());
        solve();
        assertEquals(false, board.isSolved());
    }

    @Test
    public void testBlocksHorizontally() {
        assertEquals(false, board.isSolved());

        // Will block when the opponent is about to win
        board.grid = new String[]{"o", "o", null, null, "x", null, null, "x", null};
        assertEquals(false, board.isSolved());
        solve();
        assertEquals(false, board.isSolved());
        // Placed the right letter at the right place
        assertEquals("x", board.getCell(2, 0));
    }

    @Test
    public void testBlocksVertically() {
        board.grid = new String[]{"x", "o", null, null, null, null, null, "o", null};
        solve();
        assertEquals("x", board.getCell(1, 1));
    }


    @Test
    public void testForksInMiddle() {
        assertEquals(false, board.isSolved());

        // Place letter in place where next turn is an automatic win
        board.grid = new String[]{"x", "o", "x", null, null, "o", null, null, null};
        assertEquals(false, board.isSolved());
        solve();
        assertEquals(false, board.isSolved());
        assertEquals(true, board.getCell(1, 1) != null);
    }

    @Test
    public void testForksInCorner() {

        // 1 | 2 | x
        // 4 | o | 6
        // 7 | x | 9 <--
        board.grid = new String[]{null, "o", "x", null, "o", null, null, "x", null};
        assertEquals(false, board.isSolved());
        solve();
        assertEquals(false, board.isSolved());
        assertEquals("x", board.getCell(2, 2));
    }

    @Test
    public void testDoesNotCreateFork() {
        // 1 | 2 | o
        // 4 | x | 6
        // o | 8 | 9
        board.grid = new String[]{null, null, "o", null, "x", null, "o", null, null};
        solve();
        assertEquals(null, board.getCell(2, 2));
        assertEquals(null, board.getCell(0, 0));
    }

    @Test
    public void testDoesNotCreateForkInTheFuture() {

        // x | 2 | 3
        // 4 | o | 6
        // 7 | 8 | o
        board.grid = new String[]{"x", null, null, null, "o", null, null, null, "o"};
        solve();
        assertEquals(null, board.getCell(1, 0));
        assertEquals(null, board.getCell(0, 1));
        assertEquals(null, board.getCell(2, 1));
        assertEquals(null, board.getCell(1, 2));
    }

    @Test
    public void testDoesNotCreateForkInTheFarFuture() {
        board.grid = new String[]{"o", null, null, null, null, null, null, null, null};
        solve();
        assertEquals(null, board.getCell(0, 2));
    }


    @Test
    public void testEmptyCorner() {
        board.grid = new String[]{null, null, null, null, "o", null, null, null, null};
        solve();
        assertEquals(true, board.getCell(0, 0) != null);
    }

    @Test
    public void testAnyEmptyPosition() {
        // o | x | o
        // o | x | 6
        // x | o | x
        board.grid = new String[]{"o", "x", "o", "o", "x", null, "x", "o", "x"};
        solve();
        assertEquals("x", board.getCell(2, 1));
    }

    @Test(expected = Exception.class)
    public void testRaisesExceptionOnFullBoard() {
        board.grid = new String[]{"x", "o", "x", "o", "o", "x", "o", "x", "o"};
        solve();
    }

    private void solve() {
        int position = solver.solve();
        board.playAt(position, "x");
    }
}
