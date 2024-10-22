package api.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.data.board.Board;

import java.io.File;
import java.io.IOException;

public class FileBoardSaveWriter implements BoardSaveWriter {
    @Override
    public void saveBoard(Board board, String name) {
        try {
            mapper.writeValue(new File(saveFolder.getPath() + "/" + name + ".board"), board);
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
