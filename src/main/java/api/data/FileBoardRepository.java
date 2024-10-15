package api.data;

import game.data.Board;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class FileBoardRepository implements BoardRepository {

    @Override
    public Board getBoard(String name) throws NoSuchElementException {
        return boardSaveReader.getBoard(name);
    }

    @Override
    public void saveBoard(Board board, String saveName) {
        boardSaveWriter.saveBoard(board, saveName);
    }

    private BoardSaveReader boardSaveReader = new FileBoardSaveReader();
    private BoardSaveWriter boardSaveWriter = new FileBoardSaveWriter();
}
