package business.game;

import business.data.Board;

import java.util.List;

public interface GameService {
    /**
     * gets current state of the specified game
     * @param gameName
     * name of game to retrieve
     * @return current state of board
     */
    Board getBoard(String gameName);

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
}
