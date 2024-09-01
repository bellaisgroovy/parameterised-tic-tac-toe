package data;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int getNoDimensions() {
        return dimensions;
    }

    public List<Integer> getSizes() {
        return sizes;
    }

    public int get(List<Integer> indices) {
        int flatIndex = getFlatIndex(indices);
        return flatBoard.get(flatIndex);
    }

    public void set(List<Integer> indices, Integer value) {
        int flatIndex = getFlatIndex(indices);
        flatBoard.set(flatIndex, value);
    }

    public Board(List<Integer> sizes) {
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

    private boolean indicesInBounds(List<Integer> indices) {
        for (int i = 0; i < indices.size(); i++) {
            // if index is less than 0 or greater than max size
            if (0 > indices.get(i) || indices.get(i) > sizes.get(i)-1) {
                return false;
            }
        }
        return true;
    }

    private int getFlatIndex(List<Integer> indices) {
        if (!indicesInBounds(indices)) {throw new IndexOutOfBoundsException();}

        int flatIndex = 0;
        int power = 0;

        for (int i = sizes.size() - 1; i >= 0; i--) {
            flatIndex = flatIndex + (indices.get(i) << power);
        }

        return flatIndex;
    }

    //populate flatBoard with 0s
    private void populateFlatBoard() {
        this.flatBoard = new ArrayList<>();
        int maxFlatIndex = sizes.getFirst();
        for (int i = 1; i < this.dimensions; i++) {
            maxFlatIndex = maxFlatIndex * this.sizes.get(i);
        }
        for (int i = 0; i < maxFlatIndex; i++) {
            flatBoard.add(0);
        }
    }
}
