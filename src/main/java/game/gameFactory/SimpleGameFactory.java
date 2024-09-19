package game.gameFactory;

import boardChecker.SimpleBoardChecker;
import data.SimpleBoard;
import game.Game;
import game.SimpleGame;
import player.Player;
import player.SimplePlayer;
import player.display.SimplePrinterDisplay;

import java.util.ArrayList;
import java.util.List;

public class SimpleGameFactory implements GameFactory{


    @Override
    public Game createGame(List<Integer> boardSizes, int streakToWin, int noPlayers) {
        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < noPlayers; i++) {
            players.add(new SimplePlayer());
        }

        return new SimpleGame(
                new SimpleBoard(boardSizes),
                players,
                new SimpleBoardChecker(streakToWin),
                new SimplePrinterDisplay()
        );
    }
}
