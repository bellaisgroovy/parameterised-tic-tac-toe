package player;

import data.Board;
import data.MatrixBoard;

import java.util.List;

public interface Player {
    public List<Integer> play(Board board);
}
