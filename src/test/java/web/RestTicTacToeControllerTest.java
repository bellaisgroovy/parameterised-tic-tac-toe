package web;


import api.controller.GameController;
import api.controller.SimpleGameControllerTest;
import game.data.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RestTicTacToeControllerTest extends SimpleGameControllerTest {
    private RestTicTacToeController restController;

    @Mock
    private GameController gameController;

    @Override
    @BeforeEach
    public void setUp() {
        restController = new RestTicTacToeController(gameController);
    }

    @Override
    @Test
    public void get_existing_board() {
        when(gameController.getBoard(GAME_NAME)).thenReturn(BOARD);

        Board actualResponse = restController.getBoard(GAME_NAME);

        assertEquals(BOARD, actualResponse);
    }

    @Override
    @Test
    public void get_nonexistent_board() {
        when(gameController.getBoard(GAME_NAME)).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> restController.getBoard(GAME_NAME));
    }

    @Override
    @Test
    @Disabled
    public void play_in_valid_cell() {
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
