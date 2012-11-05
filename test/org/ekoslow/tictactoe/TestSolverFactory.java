package org.ekoslow.tictactoe;

import org.ekoslow.tictactoe.Board;
import org.ekoslow.tictactoe.Solver;
import org.ekoslow.tictactoe.SolverFactory;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/26/12
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestSolverFactory {
    @Test
    public void createsASolverThatSolves() {
        Board board = new Board();
        Solver solver = new SolverFactory().generate(board, "x");
        int move =  solver.solve();
        assertEquals(1, move);
    }


}
