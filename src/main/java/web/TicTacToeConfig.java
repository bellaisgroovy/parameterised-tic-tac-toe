package web;

import api.controller.GameController;
import api.controller.SimpleGameController;
import api.data.BoardRepository;
import api.data.FileBoardRepository;
import game.boardChecker.BoardChecker;
import game.boardChecker.SimpleBoardChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicTacToeConfig {
    @Bean
    public GameController gameController() {
        return new SimpleGameController(boardChecker(), boardRepository());
    }

    @Bean
    public BoardChecker boardChecker() {
        return new SimpleBoardChecker();
    }

    @Bean
    public BoardRepository boardRepository() {
        return new FileBoardRepository();
    }
}
