package data;

import game.data.Board;

import java.util.NoSuchElementException;

public interface BoardRepository {
    /**
     * gets board of name if it exists
     * @param name
     * name of board to retrieve
     * @return board specified or error
     */
    Board getBoard(String name) throws NoSuchElementException;

    /**
     * @param board
     * board to be saved
     * @param saveName
     * name given
     */
    void saveBoard(Board board, String saveName);
}
