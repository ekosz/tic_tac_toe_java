package org.ekoslow.tictactoe;

import org.ekoslow.tictactoe.ComputerPlayer;
import org.ekoslow.tictactoe.Game;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/26/12
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class TicTacToeTest {

    @Test
    public void canPlayAFullGame() {
        Game game = new Game(new String[9], new ComputerPlayer("x"), new ComputerPlayer("o"));
        game.playNextTurn();
        assertEquals(true, game.isCats());
    }
}
