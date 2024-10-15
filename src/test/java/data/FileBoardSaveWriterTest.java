package data;

import game.data.Board;
import game.data.SimpleBoard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileBoardSaveWriterTest {
    @BeforeAll
    public static void instantiateWriter() {
        File saveFolder = new File("src/test/resources/saves");
        writer = new FileBoardSaveWriter();
        writer.setSaveFolder(saveFolder);

        reader = new FileBoardSaveReader();
        reader.setSaveFolder(saveFolder);
    }

    static FileBoardSaveWriter writer;
    static FileBoardSaveReader reader;

    @Test
    public void save_board_overwrite() {
        String saveName = "overwritten_4x5";
        int streakToWin = 4;
        // save one board
        Board originalBoard = new SimpleBoard(List.of(4,5), streakToWin, List.of(1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2));
        writer.saveBoard(originalBoard, saveName);
        assertEquals(originalBoard, reader.getBoard(saveName));

        //save another board
        Board newBoard = new SimpleBoard(List.of(4,5), streakToWin, List.of(0,0,0,0,0,0,1,0,0,0,0,0,0,2,0,0,0,0,0,0));
        writer.saveBoard(newBoard, saveName);
        assertEquals(newBoard, reader.getBoard(saveName));
    }
    @Test
    public void save_board_fresh() {
        String saveName = "fresh_board_2x1";

        // save one board
        Board board = new SimpleBoard(List.of(2,1), 2, List.of(1,2));
        writer.saveBoard(board, saveName);
        assertEquals(board, reader.getBoard(saveName));
    }
}
