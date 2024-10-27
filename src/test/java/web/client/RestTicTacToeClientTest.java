package web.client;

import api.controller.GameController;
import api.controller.SimpleGameControllerTest;
import game.data.board.Board;
import game.data.board.ListBoard;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import web.TicTacToeApplication;
import web.data.BoardResponse;
import web.data.MoveRequest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TicTacToeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTicTacToeClientTest extends SimpleGameControllerTest {
    @LocalServerPort
    private int port;

    @MockBean
    GameController gameController;

    private static final String BASE_URL = "http://localhost:";
    private static final String BOARD_EXTENSION = "/board";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    @Disabled
    public void setUp() {}

    @Override
    @Test
    public void get_existing_board() {
        when(gameController.getBoard(GAME_NAME)).thenReturn(BOARD);
        String url = BASE_URL + port + BOARD_EXTENSION + "/" + GAME_NAME;


        Board actualResponse = restTemplate.getForObject(url, ListBoard.class);


        assertEquals(BOARD, actualResponse);
    }

    @Override
    @Test
    public void get_nonexistent_board() {
        when(gameController.getBoard(GAME_NAME)).thenThrow(new NoSuchElementException());
        String url = BASE_URL + port + BOARD_EXTENSION + "/" + GAME_NAME;


        HttpClientErrorException exception =
                assertThrows(HttpClientErrorException.class, () -> restTemplate.getForObject(url, BoardResponse.class));


        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }



    @Override
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

    @Override
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

    @Override
    @Test
    @Disabled
    public void check_board_with_winner() {
        super.check_board_with_winner();
    }

    @Override
    @Test
    @Disabled
    public void check_board_with_draw() {
        super.check_board_with_draw();
    }

    @Override
    @Test
    @Disabled
    public void check_ongoing_board() {
        super.check_ongoing_board();
    }

    @Override
    @Test
    @Disabled
    public void create_board_works() {
        super.create_board_works();
    }
}
