package persistence;

import model.Game;
import model.GameGenre;
import model.PlayStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGame(String title, String dev, GameGenre genre, List<String> platform, int year,
                             PlayStatus playStatus, Game game) {
        assertEquals(title, game.getTitle());
        assertEquals(dev, game.getDeveloper());
        assertEquals(genre, game.getGenre());
        assertEquals(platform, game.getPlatform());
        assertEquals(year, game.getYear());
        assertEquals(playStatus, game.getPlayStatus());
    }
}
