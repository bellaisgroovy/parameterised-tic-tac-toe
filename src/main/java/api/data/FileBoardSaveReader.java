package api.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.data.board.Board;
import game.data.board.ListBoard;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public class FileBoardSaveReader implements BoardSaveReader{

    @Override
    public Board getBoard(String name) throws NoSuchElementException {
        try {
            return mapper.readValue(new File(saveFolder.getPath() + "/" + name + ".board"), ListBoard.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSaveFolder(File saveFolder) {
        this.saveFolder = saveFolder;
    }

    private File saveFolder = new File("src/main/resources/saves");

    private final ObjectMapper mapper = new ObjectMapper();

}
