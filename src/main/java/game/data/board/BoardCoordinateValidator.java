package game.data.board;

import java.util.List;

public class BoardCoordinateValidator {
    public void validateIndices(List<Integer> indices, List<Integer> sizes, int noDimensions) {
        if (!indicesHaveCorrectNoDimensions(indices, noDimensions)) {
            throw new IllegalArgumentException(indices + "contains wrong number of indices");
        }
        if (!indicesAreInBounds(indices, sizes)) {
            throw new IndexOutOfBoundsException("one or more indices in" + indices + " are not in bounds");
        }
    }

    private boolean indicesHaveCorrectNoDimensions(List<Integer> indices, int noDimensions) {
        return indices.size() == noDimensions;
    }

    private boolean indicesAreInBounds(List<Integer> indices, List<Integer> sizes) {
        for (int i = 0; i < indices.size(); i++) {
            if (indices.get(i) >= sizes.get(i)) {
                return false;
            }
            if (indices.get(i) < 0) {
                return false;
            }
        }
        return true;
    }
}
