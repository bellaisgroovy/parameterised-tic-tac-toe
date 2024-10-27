package web;


import api.controller.GameController;
import game.data.board.Board;
import game.data.board.factory.BoardFactory;
import game.data.board.factory.ListBoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import web.data.BoardCreationRequest;
import web.data.MoveRequest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RestTicTacToeControllerTest {
    private final BoardFactory boardFactory = new ListBoardFactory();

    private final String GAME_NAME = "existing_3x3";

    private final Board BOARD = boardFactory.createBoard(List.of(3,3), 3);

    @Mock
    private GameController gameController;

    private RestTicTacToeController restController;

    @BeforeEach
    public void setUp() {
        restController = new RestTicTacToeController(gameController);
    }

    @Test
    public void get_existing_board() {
        when(gameController.getBoard(GAME_NAME)).thenReturn(BOARD);

        Board actualResponse = restController.getBoard(GAME_NAME);

        assertEquals(BOARD, actualResponse);
    }

    @Test
    public void get_nonexistent_board() {
        when(gameController.getBoard(GAME_NAME)).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> restController.getBoard(GAME_NAME));
    }

    @Test
    public void play_in_valid_cell() {
        List<Integer> coordinate = List.of(1,2);
        int player = 7;
        MoveRequest moveRequest = new MoveRequest();
        moveRequest.setCoordinate(coordinate);
        moveRequest.setPlayer(player);

        when(gameController.playInCell(coordinate, player, GAME_NAME)).thenReturn(true);


        restController.playInCell(GAME_NAME, moveRequest);


        verify(gameController, times(1)).playInCell(coordinate, player, GAME_NAME);
    }

    @Test
    public void check_board() {
        int expectedWinner = 2;

        when(gameController.getWinner(GAME_NAME)).thenReturn(expectedWinner);


        int actualWinner = restController.getWinner(GAME_NAME);

        assertEquals(expectedWinner, actualWinner);
        verify(gameController, times(1)).getWinner(GAME_NAME);
    }

    @Test
    public void create_board_works() {
        BoardCreationRequest boardCreationRequest = new BoardCreationRequest();
        boardCreationRequest.setSizes(List.of(4,4,4));
        boardCreationRequest.setStreakToWin(4);

        when(gameController.getBoard(GAME_NAME)).thenReturn(BOARD);


        ResponseEntity<Board> actualResponse = restController.createBoard(GAME_NAME, boardCreationRequest);


        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        assertEquals(actualResponse.getBody(), BOARD);

        verify(gameController, times(1)).createBoard(boardCreationRequest.getSizes(), boardCreationRequest.getStreakToWin(), GAME_NAME);
        verify(gameController, times(1)).getBoard(GAME_NAME);
    }
}
