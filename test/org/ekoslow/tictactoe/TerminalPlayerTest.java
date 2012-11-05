package org.ekoslow.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 10/1/12
 * Time: 1:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class TerminalPlayerTest extends PlayerTests{

    private TerminalMock mock;
    private PlayableMock game;

    @Before
    public void setup() {
        this.mock = new TerminalMock("1");
        this.player = new TerminalPlayer(mock, "x");
        this.game = new PlayableMock();
    }

    @Test
    public void getsMoveFromTerminal() {
        player.startMove(game, new BoardMock());
        assertEquals(true , mock.output.indexOf("Move (1-9): ") > 0);
    }

    @Test
    public void getsBadMoveFromUser() throws BadMoveException {
        MultiInputTerminalMock mock = new MultiInputTerminalMock("abc\n", "1");
        Player player = new TerminalPlayer(mock, "x");
        player.startMove(game, new BoardMock());
        assertEquals(1, game.location);
    }

    @Test
    public void printsOutTheBoard() {
        player.startMove(game, new BoardMock());
        System.out.println(mock.output);
        assertEquals(true, mock.output.startsWith("-------\n|1|2|3|\n|4|5|6|\n|7|8|9|\n-------\n\n"));
    }

    @Test
    public void printsOutBoardWithValues() {
        String[] grid = new String[] { "x", null, null, null, "x", null, null, "o", null};
        Board boardMock = new BoardMock();
        boardMock.grid = grid;
        player.startMove(game, boardMock);
        System.out.println(mock.output);
        assertEquals(true, mock.output.startsWith("-------\n|x|2|3|\n|4|x|6|\n|7|o|9|\n-------\n\n"));
    }

}

class MultiInputTerminalMock extends TerminalMock {
    private String input1;
    private String input2;
    private boolean firstCall;

    public MultiInputTerminalMock(String input1, String input2) {
        this.input1 = input1;
        this.input2 = input2;
        this.firstCall = true;
    }

    @Override
    public String getInput() {
        if(firstCall) { firstCall = false; return input1; }
        return input2;
    }
}

class TerminalMock implements ITerminal {

    public String output = "";
    private String input;

    public TerminalMock(String input) {
        this.input = input;
    }

    public TerminalMock() { }

    @Override
    public void writeLine(String output) {
        this.output += output + "\n";
    }

    @Override
    public void write(String output) {
        this.output += output;
    }

    @Override
    public String getInput() {
        return input;
    }
}

