package game.data.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardCoordinateIterator implements Iterator<List<Integer>> {
    public BoardCoordinateIterator(Board board) {
        initializeCoordinate(board.getSizes().size());
        sizes = board.getSizes();
        sizesAsIndex = board.getSizes().stream().map((size) -> size - 1).toList();
    }

    @Override
    public boolean hasNext() {
        return !coordinate.equals(sizesAsIndex);
    }

    @Override
    public List<Integer> next() {
        coordinate = getNextBoardCoordinate(sizes, coordinate);
        return coordinate;
    }

    private List<Integer> coordinate;
    private final List<Integer> sizes;
    private final List<Integer> sizesAsIndex;

    /**
     * Adds 1 to the rightmost item in list unless it is already at max in which case it rolls over one place left
     * <br>
     * like a number system where the base changes at each
     * @param sizes max value of each place
     * @param coordinate current value at each place
     * @return coordinate with 1 added to the rightmost place or rolled over.
     */
    private List<Integer> getNextBoardCoordinate(List<Integer> sizes, List<Integer> coordinate) {
        List<Integer> newCoordinate = new ArrayList<>(coordinate);
        int i = newCoordinate.size() -1;
        while (i >= 0) {
            if (newCoordinate.get(i) >= sizes.get(i) - 1) {
                newCoordinate.set(i, 0);
                i--;
            } else {
                newCoordinate.set(i, newCoordinate.get(i) + 1);
                break;
            }
        }
        return newCoordinate;
    }

    /**
     * sets coordinate to list of 0s of size dimensions. With -1 at end so next() returns 1st coordinate
     * @param dimensions number of coordinates to generate
     */
    private void initializeCoordinate(int dimensions) {
        coordinate = new ArrayList<>();
        for (int i = 0; i < dimensions; i++) {
            coordinate.add(0);
        }
        coordinate.set(coordinate.size() - 1, -1);
    }
}
