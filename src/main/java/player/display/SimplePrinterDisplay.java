package player.display;

import data.Board;

public class SimplePrinterDisplay implements Display{

    @Override
    public void display(Board board) {
        System.out.println(board.toString());
    }
}
