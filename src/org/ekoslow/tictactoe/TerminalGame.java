package org.ekoslow.tictactoe;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 10/1/12
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TerminalGame {
    public static void main(String[] args) {
       System.out.println("Welcome to Tic-Tac-Toe");
       new Game(new String[9], new TerminalPlayer(new Terminal(), "o"), new ComputerPlayer("x")).playNextTurn();
       System.out.println("Thanks for playing!");
    }
}
