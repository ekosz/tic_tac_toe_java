package org.ekoslow.tictactoe;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/26/12
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class ComputerPlayer implements Player {

    private String letter;
    private ISolverFactory solverFactory;

    public ComputerPlayer(String letter, ISolverFactory solverFactory) {
        this.letter = letter;
        this.solverFactory = solverFactory;
    }

    public ComputerPlayer(String letter) {
        this.letter = letter;
        this.solverFactory = new SolverFactory();
    }

    @Override
    public void startMove(IPlayable game, Board board) {
        game.playAt(solverFactory.generate(board, letter).solve(), letter);
    }
}
