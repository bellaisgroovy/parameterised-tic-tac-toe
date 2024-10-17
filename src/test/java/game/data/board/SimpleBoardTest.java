package game.data.board;


import game.data.board.factory.SimpleBoardFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleBoardTest extends BoardTest {
    @Override
    @BeforeEach
    public void setUp() {
        setBoardFactory(new SimpleBoardFactory());
    }
}
