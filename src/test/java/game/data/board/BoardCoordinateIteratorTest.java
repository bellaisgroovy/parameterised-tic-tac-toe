package game.data.board;

import game.data.board.factory.BoardFactory;
import game.data.board.factory.ListBoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardCoordinateIteratorTest {
    private BoardFactory boardFactory;

    protected BoardFactory getBoardFactory() {
        return boardFactory;
    }

    protected void setBoardFactory(BoardFactory boardFactory) {
        this.boardFactory = boardFactory;
    }

    @BeforeEach
    public void setUp() {
        setBoardFactory(new ListBoardFactory());
    }

    @Test
    public void loop() {
        Board board = getBoardFactory().createBoard(List.of(3,3), 3);
        Iterator<List<Integer>> iterator = board.iterator();

        List<List<Integer>> expectedCoordinateList = List.of(
                List.of(0,0),
                List.of(0,1),
                List.of(0,2),
                List.of(1,0),
                List.of(1,1),
                List.of(1,2),
                List.of(2,0),
                List.of(2,1),
                List.of(2,2)
        );
        int index = 0;
        while (iterator.hasNext()) {
            List<Integer> actualCoordinate = iterator.next();
            System.out.println(actualCoordinate);
            assertEquals(expectedCoordinateList.get(index), actualCoordinate);
            index++;
        }
        assertEquals(index, expectedCoordinateList.size());
    }
}
