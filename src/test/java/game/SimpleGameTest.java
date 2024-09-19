package game;

import boardChecker.BoardChecker;
import boardChecker.SimpleBoardChecker;
import data.Board;
import data.GameState;
import data.SimpleBoard;
import org.junit.jupiter.api.Test;
import player.Player;
import player.SimplePlayer;
import player.display.Display;
import player.display.SimplePrinterDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleGameTest {
    SimpleGame createGame(List<Integer> boardSizes, List<String> inputs, int streakToWin) {
        Board board = new SimpleBoard(List.of(3,3));

        List<Player> players = new ArrayList<>();
        for (String input : inputs) {
            players.add(new SimplePlayer(new Scanner(input)));
        }

        BoardChecker boardChecker = new SimpleBoardChecker(streakToWin);

        Display display = new SimplePrinterDisplay();

        return new SimpleGame(board, players, boardChecker, display);
    }

    @Test
    public void test_player_1_wins() {
       String noughtsInputs = """
                0
                0
                0
                1
                0
                2""";
        String crossesInputs = """
                1
                0
                1
                1""";

       Game game = createGame(List.of(3,3), List.of(noughtsInputs, crossesInputs), 3);

        assertEquals(1, game.play());
    }

    @Test
    public void draw() {
        String noughtsInputs = """
                1
                1
                1
                0
                0
                2
                0
                0
                2
                1""";
        String crossesInputs = """
                0
                1
                1
                2
                2
                0
                2
                2""";

        Game game = createGame(List.of(3,3), List.of(noughtsInputs, crossesInputs), 3);

        assertEquals(GameState.DRAW.value, game.play());
    }
}
