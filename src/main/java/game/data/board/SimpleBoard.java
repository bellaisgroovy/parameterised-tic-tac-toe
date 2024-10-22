package game.data.board;

import game.data.BoardEquality;
import game.data.board.factory.BoardFactory;
import game.data.board.factory.ListBoardFactory;
import game.data.board.factory.SimpleBoardFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleBoard implements Board {
    public int getNoDimensions() {
        return sizes.size();
    }

    public List<Integer> getSizes() {
        return sizes;
    }

    public List<Integer> getFlatBoard() {
        return flatBoard;
    }

    private void setFlatBoard(List<Integer> flatBoard) {
        this.flatBoard = flatBoard;
    }

    public int getStreakToWin() {
        return streakToWin;
    }

    @Override
    public List<Object> getBoard() {
        Board listBoard = new ListBoardFactory().createBoard(getSizes(), getStreakToWin());
        Iterator<List<Integer>> listBoardIterator = listBoard.iterator();
        Iterator<List<Integer>> thisIterator = iterator();

        while (listBoardIterator.hasNext()) {
            listBoard.setCellAt(listBoardIterator.next(), getCellAt(thisIterator.next()));
        }

        return listBoard.getBoard();
    }

    public int getCellAt(List<Integer> indices) {
        validateIndices(indices);
        int flatIndex = getFlatIndex(indices);
        return getFlatBoard().get(flatIndex);
    }

    public void setCellAt(List<Integer> indices, Integer value) {
        validateIndices(indices);
        int flatIndex = getFlatIndex(indices);
        getFlatBoard().set(flatIndex, value);
    }

    public SimpleBoard() {};

    public SimpleBoard(List<Integer> sizes, int streakToWin) {
        this.sizes = sizes;
        populateFlatBoard();
        this.streakToWin = streakToWin;
    }

    public SimpleBoard(List<Integer> sizes, int streakToWin, List<Integer> flatBoard) {
        this.sizes = sizes;
        setFlatBoard(flatBoard);
        this.streakToWin = streakToWin;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Board)) return false;
        return boardEquality.equals(this, (Board) obj);
    }

    @Override
    public String toString() {
        List<String> stringFlatBoard = new ArrayList<>(getFlatBoard().size());
        for (int cell: getFlatBoard()) {
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

        return stringFlatBoard.toString().replaceAll(" ", "");
    }

    private final BoardCoordinateValidator coordinateValidator = new BoardCoordinateValidator();

    private final BoardEquality boardEquality = new BoardEquality();

    private List<Integer> sizes;

    private int streakToWin;

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
        coordinateValidator.validateIndices(indices, getSizes(), getNoDimensions());
    }

    //populate flatBoard with 0s
    private void populateFlatBoard() {
        if (getNoDimensions() == 0) {return;}

        List<Integer> flatBoardCopy = new ArrayList<>();

        int totalNoValues = sizes.getFirst();
        for (int i = 1; i < getNoDimensions(); i++) {
            totalNoValues = totalNoValues * this.sizes.get(i);
        }

        for (int i = 0; i < totalNoValues; i++) {
            flatBoardCopy.add(0);
        }
        setFlatBoard(flatBoardCopy);
    }
}
