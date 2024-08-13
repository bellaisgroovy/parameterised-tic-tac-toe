package data;


import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void get_index_in_1d_array_returns_value_at_index() {
        Board board = new Board(List.of(3));

        board.set(List.of(1), 2);

        int expected = 2;

        assertEquals(expected, board.get(List.of(1)));
    }

    @Test
    public void get_index_in_2d_array_returns_value_at_index() {
        Board board = new Board(List.of(3,3));
        board.set(List.of(1,1), 2);
        int expected = 2;
        assertEquals(expected, board.get(List.of(1,1)));
    }

    @Test
    public void get_index_in_5d_array_returns_value_at_index() {
        Board board = new Board(List.of(3,12,7,9,2));
        board.set(List.of(2,4,3,6,1), -1);
        int expected = -1;
        assertEquals(expected, board.get(List.of(2,4,3,6,1)));
    }

    @Test
    public void construct_0d_board_fails() {
        try {
            Board board = new Board(List.of());
            fail();
        } catch (NoSuchElementException _) {
        }
    }

    @Test
    public void get_index_out_of_bounds_fails() {
        Board board = new Board(List.of(2,4));
        try {
            int x = board.get(List.of(1,5));
            fail();
        } catch (IndexOutOfBoundsException _) {}
    }

    @Test
    public void construct_9d_board() {
        Board board = new Board(List.of(7,4,1,8,4,9,3,6,4));
        int expected = 9;
        assertEquals(expected, board.getDimensions());
    }


}
