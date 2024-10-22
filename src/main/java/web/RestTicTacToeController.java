package web;

import api.controller.GameController;
import api.controller.SimpleGameController;
import api.data.BoardRepository;
import game.boardChecker.BoardChecker;
import game.data.board.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

/**
 * defines web endpoints for accessing tic tac toe
 */
@RestController
public class RestTicTacToeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final GameController gameController;

    @Autowired
    public RestTicTacToeController(BoardRepository boardRepository, BoardChecker boardChecker) {
        this.gameController = new SimpleGameController(boardChecker, boardRepository);
    }

    /**
     * returns the board specified
     * @param gameName
     * name of game to retrieve
     * @return stringified JSON array of the board and streakToWin on that board
     * @throws NoSuchElementException
     * if a specified board does not exist
     */
    @GetMapping("/board/{gameName}")
    public Board getBoard(@PathVariable String gameName) throws NoSuchElementException {
        return gameController.getBoard(gameName);
    }

    /**
     * returns 404 not found when element can't be found
     * @param e exception
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public void handleBoardDoesntExist(NoSuchElementException e) {
        logger.error("Exception is: ", e);
    }
}
