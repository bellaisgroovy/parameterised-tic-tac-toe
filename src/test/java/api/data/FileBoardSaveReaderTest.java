package api.data;

import game.data.board.Board;
import game.data.board.SimpleBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileBoardSaveReaderTest {
    @BeforeEach // TODO switch to @BeforeAll
    public void instantiateReader() {
        reader = new FileBoardSaveReader();
        reader.setSaveFolder(new File("src/test/resources/saves"));
    }

    private FileBoardSaveReader reader;
    @Test
    public void get_existing_board() {
        Board board = reader.getBoard("mid_game_3x3");

        Board expectedBoard = new SimpleBoard(List.of(3,3), 3, List.of(1,2,1,2,0,0,0,0,0));
        assertEquals(expectedBoard, board);
    }
}
