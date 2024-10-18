package game.data.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.data.board.factory.ListBoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListBoardTest extends SimpleBoardTest {
    @Override
    @BeforeEach
    public void setUp() {
        setBoardFactory(new ListBoardFactory());
    }

    @Override
    @Test
    public void toString_board() {
        Board board = getBoardFactory().createBoard(List.of(3,3,3,3), 3);
        String expected = "[[[[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0]]],[[[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0]]],[[[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0]],[[0,0,0],[0,0,0],[0,0,0]]]]";
        assertEquals(expected, board.toString());
    }

    @Test
    public void construct_deeply_immutable_board() {
        Board board = getBoardFactory().createBoard(List.of(3,3), 3);

        int x = 1;
        List<Integer> posX = List.of(0,0);
        int o = 2;
        List<Integer> posO = List.of(1,2);
        int emptyCell = 0;
        board.setCellAt(posX, x);
        board.setCellAt(posO, o);

        assertEquals(x, board.getCellAt(posX));
        assertEquals(o, board.getCellAt(posO));

        assertEquals(emptyCell, board.getCellAt(List.of(0,1)));
        assertEquals(emptyCell, board.getCellAt(List.of(0,2)));
        assertEquals(emptyCell, board.getCellAt(List.of(1,0)));
        assertEquals(emptyCell, board.getCellAt(List.of(1,1)));
        assertEquals(emptyCell, board.getCellAt(List.of(2,0)));
        assertEquals(emptyCell, board.getCellAt(List.of(2,1)));
        assertEquals(emptyCell, board.getCellAt(List.of(2,2)));
    }
}
