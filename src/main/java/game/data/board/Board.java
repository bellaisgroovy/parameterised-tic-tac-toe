package game.data.board;

import game.data.board.factory.BoardFactory;

import java.util.List;

/**
 * Manages an n dimensional List with regular lengths. All lists in one dimension must have the same length.
 */
public interface Board{
    int getNoDimensions();

    List<Integer> getSizes();

    List<Integer> getFlatBoard();

    int getStreakToWin();

    int getCellAt(List<Integer> indices);

    void setCellAt(List<Integer> indices, Integer value);

    String toString();

    BoardFactory getBoardFactory();

    @Override
    boolean equals(Object obj);
}