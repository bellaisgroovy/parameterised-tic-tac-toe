package api.data;

import game.data.board.Board;
import game.data.board.SimpleBoard;
import game.data.board.factory.BoardFactory;
import game.data.board.factory.ListBoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileBoardSaveWriterTest {
    @BeforeEach
    public void instantiateWriter() {
        File saveFolder = new File("src/test/resources/saves");
        writer = new FileBoardSaveWriter();
        writer.setSaveFolder(saveFolder);

        reader = new FileBoardSaveReader();
        reader.setSaveFolder(saveFolder);

        setBoardFactory(new ListBoardFactory());
    }

    private FileBoardSaveWriter writer;

    private FileBoardSaveReader reader;

    private BoardFactory boardFactory;

    private void setBoardFactory(BoardFactory boardFactory) {
        this.boardFactory = boardFactory;
    }

    @Test
    public void save_board_overwrite() {
        String saveName = "overwritten_4x5";
        int streakToWin = 4;
        // save one board
        Board originalBoard = boardFactory.createBoard(List.of(4,5), streakToWin);
        originalBoard.setCellAt(List.of(0,0), 1);
        originalBoard.setCellAt(List.of(3,4), 2);

        writer.saveBoard(originalBoard, saveName);
        assertEquals(originalBoard, reader.getBoard(saveName));

        //save another board
        Board newBoard = boardFactory.createBoard(List.of(4,5), streakToWin);
        newBoard.setCellAt(List.of(0,0), 2);
        newBoard.setCellAt(List.of(3,4), 1);

        writer.saveBoard(newBoard, saveName);
        assertEquals(newBoard, reader.getBoard(saveName));
    }
    @Test
    public void save_board_fresh() {
        String saveName = "fresh_board_2x1";

        // save one board
        Board board = boardFactory.createBoard(List.of(2, 1), 2);
        board.setCellAt(List.of(0,0), 1);
        board.setCellAt(List.of(1,0), 2);
        writer.saveBoard(board, saveName);
        assertEquals(board, reader.getBoard(saveName));
    }
}
