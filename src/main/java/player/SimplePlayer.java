package player;

import data.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimplePlayer implements Player {

    public SimplePlayer() {}

    public SimplePlayer(Scanner scanner) {
        setScanner(scanner);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    private Scanner scanner = new Scanner(System.in);

    @Override
    public List<Integer> play(Board board) {
        List<Integer> coordinate = List.of();
        boolean valid = false;

        while (!valid) {
            coordinate = new ArrayList<>();
            valid = true;
            for (int dimension = 0; dimension < board.getNoDimensions(); dimension++) {
                int max_index = board.getSizes().get(dimension)-1;
                coordinate.add(get_valid_input(max_index, dimension));
            }

            // if cell is not empty, return false
            if (!is_cell_empty(board, coordinate)) {
                valid = false;
            }
        }

        return coordinate;
    }

    private boolean is_cell_empty(Board board, List<Integer> coordinate) {
        return board.getCellAt(coordinate) == 0;
    }

    private int get_valid_input(int max, int dimension) {
        String input;
        boolean valid = false;
        int intInput = -1;

        while (!valid) {
            System.out.println("input coordinate in dimension " + dimension);
            input = scanner.nextLine();
            valid = true;

            try {
                intInput = Integer.parseInt(input);
            } catch(NumberFormatException e ) {
                valid = false;
            }

            int min = 0;
            // if less than 0 or greater than max
            if (min > intInput || intInput > max) {
                valid = false;
            }
        }

        return intInput;
    }

}
