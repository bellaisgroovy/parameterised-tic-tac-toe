package game.data.board;

import game.data.board.factory.BoardFactory;
import game.data.board.factory.SimpleBoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SimpleBoardTest {
    private BoardFactory boardFactory;
    protected BoardFactory getBoardFactory() {
        return boardFactory;
    }
    protected void setBoardFactory(BoardFactory boardFactory) {
        this.boardFactory = boardFactory;
    }

    @BeforeEach
    public void setUp() {
        setBoardFactory(new SimpleBoardFactory());
    }

    @Test
    public void get_CellAt_index_in_1D_array_returns_value_at_index() {
        Board board = getBoardFactory().createBoard(List.of(3), 3);

        board.setCellAt(List.of(1), 2);

        int expected = 2;

        assertEquals(expected, board.getCellAt(List.of(1)));
    }

    @Test
    public void get_CellAt_index_in_2D_array_returns_value_at_index() {
        Board board = getBoardFactory().createBoard(List.of(3,3),3 );
        board.setCellAt(List.of(1,1), 2);
        int expected = 2;
        assertEquals(expected, board.getCellAt(List.of(1,1)));
    }

    @Test
    public void get_CellAt_index_in_5D_array_returns_value_at_index() {
        Board board = getBoardFactory().createBoard(List.of(3,12,7,9,2), 3);
        board.setCellAt(List.of(2,4,3,6,1), -1);
        int expected = -1;
        assertEquals(expected, board.getCellAt(List.of(2,4,3,6,1)));
    }

    @Test
    public void construct_0d_board_fails() {
        try {
            getBoardFactory().createBoard(List.of(),3 );
            fail();
        } catch (NoSuchElementException _) {
        }
    }

    @Test
    public void get_CellAt_index_out_of_bounds_fails() {
        Board board = getBoardFactory().createBoard(List.of(2,4), 3);
        try {
            board.getCellAt(List.of(1,5));
            fail();
        } catch (IndexOutOfBoundsException _) {}
    }

    @Test
    public void construct_9d_board() {
        Board board = getBoardFactory().createBoard(List.of(7,4,1,8,4,9,3,6,4), 3);
        int expected = 9;
        assertEquals(expected, board.getNoDimensions());
    }

    @Test
    public void find_sizes_of_board() {
        List<Integer> expectedSizes = List.of(4,3,2,1);
        Board board = getBoardFactory().createBoard(expectedSizes, 3);
        assertEquals(expectedSizes, board.getSizes());
    }

    @Test
    public void toString_board() {
        Board board = getBoardFactory().createBoard(List.of(2, 2, 2, 2), 3);
        String expected = "[[[[0, 0], [0, 0]], [[0, 0], [0, 0]]], [[[0, 0], [0, 0]], [[0, 0], [0, 0]]]]";
        assertEquals(expected, board.toString());
    }

    @Test
    public void set_max_index() {
        Board board = getBoardFactory().createBoard(List.of(3,3), 3);
        List<Integer> coordinate = List.of(2,2);
        int player = 2;
        board.setCellAt(coordinate, player);
        assertEquals(player ,board.getCellAt(coordinate));
    }

    @Test
    public void fail_to_set_CellAt_negative_index() {
        Board board = getBoardFactory().createBoard(List.of(9,4,7), 3);
        try {
            board.setCellAt(List.of(-1,3,3), 9);
            fail();
        } catch (IndexOutOfBoundsException _) {}
    }

    @Test
    public void fail_to_set_CellAt_index_out_of_bounds() {
        Board board = getBoardFactory().createBoard(List.of(9,4,7), 3);
        try {
            board.setCellAt(List.of(11,3,3), 9);
            fail();
        } catch (IndexOutOfBoundsException _) {}
    }

    @Test
    public void can_retrieve_sizes_of_board() {
        List<Integer> expectedSizes = List.of(5,4,3,2,1);
        Board board = getBoardFactory().createBoard(expectedSizes, 3);
        assertEquals(expectedSizes, board.getSizes());
    }

    @Test
    public void can_retrieve_dimensions_of_board() {
        List<Integer> sizes = List.of(5,4,3,7,5, 2,1);
        Board board = getBoardFactory().createBoard(sizes, 3);
        int expectedDimensions = sizes.size();
        assertEquals(expectedDimensions, board.getNoDimensions());
    }

    @Test
    public void set_too_few_indices() {
        Board board = getBoardFactory().createBoard(List.of(3,3), 3);
        try {
            board.setCellAt(List.of(1), 2);
            fail();
        } catch (IllegalArgumentException _) {}
    }

    @Test
    public void set_too_many_indices() {
        Board board = getBoardFactory().createBoard(List.of(3,3), 3);
        try {
            board.setCellAt(List.of(1,2,7), 2);
            fail();
        } catch (IllegalArgumentException _) {}
    }
}
