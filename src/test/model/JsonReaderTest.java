package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static model.GameGenre.ADVENTURE;
import static model.GameGenre.RPG;
import static model.PlayStatus.COMPLETED;
import static model.PlayStatus.CURRENTLY_PLAYING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    // non existent file
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GameCatalogue gc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // no games in game catalogue
    @Test
    void testReaderEmptyGameCatalogue() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGameCatalogue.json");
        try {
            GameCatalogue gc = reader.read();
            assertEquals("Clarissa", gc.getUsername());
            assertEquals(0, gc.getAllGames().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // there are games in game catalogue
    @Test
    void testReaderGeneralGameCatalogue() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGameCatalogue.json");
        try {
            GameCatalogue gc = reader.read();
            assertEquals("Clarissa", gc.getUsername());
            List<Game> gamesList = gc.getAllGames();
            assertEquals(2, gamesList.size());
            checkGame("Marvel's Spider-Man", "Insomniac Games", RPG,
                    Arrays.asList("PlayStation 4"), 2018, COMPLETED, gamesList.get(0));
            checkGame("Spiritfarer", "Thunder Lotus Games", ADVENTURE,
                    Arrays.asList("Nintendo Switch", "macOS"), 2020, CURRENTLY_PLAYING, gamesList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}