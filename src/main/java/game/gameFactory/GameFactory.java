package game.gameFactory;

import game.Game;

import java.util.List;

public interface GameFactory {
    Game createGame(List<Integer> boardSizes, int streakToWin, int noPlayers);
}
