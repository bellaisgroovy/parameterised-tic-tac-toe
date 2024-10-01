package business.game;

import business.data.Board;

import java.util.List;

public interface GameService {
    Board getBoard();

    void playInCell(List<Integer> coordinate, int player);
}
