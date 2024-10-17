package api.data;

import game.data.board.Board;

public interface BoardSaveWriter {
    /**
     * @param board
     * board to be saved
     * @param saveName
     * name given
     */
    void saveBoard(Board board, String saveName);
}
