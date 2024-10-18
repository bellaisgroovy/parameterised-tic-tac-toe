package game.boardChecker;

import game.data.board.Board;
import game.data.binary.adder.ListBoolBinaryAdder;
import game.data.binary.adder.SimpleListBoolBinaryAdder;
import game.data.GameState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleBoardChecker implements BoardChecker {
    ListBoolBinaryAdder binaryAdder;

    public SimpleBoardChecker() {
        this.binaryAdder = new SimpleListBoolBinaryAdder();
    }

    // returns int representing winner, or 0 for a draw, or -1 for game still continuing
    @Override
    public int winningPlayer(Board board) {
        // get every direction that needs checked
        List<List<Integer>> directions = getDirections(board);

        // check for matches in every direction from every element in board
        // initialise coordinate
        List<Integer> firstCoordinate = new ArrayList<>();
        for (int i = 0; i < board.getNoDimensions(); i++) {
            firstCoordinate.add(0);
        }
        List<Integer> coordinate = new ArrayList<>(firstCoordinate);

        boolean hasEmptyCell = false;
        int winner = GameState.ONGOING.value;
        do {
            int currentPlayer = board.getCellAt(coordinate);

            if (currentPlayer == 0) {
                hasEmptyCell = true;
            }

            if (cellIsWinning(board, coordinate, directions)) {
                winner = currentPlayer;
            } else {
                coordinate = getNextBoardCoordinate(board.getSizes(), coordinate);
            }
        } while (winner == GameState.ONGOING.value && !coordinate.equals(firstCoordinate));

        if (!hasEmptyCell && winner == GameState.ONGOING.value) {
            winner = GameState.DRAW.value;
        }

        return winner;
    }

    private boolean cellIsWinning(Board board, List<Integer> coordinate, List<List<Integer>> directions) {
        if (board.getCellAt(coordinate) != 0) {
            for (List<Integer> direction : directions) {
                if (isCellWinningInDirection(board, coordinate, direction)) {
                    return true;
                }
            }
        }
        return false;
    }

    // adds 1 to the rightmost item in list unless it is already at max in which case it rolls over one place left
    // like a number system where the base changes at each
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

    private boolean isCellWinningInDirection(Board board, List<Integer> coordinate, List<Integer> direction) {
        int streak = 1;
        boolean isStreak = true;
        int player = board.getCellAt(coordinate);

        List<Integer> nextCoordinate = new ArrayList<>(addIntLists(coordinate, direction));

        while (coordinateInBoard(board, nextCoordinate) && isStreak) {
            if (player == board.getCellAt(nextCoordinate)) {
                streak++;
            } else {
                isStreak = false;
            }

            nextCoordinate = new ArrayList<>(addIntLists(nextCoordinate, direction));
        }

        return streak >= board.getStreakToWin();
    }

    private List<Integer> addIntLists(List<Integer> a, List<Integer> b) {
        List<Integer> res = new ArrayList<>(List.copyOf(a));
        for (int i = 0; i < b.size(); i++) {
            res.set(i, res.get(i) + b.get(i));
        }
        return res;
    }

    private boolean coordinateInBoard(Board board, List<Integer> coordinate) {
        try {
            board.getCellAt(coordinate);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private List<List<Integer>> getDirections(Board board) {
        //populate directions
        List<List<Boolean>> directionsBool = getAllBinaryCombinationsForNBits(board.getNoDimensions());
        directionsBool.removeFirst(); // the first item is 0 in all components, which isn't a direction
        List<List<Integer>> directions = new ArrayList<>();
        directionsBool.forEach(direction -> directions.add(boolToInt(direction)));

        List<List<Integer>> diagonals = new ArrayList<>();
        for (List<Integer> direction : directions) {
            // if there is more than one positive direction component, get diagonals
            List<Integer> positiveComponentIndices = getPositiveIndices(direction);
            if (positiveComponentIndices.size() > 1) {
                diagonals.addAll(getDiagonals(direction, positiveComponentIndices));
            }
        }
        directions.addAll(diagonals);
        return directions;
    }

    private List<List<Integer>> getDiagonals(List<Integer> direction, List<Integer> positiveComponentIndices) {
        List<List<Integer>> diagonals = new ArrayList<>();

        List<List<Boolean>> combinations = getAllBinaryCombinationsForNBits(positiveComponentIndices.size());

        // remove opposite combinations bc match in +x is same as match in -x
        // e.g. if [1, -1] is already in the list, don't add the opposite [-1, 1]
        List<List<Boolean>> shavedCombinations = new ArrayList<>();
        for (List<Boolean> combination : combinations) {
            if (!shavedCombinations.contains(flipList(combination))) {
                shavedCombinations.add(combination);
            }
        }

        // where combination index is true, set component at index of same index in positiveComponentIndices to -1
        // for each combination
        for (List<Boolean> combination : shavedCombinations) {
            List<Integer> diagonal = new ArrayList<>(List.copyOf(direction));
            for (int combinationIndex = 0; combinationIndex < combination.size(); combinationIndex++) {
                if (combination.get(combinationIndex)) {
                    diagonal.set(positiveComponentIndices.get(combinationIndex), -1);
                }
            }
            diagonals.add(diagonal);
        }

        // since the first diagonal is the direction passed in, its already present in the list being added to, so remove it
        diagonals.removeFirst();

        return diagonals;
    }

    private List<Boolean> flipList(List<Boolean> boolList) {
        List<Boolean> flippedList = new ArrayList<>();
        boolList.forEach(item -> flippedList.add(!item));
        return flippedList;
    }

    private List<List<Boolean>> getAllBinaryCombinationsForNBits(int nBits) {
        // add first combination which is false in all indices
        List<List<Boolean>> combinations = new ArrayList<>();
        combinations.add(getBinary0WithNoBits(nBits));

        //initialize combination
        List<Boolean> combination = binaryAdder.addOne(combinations.getFirst());

        //create all combinations
        while (!combination.equals(combinations.getFirst())) {
            combinations.add(combination);
            combination = binaryAdder.addOne(combination);
        }
        return combinations;
    }

    private List<Integer> boolToInt(List<Boolean> list) {
        List<Integer> result = new ArrayList<>();
        for (Boolean b : list) {
            if (b) {result.add(1);}
            else {result.add(0);}
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

    private List<Boolean> getBinary0WithNoBits(int noBits) {
        List<Boolean> zero = new ArrayList<>();
        for (int i = 0; i < noBits; i++) {
            zero.add(false);
        }
        return zero;
    }
}
