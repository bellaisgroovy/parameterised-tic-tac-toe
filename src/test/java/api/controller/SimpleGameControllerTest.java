package api.controller;

import api.data.BoardRepository;
import game.boardChecker.BoardChecker;
import game.data.board.Board;
import game.data.GameState;
import game.data.board.factory.BoardFactory;
import game.data.board.factory.ListBoardFactory;
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
    protected BoardChecker boardChecker;

    @Mock
    protected BoardRepository boardRepository;

    private GameController gameController;

    private final BoardFactory boardFactory = new ListBoardFactory();

    @BeforeEach
    public void setUp() {
        gameController = new SimpleGameController(boardChecker, boardRepository);
    }

    protected final String GAME_NAME = "existing_3x3";
    protected final Board BOARD = boardFactory.createBoard(List.of(3,3), 3);

    @Test
    public void get_existing_board() {
        when(boardRepository.getBoard(GAME_NAME)).thenReturn(BOARD);

        Board actualBoard = gameController.getBoard(GAME_NAME);

        assertEquals(BOARD, actualBoard);
    }

    @Test
    public void get_nonexistent_board() {
        when(boardRepository.getBoard(GAME_NAME)).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> gameController.getBoard(GAME_NAME));
    }

    @Test
    public void play_in_valid_cell() {
        Board board = boardFactory.createBoard(List.of(3,3), 3);
        when(boardRepository.getBoard(GAME_NAME)).thenReturn(board);

        boolean isValidMove = gameController.playInCell(List.of(0,0), 1, GAME_NAME);

        assertTrue(isValidMove);
    }

    @Test
    public void play_in_invalid_cell() {
        when(boardRepository.getBoard(GAME_NAME)).thenReturn(BOARD);

        assertThrows(IllegalArgumentException.class ,() -> gameController.playInCell(List.of(9,9,9), 1, GAME_NAME));
    }

    @Test
    public void check_board_with_winner() {
        when(boardRepository.getBoard(GAME_NAME)).thenReturn(BOARD);
        int winner = 1;
        when(boardChecker.winningPlayer(BOARD)).thenReturn(winner);

        int actualWinner = gameController.getWinner(GAME_NAME);

        assertEquals(winner, actualWinner);
    }

    @Test
    public void check_board_with_draw() {
        when(boardRepository.getBoard(GAME_NAME)).thenReturn(BOARD);
        int winner = GameState.DRAW.value;
        when(boardChecker.winningPlayer(BOARD)).thenReturn(winner);

        int actualWinner = gameController.getWinner(GAME_NAME);

        assertEquals(winner, actualWinner);
    }

    @Test
    public void check_ongoing_board() {
        when(boardRepository.getBoard(GAME_NAME)).thenReturn(BOARD);
        int winner = GameState.ONGOING.value;
        when(boardChecker.winningPlayer(BOARD)).thenReturn(winner);

        int actualWinner = gameController.getWinner(GAME_NAME);

        assertEquals(winner, actualWinner);
    }

    @Test
    public void create_board_works() {
        boolean createdSuccessfully = gameController.createBoard(List.of(3, 3), 3, GAME_NAME);

        assertTrue(createdSuccessfully);
    }
}
