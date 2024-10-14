package data;

import business.data.Board;

public interface BoardSaveReader {
    /**
     * gets board of name if it exists
     * @param name
     * name of board to retrieve
     * @return board specified or error
     */
    Board getBoard(String name);
}
