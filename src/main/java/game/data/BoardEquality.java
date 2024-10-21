package game.data;

import game.data.board.Board;

import java.util.Iterator;
import java.util.List;

public class BoardEquality {
    public boolean equals(Board board, Board board2) {
        if (board.getStreakToWin() != board2.getStreakToWin()) return false;
        if (!board.getSizes().equals(board2.getSizes())) return false;

        Iterator<List<Integer>> thisIterator = board.iterator();
        Iterator<List<Integer>> thatIterator = board2.iterator();

        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            int thisCell = board.getCellAt(thisIterator.next());
            int thatCell = board2.getCellAt(thatIterator.next());

            if (thisCell != thatCell) return false;
        }

        return thisIterator.hasNext() == thatIterator.hasNext();
    }
}
