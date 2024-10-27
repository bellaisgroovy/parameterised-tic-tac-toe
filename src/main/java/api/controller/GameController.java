package api.controller;

import game.data.board.Board;

import java.util.List;
import java.util.NoSuchElementException;

public interface GameController {
    /**
     * gets current state of the specified game if exists
     * @param gameName
     * name of game to retrieve
     * @return current state of board
     */
    Board getBoard(String gameName) throws NoSuchElementException;

    /**
     * sets cell at cellCoordinate to player in game of name gameName
     * @param cellCoordinate
     * coordinates in each dimension of board playing in
     * @param player
     * int representing player playing
     * @param gameName
     * name of game to play on
     * @return
     * true if move is valid, false/error if not
     */
    boolean playInCell(List<Integer> cellCoordinate, int player, String gameName);

    /**
     * checks which player, if any, has won in specified game
     * @param gameName
     * name of a game
     * @return an int representing game state, -1 = ongoing, 0 = draw, positive integer = the player who won
     */
    int getWinner(String gameName);

    /**
     * creates a game with specified name
     * @param sizes
     * sizes of board to be created
     * @param streakToWin
     * number of cells in a row a player needs to win
     * @param gameName
     * name of game to be created
     * @return true if created successfully, error if not
     */
    boolean createBoard(List<Integer> sizes, int streakToWin, String gameName);
}
