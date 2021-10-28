package persistence;

import model.Game;
import model.GameCatalogue;
import static model.GameGenre.*;
import static model.PlayStatus.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {

    // invalid file name
    @Test
    void testWriterInvalidFile() {
        try {
            GameCatalogue gc = new GameCatalogue("Clarissa");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // empty game catalogue
    @Test
    void testWriterEmptyGameCatalogue() {
        try {
            GameCatalogue gc = new GameCatalogue("Clarissa");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGameCatalogue.json");
            writer.open();
            writer.write(gc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGameCatalogue.json");
            gc = reader.read();
            assertEquals("Clarissa", gc.getUsername());
            assertEquals(0, gc.getAllGames().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // there are games in the game catalogue
    @Test
    void testWriterGeneralGameCatalogue() {
        try {
            GameCatalogue gc = new GameCatalogue("Clarissa");
            gc.addGame(new Game("Marvel's Spider-Man", "Insomniac Games", RPG,
                    new ArrayList<>(Arrays.asList("PlayStation 4")), 2018, COMPLETED));
            gc.addGame(new Game("Spiritfarer", "Thunder Lotus Games", ADVENTURE,
                    Arrays.asList("Nintendo Switch", "macOS"), 2020, CURRENTLY_PLAYING));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(gc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            gc = reader.read();
            assertEquals("Clarissa", gc.getUsername());
            List<Game> gamesList = gc.getAllGames();
            assertEquals(2, gamesList.size());
            checkGame("Marvel's Spider-Man", "Insomniac Games", RPG,
                    Arrays.asList("PlayStation 4"), 2018, COMPLETED, gamesList.get(0));
            checkGame("Spiritfarer", "Thunder Lotus Games", ADVENTURE,
                    Arrays.asList("Nintendo Switch", "macOS"), 2020, CURRENTLY_PLAYING, gamesList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}