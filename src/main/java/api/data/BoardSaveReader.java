package api.data;

import game.data.board.Board;

import java.util.NoSuchElementException;

public interface BoardSaveReader {
    /**
     * gets board of name if it exists
     * @param name
     * name of board to retrieve
     * @return board specified or error
     */
    Board getBoard(String name) throws NoSuchElementException;
}
