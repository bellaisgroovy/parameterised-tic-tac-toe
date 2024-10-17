package api.data;

import game.data.board.Board;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileBoardSaveWriter implements BoardSaveWriter {
    @Override
    public void saveBoard(Board board, String name) {
        StringBuilder content = new StringBuilder();
        content.append(toBoardFormat(board.getSizes().toString()));
        content.append("\n");
        content.append(toBoardFormat(board.getFlatBoard().toString()));
        content.append("\n");
        content.append(board.getStreakToWin());

        try {
            FileWriter save = new FileWriter(saveFolder.getPath() + "/" + name + ".board");
            save.append(content);
            save.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSaveFolder(File saveFolder) {
        this.saveFolder = saveFolder;
    }

    private String toBoardFormat(String unformatted) {
        unformatted = unformatted.replaceAll(" ", "");
        return unformatted.substring(1, (unformatted.length() - 1));
    }

    private File saveFolder = new File("src/main/resources/saves");
}
