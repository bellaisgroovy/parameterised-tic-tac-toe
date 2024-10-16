package web;


import api.data.BoardRepository;
import game.boardChecker.BoardChecker;
import game.data.Board;
import game.data.SimpleBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import web.data.BoardResponse;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RestTicTacToeControllerTest {
    private RestTicTacToeController restController;

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private BoardChecker boardChecker;

    @BeforeEach
    public void setUp() throws Exception {
        restController = new RestTicTacToeController(boardRepository, boardChecker);
    }

    private final String BOARD_NAME = "exists";
    private final Board BOARD = new SimpleBoard(List.of(3,3), 3);

    @Test
    public void get_existing_game() {
        when(boardRepository.getBoard(BOARD_NAME)).thenReturn(BOARD);

        BoardResponse actualResponse = restController.getBoard(BOARD_NAME);

        BoardResponse expectedResponse = new BoardResponse(BOARD.toString(), BOARD.getStreakToWin());
        assertEquals(expectedResponse.getJSONBoard(), actualResponse.getJSONBoard());
        assertEquals(expectedResponse.getStreakToWin(), actualResponse.getStreakToWin());
    }

    @Test
    public void get_nonexistent_game() {
        when(boardRepository.getBoard(BOARD_NAME)).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> restController.getBoard(BOARD_NAME));
    }
}
