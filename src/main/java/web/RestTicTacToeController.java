package web;

import api.controller.GameController;
import game.data.board.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.data.BoardCreationRequest;
import web.data.MoveRequest;

import java.util.NoSuchElementException;

/**
 * defines web endpoints for accessing tic tac toe
 */
@RestController
public class RestTicTacToeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final GameController gameController;

    @Autowired
    public RestTicTacToeController(GameController gameController) {
        this.gameController = gameController;
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

    @PutMapping("/board/{gameName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void playInCell(@PathVariable String gameName, @RequestBody MoveRequest moveRequest) {
        gameController.playInCell(moveRequest.getCoordinate(), moveRequest.getPlayer(), gameName);
    }

    @GetMapping("/board/{gameName}/winner")
    public int getWinner(@PathVariable String gameName) throws NoSuchElementException {
       return gameController.getWinner(gameName);
    }

    @PostMapping("/board/{gameName}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Board> createBoard(@PathVariable String gameName, @RequestBody BoardCreationRequest boardCreationRequest) {
        gameController.createBoard(boardCreationRequest.getSizes(), boardCreationRequest.getStreakToWin(), gameName);

        return new ResponseEntity<>(gameController.getBoard(gameName), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleInvalidMoveRequest(IllegalArgumentException e) {
        logger.error("Exception is: ", e);
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
