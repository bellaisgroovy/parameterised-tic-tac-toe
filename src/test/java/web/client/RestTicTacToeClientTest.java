package web.client;

import api.controller.GameController;
import game.data.board.Board;
import game.data.board.ListBoard;
import game.data.board.factory.BoardFactory;
import game.data.board.factory.ListBoardFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import web.TicTacToeApplication;
import web.data.BoardCreationRequest;
import web.data.MoveRequest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TicTacToeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTicTacToeClientTest {
    @LocalServerPort
    private int port;

    @MockBean
    GameController gameController;

    private static final String BASE_URL = "http://localhost:";
    private static final String BOARD_EXTENSION = "/board";
    private static final String WINNER_EXTENSION = "/winner";

    private final BoardFactory boardFactory = new ListBoardFactory();
    private final Board BOARD = boardFactory.createBoard(List.of(3,3), 3);
    private final String GAME_NAME = "existing_3x3";

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void get_existing_board() {
        when(gameController.getBoard(GAME_NAME)).thenReturn(BOARD);
        String url = BASE_URL + port + BOARD_EXTENSION + "/" + GAME_NAME;


        Board actualResponse = restTemplate.getForObject(url, ListBoard.class);


        assertEquals(BOARD, actualResponse);
    }

    @Test
    public void get_nonexistent_board() {
        when(gameController.getBoard(GAME_NAME)).thenThrow(new NoSuchElementException());
        String url = BASE_URL + port + BOARD_EXTENSION + "/" + GAME_NAME;


        HttpClientErrorException exception =
                assertThrows(HttpClientErrorException.class, () -> restTemplate.getForObject(url, ListBoard.class));


        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    public void play_in_valid_cell() {
        List<Integer> coordinate = List.of(1,2);
        int player = 7;
        MoveRequest moveRequest = new MoveRequest();
        moveRequest.setCoordinate(coordinate);
        moveRequest.setPlayer(player);

        when(gameController.playInCell(coordinate, player, GAME_NAME)).thenReturn(true);


        restTemplate.put(BASE_URL + port + BOARD_EXTENSION + "/" + GAME_NAME, moveRequest);


        verify(gameController, times(1)).playInCell(coordinate, player, GAME_NAME);
    }

    @Test
    public void play_in_invalid_cell() {
        List<Integer> coordinate = List.of(9,9,9,9);
        int player = 7;
        MoveRequest moveRequest = new MoveRequest();
        moveRequest.setCoordinate(coordinate);
        moveRequest.setPlayer(player);

        when(gameController.playInCell(coordinate, player, GAME_NAME)).thenThrow(new IllegalArgumentException());


        HttpClientErrorException exception =
                assertThrows(HttpClientErrorException.class, () -> restTemplate.put(BASE_URL + port + BOARD_EXTENSION + "/" + GAME_NAME, moveRequest));


        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    public void check_board_with_winner() {
        int expectedWinner = 2;

        when(gameController.getWinner(GAME_NAME)).thenReturn(expectedWinner);


        Integer actualWinner = restTemplate.getForObject(BASE_URL + port + BOARD_EXTENSION + "/" + GAME_NAME + WINNER_EXTENSION, Integer.class);

        assertEquals(expectedWinner, actualWinner);
        verify(gameController, times(1)).getWinner(GAME_NAME);
    }

    @Test
    public void create_board_works() {
        BoardCreationRequest boardCreationRequest = new BoardCreationRequest();
        boardCreationRequest.setSizes(List.of(4,4,4));
        boardCreationRequest.setStreakToWin(4);

        when(gameController.getBoard(GAME_NAME)).thenReturn(BOARD);


        ResponseEntity<ListBoard> actualResponse = restTemplate.postForEntity(BASE_URL + port + BOARD_EXTENSION + "/" + GAME_NAME, boardCreationRequest, ListBoard.class);


        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        assertEquals(actualResponse.getBody(), BOARD);

        verify(gameController, times(1)).createBoard(boardCreationRequest.getSizes(), boardCreationRequest.getStreakToWin(), GAME_NAME);
        verify(gameController, times(1)).getBoard(GAME_NAME);
    }
}
