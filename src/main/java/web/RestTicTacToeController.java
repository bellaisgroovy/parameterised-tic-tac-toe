package web;

import api.controller.GameController;
import game.data.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.data.BoardResponse;

import java.util.NoSuchElementException;

@RestController
public class RestTicTacToeController {

    private GameController gameController;

    @Autowired
    public RestTicTacToeController(GameController gameController) {
        this.gameController = gameController;
    }

    @GetMapping("/board/{gameName}")
    public BoardResponse getBoard(@PathVariable String gameName) throws NoSuchElementException {
        Board board = gameController.getBoard(gameName);
        return new BoardResponse(board.toString(), board.getStreakToWin());
    }
}
