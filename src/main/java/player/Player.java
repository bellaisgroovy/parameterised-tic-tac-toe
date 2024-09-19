package player;

import data.Board;

import java.util.List;

public interface Player {
    List<Integer> play(Board board);
}
