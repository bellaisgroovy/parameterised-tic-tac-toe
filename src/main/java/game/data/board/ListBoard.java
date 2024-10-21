package game.data.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.data.BoardEquality;
import game.data.board.factory.BoardFactory;
import game.data.board.factory.ListBoardFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListBoard implements Board {
    public ListBoard(List<Integer> sizes, int streakToWin) {
        this.sizes = sizes;
        this.streakToWin = streakToWin;
        this.dimensions = sizes.size();
        objectMapper = new ObjectMapper();

        populateBoard();
    }

    @Override
    public int getNoDimensions() {
        return this.dimensions;
    }

    @Override
    public List<Integer> getSizes() {
        return this.sizes;
    }

    @Override
    public int getStreakToWin() {
        return streakToWin;
    }

    @Override
    public int getCellAt(List<Integer> indices) {
        validateIndices(indices);
        List<Object> boardSection = getBoardSectionAt(indices);
        return (Integer) boardSection.get(indices.getLast());
    }

    @Override
    public void setCellAt(List<Integer> indices, Integer value) {
        validateIndices(indices);
        List<Object> boardSection = getBoardSectionAt(indices);
        boardSection.set(indices.getLast(), value);
    }

    @Override
    public BoardFactory getBoardFactory() {
        return boardFactory;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Board)) return false;
        return boardEquality.equals(this, (Board) obj);
    }

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(board);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Object> board;

    private List<Object> getBoard() {
        return board;
    }

    private void setBoard(List<Object> board) {
        this.board = board;
    }

    private List<Object> createInnerSection(int size) {
        int EMPTY_CELL = 0;
        return new ArrayList<>(Collections.nCopies(size, EMPTY_CELL));
    }

    private List<Object> createBoard(List<Integer> sizes, int currentDimension, List<Object> board) {
        if (currentDimension != sizes.size()) {
            for (int size = 0; size < sizes.get(currentDimension-1); size++) {
                List<Object> section = createBoard(sizes, currentDimension + 1, new ArrayList<>());
                board.add(section);
            }
            return board;
        } else {
            return createInnerSection(sizes.getLast());
        }
    }


    private void populateBoard() {
        if (getNoDimensions() == 0) {return;}
        setBoard(createBoard(getSizes(), 1, new ArrayList<>()));
    }

    /**
     * returns board section of item at coordinate
     * @param coordinate
     * coordinates of cell
     * @return reference to board section of that cell.
     * <br>
     * eg in a 3x3 board, passing in [1,1] would get you the middle row
     */
    private List<Object> getBoardSectionAt(List<Integer> coordinate) {
        List<Object> boardSection = getBoard();
        for (int index = 0; index < coordinate.size() - 1; index++) {
            // only the innermost dimension holds cell values so cast will always be ok
            boardSection = (List<Object>) boardSection.get(coordinate.get(index));
        }
        return boardSection;
    }

    private void validateIndices(List<Integer> indices) {
        coordinateValidator.validateIndices(indices, getSizes(), getNoDimensions());
    }

    BoardCoordinateValidator coordinateValidator = new BoardCoordinateValidator();

    private final BoardEquality boardEquality = new BoardEquality();

    private final int streakToWin;

    private final int dimensions;

    private final List<Integer> sizes;

    private final ObjectMapper objectMapper;

    private final BoardFactory boardFactory = new ListBoardFactory();
}
