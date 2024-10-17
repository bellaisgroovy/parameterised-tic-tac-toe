package game.data.board.factory;

import game.data.board.Board;

import java.util.List;

public interface BoardFactory {
    public Board createBoard(List<Integer> sizes, int streakToWin);
}
