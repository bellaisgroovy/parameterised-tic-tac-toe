package web.client;

import api.controller.SimpleGameControllerTest;
import api.data.BoardRepository;
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

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TicTacToeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTicTacToeClientTest extends SimpleGameControllerTest {
    // TODO use WebClient instead of RestTemplate
    @LocalServerPort
    private int port;

    @MockBean
    BoardRepository boardRepository;

    private static final String BASE_URL = "http://localhost:";
    private static final String BOARD_EXTENSION = "/board";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    @Disabled
    public void setUp() {}

    @Override
    @Test
    public void get_existing_board() {
        when(boardRepository.getBoard(GAME_NAME)).thenReturn(BOARD);
        String url = BASE_URL + port + BOARD_EXTENSION + "/" + GAME_NAME;

        Board actualResponse = restTemplate.getForObject(url, ListBoard.class);

        assertEquals(BOARD, actualResponse);
    }

    @Override
    @Test
    public void get_nonexistent_board() {
        when(boardRepository.getBoard(GAME_NAME)).thenThrow(new NoSuchElementException());
        String url = BASE_URL + port + BOARD_EXTENSION + "/" + GAME_NAME;

        HttpClientErrorException exception =
                assertThrows(HttpClientErrorException.class, () -> restTemplate.getForObject(url, BoardResponse.class));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }



    @Override
    @Test
    @Disabled
    public void play_in_valid_cell() {
        super.play_in_valid_cell();
    }

    @Override
    @Test
    @Disabled
    public void play_in_invalid_cell() {
        super.play_in_invalid_cell();
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
