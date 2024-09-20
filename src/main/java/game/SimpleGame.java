package game;

import boardChecker.BoardChecker;
import data.Board;
import data.GameState;
import player.Player;
import player.display.Display;

import java.util.List;

public class SimpleGame implements Game{
    Board board;
    List<Player> players;
    BoardChecker boardChecker;
    Display display;

    public SimpleGame(Board board, List<Player> players, BoardChecker boardChecker, Display display) {
        this.board = board;
        this.players = players;
        this.boardChecker = boardChecker;
        this.display = display;
    }

    @Override
    public int play() {
        int winner = GameState.ONGOING.value;
        int playerIndex = 0;

        while (winner == GameState.ONGOING.value) {
            display.display(board);

            // do a turn
            List<Integer> coordinate = players.get(playerIndex).play(board);
            board.setCellAt(coordinate, playerIndex+1);

            // check for a winner
            winner = boardChecker.winningPlayer(board);

            // increment loop
            playerIndex = (playerIndex + 1) % players.size();
        }

        return winner;
    }
}
