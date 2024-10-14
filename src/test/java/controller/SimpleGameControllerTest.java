package controller;

import data.BoardRepository;
import game.boardChecker.BoardChecker;
import game.data.Board;
import game.data.GameState;
import game.data.SimpleBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SimpleGameControllerTest {
    @Mock
    BoardChecker boardChecker;

    @Mock
    BoardRepository boardRepository;

    GameController gameController;

    @BeforeEach
    public  void setUp() {
        gameController = new SimpleGameController(boardChecker, boardRepository);
    }

    final String GAME_NAME = "existing_3x3";

    @Test
    public void get_existing_board() {
        Board board = new SimpleBoard(List.of(3,3));
        when(boardRepository.getBoard(GAME_NAME)).thenReturn(board);

        Board actualBoard = gameController.getBoard(GAME_NAME);

        assertEquals(board, actualBoard);
    }

    @Test
    public void get_nonexistent_board() {
        when(boardRepository.getBoard(GAME_NAME)).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> gameController.getBoard(GAME_NAME));
    }

    @Test
    public void play_in_valid_cell() {
        Board board = new SimpleBoard(List.of(3,3));
        when(boardRepository.getBoard(GAME_NAME)).thenReturn(board);

        boolean isValidMove = gameController.playInCell(List.of(0,0), 1, GAME_NAME);

        assertTrue(isValidMove);
    }

    @Test
    public void play_in_invalid_cell() {
        Board board = new SimpleBoard(List.of(3,3));
        when(boardRepository.getBoard(GAME_NAME)).thenReturn(board);

        assertThrows(IllegalArgumentException.class ,() -> gameController.playInCell(List.of(9,9,9), 1, GAME_NAME));
    }

    @Test
    public void check_board_with_winner() {
        Board board = new SimpleBoard(List.of(3,3));
        when(boardRepository.getBoard(GAME_NAME)).thenReturn(board);
        int winner = 1;
        when(boardChecker.winningPlayer(board)).thenReturn(winner);

        int actualWinner = gameController.getWinner(GAME_NAME);

        assertEquals(winner, actualWinner);
    }

    @Test
    public void check_board_with_draw() {
        Board board = new SimpleBoard(List.of(3,3));
        when(boardRepository.getBoard(GAME_NAME)).thenReturn(board);
        int winner = GameState.DRAW.value;
        when(boardChecker.winningPlayer(board)).thenReturn(winner);

        int actualWinner = gameController.getWinner(GAME_NAME);

        assertEquals(winner, actualWinner);
    }

    @Test
    public void check_ongoing_board() {
        Board board = new SimpleBoard(List.of(3,3));
        when(boardRepository.getBoard(GAME_NAME)).thenReturn(board);
        int winner = GameState.ONGOING.value;
        when(boardChecker.winningPlayer(board)).thenReturn(winner);

        int actualWinner = gameController.getWinner(GAME_NAME);

        assertEquals(winner, actualWinner);
    }

    @Test
    public void create_board_works() {
        boolean createdSuccessfully = gameController.createBoard(List.of(3, 3), GAME_NAME);

        assertTrue(createdSuccessfully);
    }
}
