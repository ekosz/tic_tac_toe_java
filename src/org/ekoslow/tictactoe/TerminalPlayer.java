package org.ekoslow.tictactoe;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 10/1/12
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class TerminalPlayer implements Player {
    private ITerminal terminal;
    private String letter;

    public TerminalPlayer(ITerminal terminal, String letter) {
        this.terminal = terminal;
        this.letter = letter;
    }

    @Override
    public void startMove(IPlayable game, Board board) {
        printGrid(board.grid);
        terminal.write("Move (1-9): ");
        String input = terminal.getInput().trim();
        try {
            game.playAt(Integer.parseInt(input), letter);
        } catch (NumberFormatException e) {
            terminal.writeLine(input + " is not a valid move.");
            startMove(game, board);
        }
    }

    private void printGrid(String[] grid) {
        terminal.writeLine("-------");
        writeBoardLine(grid, 0);
        writeBoardLine(grid, 3);
        writeBoardLine(grid, 6);
        terminal.writeLine("-------");
        terminal.writeLine("");
    }

    private void writeBoardLine(String[] grid, int start) {
        terminal.write("|");
        for(int i=0; i < 3; i++) {
            if(grid[start+i] == null) {
                terminal.write(String.valueOf(start+i+1));
            } else {
                terminal.write(grid[start+i]);
            }
            terminal.write("|");
        }
        terminal.write("\n");
    }
}
