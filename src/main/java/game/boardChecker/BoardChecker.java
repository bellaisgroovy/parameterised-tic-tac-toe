package game.boardChecker;

import game.data.board.Board;

public interface BoardChecker {
    /**
     * checks which player, if any, has won
     * @param board
     * a Board object
     * @return an int representing game state, -1 = ongoing, 0 = draw, positive integer = the player who won
     */
    int winningPlayer(Board board);
}
