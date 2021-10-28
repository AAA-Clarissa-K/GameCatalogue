package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//  A game catalogue consists of the user's username (String), and a list of games the user has added (List<Game>).
public class GameCatalogue implements Writable {
    private String username;
    private List<Game> allGames;

    // constructs a game catalogue
    public GameCatalogue(String username) {
        this.username = username;
        allGames = new ArrayList<>();
    }

    // getter
    public String getUsername() {
        return username;
    }

    // EFFECTS: returns the game with the given string title, otherwise returns null
    public Game getGame(String name) {
        for (Game g: allGames) {
            if (g.getTitle().equals(name)) {
                return g;
            }
        }
        return null;
    }

    // EFFECTS: returns list (of game) of all the games in the catalogue
    public List<Game> getAllGames() {
        return allGames;            // NOTE: needed mostly for test of addGame
    }

    // MODIFIES: this
    // EFFECTS: adds a game to the game catalogue
    public void addGame(Game g) {
        allGames.add(g);
    }

    // REQUIRES: game exists in the database
    // MODIFIES: this
    // EFFECTS: removes a game to the game catalogue
    public void removeGame(Game g) {
        allGames.remove(g);
    }

    // EFFECTS: returns a single string of all game titles,
    //          when games list is empty, returns empty string
    public String getAllGameTitles() {
        List<String> allGamesString = new ArrayList<>();
        for (Game g: allGames) {
            String title = g.getTitle();
            allGamesString.add(title);
        }
        return String.join(", ", allGamesString);
    }

    // REQUIRES: given playing status is in lowercase, and is one of: "completed", "currently playing", "on hold" or
    //           "plan to play"
    // EFFECT: returns a single string of all game titles that have the given playing status,
    //         when there are no games with the play status, returns empty string
    public String assortByPlayStatus(String playStatus) {
        List<String> givenPlayStatus = new ArrayList<>();
        for (Game game: allGames) {
            if (game.stringPlayStatus().toLowerCase().equals(playStatus)) {
                String title = game.getTitle();
                givenPlayStatus.add(title);
            }
        }
        return String.join(", ", givenPlayStatus);
    }

    // REQUIRES: given genre is in lowercase, and is one of: "fps", "rpg", "adventure", "puzzle", "horror",
    //           "simulation", "platformer"
    // EFFECT: returns a single string of all game titles that are of the given game genre
    //         when there are no games with the genre, returns empty string
    public String assortByGenre(String genre) {
        List<String> givenGenre = new ArrayList<>();
        for (Game game: allGames) {
            if (game.stringGenre().toLowerCase().equals(genre)) {
                String title = game.getTitle();
                givenGenre.add(title);
            }
        }
        return String.join(", ", givenGenre);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("games", gamesToJson());
        return json;
    }

    // EFFECTS: returns games in this game catalogue as a JSON array
    private JSONArray gamesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Game g : allGames) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }
}
