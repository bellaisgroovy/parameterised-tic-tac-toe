package business.boardChecker;

import business.boardChecker.BoardChecker;
import business.boardChecker.SimpleBoardChecker;
import business.data.GameState;
import business.data.SimpleBoard;
import business.data.Board;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleBoardCheckerTest {
    // tests for many directions of diagonal in many dimensions
    @Test
    public void top_row_3x3_board_3_to_win() {
        List<Integer> boardSize = List.of(3,3);
        int sizeRowToWin = 3;
        int player = 1;

        // create board where top three slots are set to one
        Board board = new SimpleBoard(boardSize);
        board.setCellAt(List.of(0,2), player);
        board.setCellAt(List.of(1,2), player);
        board.setCellAt(List.of(2,2), player);

        BoardChecker boardChecker = new SimpleBoardChecker(sizeRowToWin);

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void right_column_3x3_board_3_to_win() {
        List<Integer> boardSize = List.of(3,3);
        int sizeRowToWin = 3;
        int player = 9;

        // create board where top three slots are set to one
        Board board = new SimpleBoard(boardSize);
        board.setCellAt(List.of(0,2), player);
        board.setCellAt(List.of(1,2), player);
        board.setCellAt(List.of(2,2), player);

        BoardChecker boardChecker = new SimpleBoardChecker(sizeRowToWin);

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void left_to_right_asc_3x3_board_3_to_win() {
        List<Integer> boardSize = List.of(3,3);
        int sizeRowToWin = 3;
        int player = 2;

        // create board where top three slots are set to one
        Board board = new SimpleBoard(boardSize);
        board.setCellAt(List.of(0,0), player);
        board.setCellAt(List.of(1,1), player);
        board.setCellAt(List.of(2,2), player);

        BoardChecker boardChecker = new SimpleBoardChecker(sizeRowToWin);

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void left_to_right_desc_3x3_board_3_to_win() {
        List<Integer> boardSize = List.of(3,3);
        int sizeRowToWin = 3;
        int player = 2;

        // create board where top three slots are set to one
        Board board = new SimpleBoard(boardSize);
        board.setCellAt(List.of(0,2), player);
        board.setCellAt(List.of(1,1), player);
        board.setCellAt(List.of(2,0), player);

        BoardChecker boardChecker = new SimpleBoardChecker(sizeRowToWin);

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void back_top_left_to_front_bottom_right_4x4x4_board_4_to_win() {
        List<Integer> boardSize = List.of(4,4,4);
        int sizeRowToWin = 4;
        int player = 7;

        // create board where top three slots are set to one
        Board board = new SimpleBoard(boardSize);
        board.setCellAt(List.of(0,3,0), player);
        board.setCellAt(List.of(1,2,1), player);
        board.setCellAt(List.of(2,1,2), player);
        board.setCellAt(List.of(3,0,3), player);

        BoardChecker boardChecker = new SimpleBoardChecker(sizeRowToWin);

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void back_top_right_to_front_bottom_left_4x4x4_board_4_to_win() {
        List<Integer> boardSize = List.of(4,4,4);
        int sizeRowToWin = 4;
        int player = 7;

        // create board where top three slots are set to one
        Board board = new SimpleBoard(boardSize);
        board.setCellAt(List.of(3,3,0), player);
        board.setCellAt(List.of(2,2,1), player);
        board.setCellAt(List.of(1,1,2), player);
        board.setCellAt(List.of(0,0,3), player);

        BoardChecker boardChecker = new SimpleBoardChecker(sizeRowToWin);

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void back_bottom_right_to_front_top_left_4x4x4_board_4_to_win() {
        List<Integer> boardSize = List.of(4,4,4);
        int sizeRowToWin = 4;
        int player = 5;

        // create board where top three slots are set to one
        Board board = new SimpleBoard(boardSize);
        board.setCellAt(List.of(3,0,0), player);
        board.setCellAt(List.of(2,1,1), player);
        board.setCellAt(List.of(1,2,2), player);
        board.setCellAt(List.of(0,3,3), player);

        BoardChecker boardChecker = new SimpleBoardChecker(sizeRowToWin);

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void back_bottom_left_to_front_top_right_4x4x4_board_4_to_win() {
        List<Integer> boardSize = List.of(4,4,4);
        int sizeRowToWin = 4;
        int player = 11;

        // create board where top three slots are set to one
        Board board = new SimpleBoard(boardSize);
        board.setCellAt(List.of(0,0,0), player);
        board.setCellAt(List.of(1,1,1), player);
        board.setCellAt(List.of(2,2,2), player);
        board.setCellAt(List.of(3,3,3), player);

        BoardChecker boardChecker = new SimpleBoardChecker(sizeRowToWin);

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    // tests for sizeRowToWin
    @Test
    public void only_2_in_a_row_3_to_win_no_win() {
        List<Integer> boardSize = List.of(3,3);
        int sizeRowToWin = 3;
        int player = 1;

        // create board where top three slots are set to one
        Board board = new SimpleBoard(boardSize);
        board.setCellAt(List.of(0,2), player);
        board.setCellAt(List.of(1,2), player);

        BoardChecker boardChecker = new SimpleBoardChecker(sizeRowToWin);

        assertEquals(GameState.ONGOING.value, boardChecker.winningPlayer(board));
    }

    @Test
    public void more_than_3_in_a_row_3_to_win_win() {
        List<Integer> boardSize = List.of(4,4);
        int sizeRowToWin = 3;
        int player = 1;

        // create board where top three slots are set to one
        Board board = new SimpleBoard(boardSize);
        board.setCellAt(List.of(0,1), player);
        board.setCellAt(List.of(1,1), player);
        board.setCellAt(List.of(2,1), player);
        board.setCellAt(List.of(3,1), player);


        BoardChecker boardChecker = new SimpleBoardChecker(sizeRowToWin);

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void empty_board_no_win() {
        List<Integer> boardSize = List.of(3,6,4,2);
        int sizeRowToWin = 4;

        Board board = new SimpleBoard(boardSize);
        BoardChecker boardChecker = new SimpleBoardChecker(sizeRowToWin);

        assertEquals(GameState.ONGOING.value, boardChecker.winningPlayer(board));
    }

    @Test
    public void draw() {
        Board board = new SimpleBoard(List.of(3,3));
        int nought = 1;
        int cross = 2;
        board.setCellAt(List.of(0,0), nought);
        board.setCellAt(List.of(0,1), cross);
        board.setCellAt(List.of(0,2), nought);
        board.setCellAt(List.of(1,0), cross);
        board.setCellAt(List.of(1,1), cross);
        board.setCellAt(List.of(1,2), nought);
        board.setCellAt(List.of(2,0), nought);
        board.setCellAt(List.of(2,1), nought);
        board.setCellAt(List.of(2,2), cross);

        int sizeRowToWin = 3;
        BoardChecker boardChecker = new SimpleBoardChecker(sizeRowToWin);

        assertEquals(GameState.DRAW.value, boardChecker.winningPlayer(board));
    }
}
