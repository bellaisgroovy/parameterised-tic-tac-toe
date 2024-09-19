import game.Game;
import game.SimpleGame;
import game.gameFactory.GameFactory;
import game.gameFactory.SimpleGameFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static GameFactory gameFactory = new SimpleGameFactory();
    public static void main(String[] args) {
        List<Integer> boardSizes = Arrays.stream(args[0].split(",")).map(s -> Integer.parseInt(s.trim())).toList();
        Game game = gameFactory.createGame(boardSizes, Integer.parseInt(args[1]), Integer.parseInt(args[2]));

        System.out.println(game.play());
    }
}
