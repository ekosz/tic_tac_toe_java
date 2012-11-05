package org.ekoslow.tictactoe;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/26/12
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ITerminal {
    public void writeLine(String output);

    public void write(String output);

    public String getInput();
}
