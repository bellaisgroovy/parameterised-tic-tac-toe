package data;

import game.data.Board;

public interface BoardSaveWriter {
    void saveBoard(Board board, String saveName);
}
