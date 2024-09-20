package player;

import data.Board;
import data.SimpleBoard;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SimplePlayerTest {
    @Test
    public void testPlayValidMove() {
        Board board = new SimpleBoard(List.of(3,3));
        SimplePlayer simplePlayer = new SimplePlayer();
        final String INPUTS = """
                2
                2""";
        simplePlayer.setScanner(new Scanner(INPUTS));

        List<Integer> moves = simplePlayer.play(board);

        assertEquals(moves, List.of(2,2));
    }

    @Test
    public void move_in_occupied_cell_invalid() {
        Board board = new SimpleBoard(List.of(3,3));
        board.setCellAt(List.of(1,1), 1);
        SimplePlayer simplePlayer = new SimplePlayer(); // TODO rewrite the board TT
        final String INPUTS = """
                1
                1
                2
                2""";
        simplePlayer.setScanner(new Scanner(INPUTS));

        List<Integer> move = simplePlayer.play(board);

        assertEquals(List.of(2,2), move);
    }

    @Test
    public void non_integer_input_asks_again() {
        Board board = new SimpleBoard(List.of(3,3));
        SimplePlayer simplePlayer = new SimplePlayer();
        final String INPUTS = """
                a
                2
                2""";
        simplePlayer.setScanner(new Scanner(INPUTS));

        List<Integer> move = simplePlayer.play(board);

        assertEquals(List.of(2,2), move);
    }

    @Test
    public void out_of_bounds_input_asks_again() {
        Board board = new SimpleBoard(List.of(3,3));
        SimplePlayer simplePlayer = new SimplePlayer();
        final String INPUTS = """
                9
                -4
                3
                0
                2""";
        simplePlayer.setScanner(new Scanner(INPUTS));

        List<Integer> move = simplePlayer.play(board);

        assertEquals(List.of(0,2), move);
    }
}
