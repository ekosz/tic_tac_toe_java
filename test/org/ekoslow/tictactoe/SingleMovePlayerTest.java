package org.ekoslow.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SingleMovePlayerTest extends PlayerTests {
    @Before
    public void setup() {
        this.player = new SingleMovePlayer("x", 1);
    }

    @Test
    public void doesNotHaveSecondMove() {
        PlayAtCounter mock = new PlayAtCounter();
        player.startMove(mock, null);
        player.startMove(mock, null);
        assertEquals(1, mock.count);
    }


}

class PlayAtCounter implements IPlayable {

   public int count = 0;

    @Override
    public void playAt(int location, String letter) {
        this.count += 1;
    }
}
