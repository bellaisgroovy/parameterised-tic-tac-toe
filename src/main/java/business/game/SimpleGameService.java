package business.game;

import business.data.Board;

import java.util.List;

public class SimpleGameService implements GameService {
    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void playInCell(List<Integer> coordinate, int player) {

    }

    private Board board;
}
