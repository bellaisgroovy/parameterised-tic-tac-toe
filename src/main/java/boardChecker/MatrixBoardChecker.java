package boardChecker;

import data.Board;
import data.ListBoolBinaryAdder;
import data.SimpleListBoolBinaryAdder;

import java.util.ArrayList;
import java.util.List;

public class MatrixBoardChecker implements BoardChecker {

    int sizeRowToWin;
    ListBoolBinaryAdder binaryAdder;

    private int getSizeRowToWin() { return sizeRowToWin; }

    public MatrixBoardChecker(int sizeRowToWin) {
        this.sizeRowToWin = sizeRowToWin;
        this.binaryAdder = new SimpleListBoolBinaryAdder();
    }

    @Override
    public int winningPlayer(Board board) {
        // get every direction that needs checked
        List<List<Integer>> directions = getDirections(board);

        // check for matches in every direction from every element in board
        for (int i = 0; i < board.getSizes().size(); i++) {

        }
        return 0;
    }

    private List<List<Integer>> getDirections(Board board) {
        List<List<Boolean>> directionsBool = getAllBinaryCombinationsForNBits(board.getNoDimensions());
        List<List<Integer>> directions = new ArrayList<>();
        directionsBool.forEach(direction -> {directions.add(boolToInt(direction));});

        for (List<Integer> direction : directions) {
            // if there is more than one positive direction component, get diagonals
            List<Integer> positiveComponentIndices = getPositiveIndices(direction);
            if (positiveComponentIndices.size() > 1) {
                List<List<Integer>> diagonals = getDiagonals(direction, positiveComponentIndices);
                directions.addAll(diagonals);
            }
        }
        return directions;
    }

    private List<List<Integer>> getDiagonals(List<Integer> direction, List<Integer> positiveComponentIndices) {
        List<List<Integer>> diagonals = new ArrayList<>();

        List<List<Boolean>> combinations = getAllBinaryCombinationsForNBits(positiveComponentIndices.size());

        // remove opposite combinations bc match in +x is same as match in -x
        // e.g. if [1, -1] is already in the list, don't add the opposite [-1, 1]
        List<List<Boolean>> shavedCombinations = new ArrayList<>();
        for (List<Boolean> combination : combinations) {
            if (!shavedCombinations.contains(combination)) {
                shavedCombinations.add(combination);
            }
        }

        // where combination index is true, set component at index of same index in positiveComponentIndices to -1
        for (int combinationIndex = 0; combinationIndex < shavedCombinations.size(); combinationIndex++) {
            List<Integer> diagonal = new ArrayList<>(List.copyOf(direction));
            for (int componentIndex = 0; componentIndex < shavedCombinations.get(combinationIndex).size(); componentIndex++) {
                diagonal.set(positiveComponentIndices.get(componentIndex), -1);
            }
            diagonals.add(diagonal);
        }

        // since the first diagonal is the direction passed in, its already present in the list being added to, so remove it
        diagonals.removeFirst();

        return diagonals;
    }

    private List<Boolean> flipList(List<Boolean> boolList) {
        List<Boolean> flippedList = new ArrayList<>();
        boolList.forEach(item -> {flippedList.add(!item);});
        return flippedList;
    }

    private List<List<Boolean>> getAllBinaryCombinationsForNBits(int nBits) {
        // add first combination which is false in all indices
        List<List<Boolean>> combinations = new ArrayList<>();
        combinations.add(getBinary0WithNoBits(nBits));

        //initialize combination
        List<Boolean> combination = binaryAdder.addOne(combinations.getFirst());

        //create all combinations
        while (combination != combinations.getFirst()) {
            combinations.add(combination);
            combination = binaryAdder.addOne(combination);
        }
        return combinations;
    }

    private List<Integer> boolToInt(List<Boolean> list) {
        List<Integer> result = new ArrayList<>();
        for (Boolean b : list) {
            if (b) {result.add(1);}
            else {result.add(0);};
        }
        return result;
    }

    private List<Boolean> intToBool(List<Integer> list) {
        List<Boolean> result = new ArrayList<>();
        for (Integer b : list) {
            if (b == 1) {result.add(true);}
            else {result.add(false);};
        }
        return result;
    }

    private List<Integer> getPositiveIndices(List<Integer> direction) {
        List<Integer> positiveIndices = new ArrayList<>();
        for (int i = 0; i < direction.size(); i++) {
            if (direction.get(i) > 0) {
                positiveIndices.add(i);
            }
        }
        return positiveIndices;
    }

    private List<Integer> getFirstDirection(Board board) {
        List<Boolean> direction = new ArrayList<>();
        for (int i = 0; i < board.getNoDimensions(); i++) {
            direction.add(false);
        }
        return boolToInt(direction);
    }

    private List<Boolean> getBinary0WithNoBits(int noBits) {
        List<Boolean> zero = new ArrayList<>();
        for (int i = 0; i < noBits; i++) {
            zero.add(false);
        }
        return zero;
    }
}
