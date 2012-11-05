package org.ekoslow.tictactoe;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/26/12
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class SolverFactory implements ISolverFactory {
    @Override
    public Solver generate(Board board, String letter) {
        return new Solver(board, letter);
    }
}
