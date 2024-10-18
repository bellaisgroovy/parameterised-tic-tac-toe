package game.data.board.factory;

import game.data.board.Board;
import game.data.board.ListBoard;

import java.util.List;

public class ListBoardFactory implements BoardFactory{

    @Override
    public Board createBoard(List<Integer> sizes, int streakToWin) {
        return new ListBoard(sizes, streakToWin);
    }
}
