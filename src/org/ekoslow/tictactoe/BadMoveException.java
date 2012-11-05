package org.ekoslow.tictactoe;

/**
* Created with IntelliJ IDEA.
* User: ekoslow
* Date: 9/26/12
* Time: 2:14 PM
* To change this template use File | Settings | File Templates.
*/
public class BadMoveException extends Throwable {
    public BadMoveException(String s) {
        super(s);
    }
}
