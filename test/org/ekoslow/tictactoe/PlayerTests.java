package org.ekoslow.tictactoe;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/26/12
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
class BoardMock extends Board {
}

public abstract class PlayerTests {
    protected Player player;

    @Test
    public void playsAMove() {
        PlayableMock game = new PlayableMock();
        player.startMove(game, new BoardMock());
        assertEquals(1, game.location);
    }

    @Test
    public void playsTheRightLetter() {
        PlayableMock game = new PlayableMock();
        player.startMove(game, new BoardMock());
        assertEquals("x", game.letter);
    }

}

class PlayableMock implements IPlayable {

    public int location;
    public String letter;

    @Override
    public void playAt(int location, String letter) {
        this.location = location;
        this.letter = letter;
    }
}


