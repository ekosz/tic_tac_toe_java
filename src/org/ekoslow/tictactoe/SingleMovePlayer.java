package org.ekoslow.tictactoe;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/26/12
 * Time: 11:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class SingleMovePlayer implements Player {
    private int move;
    private String letter;
    private boolean played;

    public SingleMovePlayer(String letter, int move) {
        this.letter = letter;
        this.move = move;
        this.played = false;
    }

    @Override
    public void startMove(IPlayable game, Board board) {
        if(!played) {
            played = true;
            game.playAt(move, letter);
        }
    }
}
