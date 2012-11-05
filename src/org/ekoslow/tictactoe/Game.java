package org.ekoslow.tictactoe;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/26/12
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game implements IPlayable {
    public Board board;

    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game() {
        this.board = new Board();
    }

    public Game(String[] grid) {
        this.board = new Board();
        board.grid = grid;
    }

    public String[] getGrid() {
        return board.grid;
    }

    public Game(String[] grid, Player player1, Player player2) {
        this.board = new Board();
        board.grid = grid;

        this.player1 = player1;
        this.player2 = player2;

        setCurrentPlayer();
    }

    private void setCurrentPlayer() {
        if(player1 != null) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
    }

    public boolean isSolved() {
        return board.isSolved();
    }

    public boolean isCats() {
        return board.isFull() && !board.isSolved();
    }

    public String winner() {
        return board.winner();
    }

    public void playNextTurn() {
        if(!isOver()) currentPlayer.startMove(this, board);
    }

    private void switchPlayer() {
        if(currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

   public boolean isOver() {
        return isSolved() || isCats();
    }

    @Override
    public void playAt(int location, String letter) {
        board.playAt(location, letter);
        switchPlayer();
        playNextTurn();
    }
}
