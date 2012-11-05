package org.ekoslow.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/26/12
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Terminal implements ITerminal {
    @Override
    public void writeLine(String output) {
        System.out.println(output);
    }

    @Override
    public void write(String output) {
        System.out.print(output);
    }

    @Override
    public String getInput() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferRead.readLine();
        } catch (IOException e) {
            return "";
        }
    }
}
