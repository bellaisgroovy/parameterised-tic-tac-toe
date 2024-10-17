package game.data.board.factory;

import game.data.board.Board;
import game.data.board.SimpleBoard;

import java.util.List;

public class SimpleBoardFactory implements BoardFactory {

    @Override
    public Board createBoard(List<Integer> sizes, int streakToWin) {
        return new SimpleBoard(sizes, streakToWin);
    }
}
