package data;

import business.data.Board;

public interface BoardSaveWriter {
    void saveBoard(Board board, String saveName);
}
