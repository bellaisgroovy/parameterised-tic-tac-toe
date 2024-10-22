package web;

import api.data.BoardRepository;
import api.data.FileBoardRepository;
import game.boardChecker.BoardChecker;
import game.boardChecker.SimpleBoardChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicTacToeConfig {
    @Bean
    public BoardChecker boardChecker() {
        return new SimpleBoardChecker();
    }

    @Bean
    public BoardRepository boardRepository() {
        return new FileBoardRepository();
    }
}
