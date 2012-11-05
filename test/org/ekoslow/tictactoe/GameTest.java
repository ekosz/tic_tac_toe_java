package org.ekoslow.tictactoe;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/26/12
 * Time: 12:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameTest {

    @Test
    public void testGrid() {
        Game game = new Game();

        boolean isMatching = isMachingArrays(emptyGrid(), game.getGrid());

        assertEquals(true, isMatching);
    }

    private boolean isMachingArrays(String[] grid1, String[] grid2) {
        if (grid1.length != grid2.length) return false;

        for (int i = 0; i < grid1.length; i++) {
            if (grid1[i] != null) {
                if (!grid1[i].equals(grid2[i])) return false;
            } else  {
                if(grid2[i] != null) return false;
            }
        }
        return true;
    }

    @Test
    public void testAnEmptyGameIsNotSolved() {
        Game game = new Game();

        assertEquals(false, game.isSolved());
    }

    @Test
    public void testASolvedGameisSolved() {
        Game game = new Game(new String[] {"x", "x", "x", null, null, null, null, null, null });

        assertEquals(true, game.isSolved());
    }

    @Test
    public void testAFullGridIsCats() {
        // x o x
        // x o x
        // o x o
        Game game = new Game(new String[]{"x", "o", "x", "x", "o", "x", "o", "x", "o"});

        assertEquals(true, game.isCats());
    }

    @Test
    public void testWinner() {
        Game game = new Game(new String[]{"x", "x", "x", null, null, null, null, null, null},
                new PlayerMock(), new PlayerMock());

        assertEquals("x", game.winner());
    }

    @Test
    public void testSettingAPlayersMove() {
        Game game = new Game(emptyGrid(), new PlayerMock(1), new PlayerMock());
        game.playNextTurn();

        assertEquals("x", game.board.getCell(0, 0));
    }

    @Test
    public void playsAFullRound() {
        Game game = new Game(emptyGrid(), new PlayerMock(1), new PlayerMock(9));
        game.playNextTurn();

        assertEquals(7, emptyPosistions(game.board));
    }

    private int emptyPosistions(Board board) {
        int count = 0;
        for (String letter : board.grid) {
            if (letter == null) count++;
        }
        return count;
    }

    @Test
    public void testSkipAPlayersTurn() {
        Game game = new Game(emptyGrid(), new PlayerMock(1), new PlayerMock());
        game.playNextTurn();

        assertEquals(true, game.board.isOnlyOne());
    }


    public String[] emptyGrid() {
        return new String[9];
    }
}

class PlayerMock implements Player {
    private int move;
    private boolean played;

    public PlayerMock() {
        this.played = false;
    }

    public PlayerMock(int move) {
        this.move = move;
        this.played = false;
    }

    @Override
    public void startMove(IPlayable game, Board board) {
        if(!played && move > 0) {
            played = true;
            game.playAt(move, "x");
        }
    }
}

