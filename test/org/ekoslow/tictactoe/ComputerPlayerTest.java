package org.ekoslow.tictactoe;

import org.junit.Before;

public class ComputerPlayerTest extends PlayerTests {

    @Before
    public void setup() {
        this.player = new ComputerPlayer("x", new MockSolverFactory());
    }

}

class MockSolverFactory implements ISolverFactory {
    public Solver generate(Board board, String letter) {
        return new SolverMock(board, letter);
    }
}

class SolverMock extends Solver {
    public SolverMock(Board board, String letter) { }

    public int solve() { return 1; }
}
