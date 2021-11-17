package persistence;

import model.Game;
import model.GameCatalogue;

import model.GameGenre;
import model.PlayStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game catalogue from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public GameCatalogue read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameCatalogue(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses game catalogue from JSON object and returns it
    private GameCatalogue parseGameCatalogue(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        GameCatalogue gc = new GameCatalogue(username);
        addGameFiles(gc, jsonObject);
        return gc;
    }

    // MODIFIES: gc
    // EFFECTS: parses games from JSON object and adds them to game catalogue
    private void addGameFiles(GameCatalogue gc, JSONObject jsonObject) {
        JSONArray gamesList = jsonObject.getJSONArray("games");
        for (Object game : gamesList) {
            JSONObject nextGame = (JSONObject) game;
            addGameFile(gc, nextGame);
        }
    }

    // MODIFIES: gc
    // EFFECTS: parses game from JSON object and adds it to game catalogue
    private void addGameFile(GameCatalogue gc, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String developer = jsonObject.getString("developer");
        GameGenre genre = GameGenre.valueOf(jsonObject.getString("genre"));
        List<String> platform = Arrays.asList(jsonObject.getString("platform").split(", "));
        int year = jsonObject.getInt("year");
        PlayStatus playstatus = PlayStatus.valueOf(jsonObject.getString("play status"));
        Game g = new Game(title, developer, genre, platform, year, playstatus);
        gc.addGame(g);
    }
}
