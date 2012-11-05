package org.ekoslow.tictactoe;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/26/12
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ISolverFactory {

    public Solver generate(Board board, String letter);
}
