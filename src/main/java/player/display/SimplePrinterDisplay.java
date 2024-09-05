package player.display;

import data.MatrixBoard;

public class SimplePrinterDisplay implements Display{

    @Override
    public void display(MatrixBoard board) {
        System.out.println(board.toString());
    }
}
