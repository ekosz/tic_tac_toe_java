package org.ekoslow.tictactoe;

/**
 * Created with IntelliJ IDEA.
 * User: ekoslow
 * Date: 9/25/12
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class Solver {
    private Board board;
    private String letter;

    public Solver(Board board, String letter) {
        this.board = board;
        this.letter = letter;
    }

    public Solver() {
    }

    public int solve() {
        if (board.isFull()) throw new RuntimeException("Can not solve a full board");

        return new MiniMax(board, letter).bestMove();
    }
}

class MiniMax {

    private Board start_board;
    private String player;

    private final int MAXDEPTH = 6;

    public MiniMax(Board board, String player) {
        this.start_board = board;
        this.player = player;
    }

    public int bestMove() {
        int bestMove = -1;
        double bestScore = Integer.MIN_VALUE;
        for (int i = 1; i <= start_board.grid.length; i++) {
            if (start_board.grid[i - 1] == null) {
                double score = score(start_board.clone().playAt(i, player), nextTurn(player));

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    private double score(Board board, String whosTurn) {
        return score(board, whosTurn, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private double score(Board board, String whosTurn, int depth, double alpha, double beta) {
        if (board.isFull() || board.isSolved()) return scoreBoard(board, depth);

        if (whosTurn.equals(player)) return maxScore(board, whosTurn, depth, alpha, beta);

        return minScore(board, whosTurn, depth, alpha, beta);
    }

    private double minScore(Board board, String whosTurn, int depth, double alpha, double beta) {
        for (int i = 1; i <= board.grid.length; i++) {
            if (board.grid[i-1] == null) {
                double nextScore = score(board.clone().playAt(i, whosTurn),
                        nextTurn(whosTurn), depth + 1, alpha, beta);

                if (nextScore < beta) beta = nextScore;

                if (alpha >= beta || depth >= MAXDEPTH) break;

            }
        }
        return beta;
    }

    private double maxScore(Board board, String whosTurn, int depth, double alpha, double beta) {
        for (int i = 1; i <= board.grid.length; i++) {
            if (board.grid[i-1] == null) {
                double nextScore = score(board.clone().playAt(i, whosTurn),
                        nextTurn(whosTurn), depth + 1, alpha, beta);
                if (nextScore > alpha) alpha = nextScore;

                if (beta <= alpha || depth >= MAXDEPTH) break;

            }
        }
        return alpha;
    }

    private double scoreBoard(Board board, int depth) {
        if (board.winner() != null && board.winner().equals(player)) {
            return 1.0 / depth;
        } else if (board.isSolved()) {
            return -1.0;
        } else {
            return 0;
        }
    }

    private String nextTurn(String letter) {
        if (letter.equals("x")) {
            return "o";
        } else {
            return "x";
        }

    }
}
