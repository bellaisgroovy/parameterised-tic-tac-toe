package game.boardChecker;

import game.data.GameState;
import game.data.board.Board;
import game.data.board.factory.BoardFactory;
import game.data.board.factory.ListBoardFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleBoardCheckerTest {
    private final BoardFactory boardFactory = new ListBoardFactory();
    
    // tests for many directions of diagonal in many dimensions
    @Test
    public void top_row_3x3_board_3_to_win() {
        List<Integer> boardSize = List.of(3,3);
        int player = 1;
        int streakToWin = 3;

        // create board where top three slots are set to one
        Board board = boardFactory.createBoard(boardSize, streakToWin);
        board.setCellAt(List.of(0,2), player);
        board.setCellAt(List.of(1,2), player);
        board.setCellAt(List.of(2,2), player);

        BoardChecker boardChecker = new SimpleBoardChecker();

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void right_column_3x3_board_3_to_win() {
        List<Integer> boardSize = List.of(3,3);
        int player = 9;
        int streakToWin = 3;

        // create board where top three slots are set to one
        Board board = boardFactory.createBoard(boardSize, streakToWin);
        board.setCellAt(List.of(0,2), player);
        board.setCellAt(List.of(1,2), player);
        board.setCellAt(List.of(2,2), player);

        BoardChecker boardChecker = new SimpleBoardChecker();

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void left_to_right_asc_3x3_board_3_to_win() {
        List<Integer> boardSize = List.of(3,3);
        int streakToWin = 3;
        int player = 2;

        // create board where top three slots are set to one
        Board board = boardFactory.createBoard(boardSize, streakToWin);
        board.setCellAt(List.of(0,0), player);
        board.setCellAt(List.of(1,1), player);
        board.setCellAt(List.of(2,2), player);

        BoardChecker boardChecker = new SimpleBoardChecker();

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void left_to_right_desc_3x3_board_3_to_win() {
        List<Integer> boardSize = List.of(3,3);
        int streakToWin = 3;
        int player = 2;

        // create board where top three slots are set to one
        Board board = boardFactory.createBoard(boardSize, streakToWin);
        board.setCellAt(List.of(0,2), player);
        board.setCellAt(List.of(1,1), player);
        board.setCellAt(List.of(2,0), player);

        BoardChecker boardChecker = new SimpleBoardChecker();

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void back_top_left_to_front_bottom_right_4x4x4_board_4_to_win() {
        List<Integer> boardSize = List.of(4,4,4);
        int streakToWin = 4;
        int player = 7;

        // create board where top three slots are set to one
        Board board = boardFactory.createBoard(boardSize, streakToWin);
        board.setCellAt(List.of(0,3,0), player);
        board.setCellAt(List.of(1,2,1), player);
        board.setCellAt(List.of(2,1,2), player);
        board.setCellAt(List.of(3,0,3), player);

        BoardChecker boardChecker = new SimpleBoardChecker();

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void back_top_right_to_front_bottom_left_4x4x4_board_4_to_win() {
        List<Integer> boardSize = List.of(4,4,4);
        int streakToWin = 4;
        int player = 7;

        // create board where top three slots are set to one
        Board board = boardFactory.createBoard(boardSize, streakToWin);
        board.setCellAt(List.of(3,3,0), player);
        board.setCellAt(List.of(2,2,1), player);
        board.setCellAt(List.of(1,1,2), player);
        board.setCellAt(List.of(0,0,3), player);

        BoardChecker boardChecker = new SimpleBoardChecker();

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void back_bottom_right_to_front_top_left_4x4x4_board_4_to_win() {
        List<Integer> boardSize = List.of(4,4,4);
        int streakToWin = 4;
        int player = 5;

        // create board where top three slots are set to one
        Board board = boardFactory.createBoard(boardSize, streakToWin);
        board.setCellAt(List.of(3,0,0), player);
        board.setCellAt(List.of(2,1,1), player);
        board.setCellAt(List.of(1,2,2), player);
        board.setCellAt(List.of(0,3,3), player);

        BoardChecker boardChecker = new SimpleBoardChecker();

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void back_bottom_left_to_front_top_right_4x4x4_board_4_to_win() {
        List<Integer> boardSize = List.of(4,4,4);
        int streakToWin = 4;
        int player = 11;

        // create board where top three slots are set to one
        Board board = boardFactory.createBoard(boardSize, streakToWin);
        board.setCellAt(List.of(0,0,0), player);
        board.setCellAt(List.of(1,1,1), player);
        board.setCellAt(List.of(2,2,2), player);
        board.setCellAt(List.of(3,3,3), player);

        BoardChecker boardChecker = new SimpleBoardChecker();

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    // tests for sizeRowToWin
    @Test
    public void only_2_in_a_row_3_to_win_no_win() {
        List<Integer> boardSize = List.of(3,3);
        int streakToWin = 3;
        int player = 1;

        // create board where top three slots are set to one
        Board board = boardFactory.createBoard(boardSize, streakToWin);
        board.setCellAt(List.of(0,2), player);
        board.setCellAt(List.of(1,2), player);

        BoardChecker boardChecker = new SimpleBoardChecker();

        assertEquals(GameState.ONGOING.value, boardChecker.winningPlayer(board));
    }

    @Test
    public void more_than_3_in_a_row_3_to_win_win() {
        List<Integer> boardSize = List.of(4,4);
        int streakToWin = 3;
        int player = 1;

        // create board where top three slots are set to one
        Board board = boardFactory.createBoard(boardSize, streakToWin);
        board.setCellAt(List.of(0,1), player);
        board.setCellAt(List.of(1,1), player);
        board.setCellAt(List.of(2,1), player);
        board.setCellAt(List.of(3,1), player);


        BoardChecker boardChecker = new SimpleBoardChecker();

        assertEquals(player, boardChecker.winningPlayer(board));
    }

    @Test
    public void empty_board_no_win() {
        List<Integer> boardSize = List.of(3,6,4,2);
        int streakToWin = 4;

        Board board = boardFactory.createBoard(boardSize, streakToWin);
        BoardChecker boardChecker = new SimpleBoardChecker();

        assertEquals(GameState.ONGOING.value, boardChecker.winningPlayer(board));
    }

    @Test
    public void draw() {
        int streakToWin = 3;
        Board board = boardFactory.createBoard(List.of(3,3), streakToWin);
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

        BoardChecker boardChecker = new SimpleBoardChecker();

        assertEquals(GameState.DRAW.value, boardChecker.winningPlayer(board));
    }
}
