package model;

import java.util.ArrayList;
import java.util.List;

//  A game catalogue consists of the user's username (String), and a list of games the user has added (List<Game>).
public class GameCatalogue {
    private String username;
    private List<Game> allGames;


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
        return allGames;
    }

    // MODIFIES: this
    // EFFECTS: adds a game to the game catalogue
    public void addGame(Game g) {
        allGames.add(g);
    }

    // EFFECTS: returns a single string of all game titles,
    //          if games list is empty, says there are no games in game catalogue
    public String getAllGameTitles() {
        if (allGames.isEmpty()) {
            return "There are no games in your game catalogue.";
        }
        List<String> allGamesString = new ArrayList<>();
        for (Game g: allGames) {
            String title = g.getTitle();
            allGamesString.add(title);
        }
        return String.join(", ", allGamesString);
    }

    // EFFECTS: searches games list for game with the same title,
    //          if found, returns string of the game's details,
    //          if not found, returns string saying game title is not found
    public String searchGameDetails(String gameName) {
        for (Game g: allGames) {
            if (g.getTitle().equals(gameName)) {
                return g.getGameDetails();
            }
        }
        return "Game with this title couldn't be found...";
    }

    // REQUIRES: given playing status is in lowercase, and is one of: "completed", "currently playing", "on hold" or
    //           "plan to play"
    // EFFECT: returns a single string of all game titles that have the given playing status,
    //         if there are no games with the play status, returns string notifying this
    public String assortByPlayStatus(String playStatus) {
        List<String> givenPlayStatus = new ArrayList<>();
        for (Game game: allGames) {
            if (game.stringPlayStatus().toLowerCase().equals(playStatus)) {
                String title = game.getTitle();
                givenPlayStatus.add(title);
            }
        }
        if (givenPlayStatus.isEmpty()) {
            return "You have no games with this play status.";
        }
        return String.join(", ", givenPlayStatus);
    }

    // REQUIRES: given genre is in lowercase, and is one of: "fps", "rpg", "adventure", "puzzle", "horror",
    //           "simulation", "platformer"
    // EFFECT: returns a single string of all game titles that are of the given game genre
    //         if there are no games with the genre, returns string notifying this
    public String assortByGenre(String genre) {
        List<String> givenGenre = new ArrayList<>();
        for (Game game: allGames) {
            if (game.stringGenre().toLowerCase().equals(genre)) {
                String title = game.getTitle();
                givenGenre.add(title);
            }
        }
        if (givenGenre.isEmpty()) {
            return "You have no games in this genre.";
        }
        return String.join(", ", givenGenre);
    }

}
