package api.data;

import game.data.board.Board;
import game.data.board.factory.BoardFactory;
import game.data.board.factory.ListBoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileBoardSaveReaderTest {
    @BeforeEach
    public void instantiateReader() {
        reader = new FileBoardSaveReader();
        reader.setSaveFolder(new File("src/test/resources/saves"));

        setBoardFactory(new ListBoardFactory());
    }

    @Test
    public void get_existing_board() {
        Board expectedBoard = boardFactory.createBoard(List.of(3,3), 3);
        expectedBoard.setCellAt(List.of(0,0), 1);
        expectedBoard.setCellAt(List.of(0,1), 2);
        expectedBoard.setCellAt(List.of(2,1), 8);

        Board board = reader.getBoard("mid_game_3x3");

        assertEquals(expectedBoard, board);
    }

    private BoardFactory boardFactory;

    private void setBoardFactory(BoardFactory boardFactory) {
        this.boardFactory = boardFactory;
    }

    private FileBoardSaveReader reader;
}
