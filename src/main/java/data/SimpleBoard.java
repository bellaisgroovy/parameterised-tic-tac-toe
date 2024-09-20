package data;

import java.util.ArrayList;
import java.util.List;

public class SimpleBoard implements Board{
    public int getNoDimensions() {
        return dimensions;
    }

    public List<Integer> getSizes() {
        return sizes;
    }

    public int getCellAt(List<Integer> indices) {
        validateIndices(indices);
        int flatIndex = getFlatIndex(indices);
        return flatBoard.get(flatIndex);
    }

    public void setCellAt(List<Integer> indices, Integer value) {
        validateIndices(indices);
        int flatIndex = getFlatIndex(indices);
        flatBoard.set(flatIndex, value);
    }

    public SimpleBoard(List<Integer> sizes) {
        this.dimensions = sizes.size();
        this.sizes = sizes;
        populateFlatBoard();
    }

    @Override
    public String toString() {
        List<String> stringFlatBoard = new ArrayList<>(flatBoard.size());
        for (int cell: flatBoard) {
            stringFlatBoard.add(String.valueOf(cell));
        }

        int subArraySize = stringFlatBoard.size();
        for (int sizeIndex = 0; sizeIndex<sizes.size()-1; sizeIndex++) {
            subArraySize = subArraySize/sizes.get(sizeIndex);
            for (int flatIndex = 0; flatIndex < stringFlatBoard.size(); flatIndex++) {
                if (flatIndex % subArraySize == 0 ) {
                    stringFlatBoard.set(flatIndex, "["+stringFlatBoard.get(flatIndex));
                }
                if (flatIndex % subArraySize == subArraySize-1) {
                    stringFlatBoard.set(flatIndex, stringFlatBoard.get(flatIndex)+"]");
                }
            }
        }
        return stringFlatBoard.toString();
    }

    private final int dimensions;
    private final List<Integer> sizes;

    private List<Integer> flatBoard; // formatted like python literal array syntax without []

    private int getFlatIndex(List<Integer> indices) {

        // for each index in indices, multiply it by the size one index in the current dimension takes up in flatBoard
        int flatIndex = 0;
        for (int indicesIndex = 0; indicesIndex < indices.size(); indicesIndex++) {
            int subFlatIndex = indices.get(indicesIndex);
            int dimensionsNavigated = indicesIndex+1;
            for (int sizeIndex = dimensionsNavigated; sizeIndex < sizes.size(); sizeIndex++) {
                subFlatIndex = subFlatIndex * getSizes().get(sizeIndex);
            }
            flatIndex += subFlatIndex;
        }
        return flatIndex;
    }

    private void validateIndices(List<Integer> indices) {
        if (!indicesHaveCorrectNoDimensions(indices)) {
            throw new IllegalArgumentException(indices + "contains wrong number of indices");
        }
        if (!indicesAreInBounds(indices)) {
            throw new IndexOutOfBoundsException("one or more indices in" + indices + " are not in bounds");
        }
    }

    private boolean indicesHaveCorrectNoDimensions(List<Integer> indices) {
        return indices.size() == getNoDimensions();
    }

    private boolean indicesAreInBounds(List<Integer> indices) {
        for (int i = 0; i < indices.size(); i++) {
            if (indices.get(i) >= getSizes().get(i)) {
                return false;
            }
            if (indices.get(i) < 0) {
                return false;
            }
        }
        return true;
    }

    //populate flatBoard with 0s
    private void populateFlatBoard() {
        this.flatBoard = new ArrayList<>();

        int totalNoValues = sizes.getFirst();
        for (int i = 1; i < this.dimensions; i++) {
            totalNoValues = totalNoValues * this.sizes.get(i);
        }

        for (int i = 0; i < totalNoValues; i++) {
            flatBoard.add(0);
        }
    }
}
