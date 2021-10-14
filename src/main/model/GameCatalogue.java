package model;

import java.util.LinkedList;
import java.util.List;

public class GameCatalogue {
    //  A game catalogue consists of the user's username (String), and a list of games the user has added (List<Game>)
    private String username;
    private List<Game> allGames;


    public GameCatalogue(String username) {
        this.username = username;
        allGames = new LinkedList<>();
    }

    // getter
    public String getUsername() {
        return username;
    }

    public List<Game> getAllGames() {
        return allGames;
    }

    public String getAllGameTitles() {
        if (allGames.isEmpty()) {
            return "There are no games in your game catalogue";
        }
        List<String> allGamesString = new LinkedList<>();
        for (Game g: allGames) {
            String title = g.getTitle();
            allGamesString.add(title);
        }
        return String.join(", ", allGamesString);
    }

    // EFFECTS: adds game to the game catalogue
    public void addGame(Game g) {
        allGames.add(g);
    }

    // EFFECTS: returns a specific given game's game details
    public String searchGame(String gameName) {
        for (Game g: allGames) {
            if (g.getTitle() == gameName) {
                return g.getGameDetails();
            }
        }
        return "Game with this title couldn't be found...";
    }

    // EFFECTS: checks the playing status of a specific given game
    public String checkPlayStatus(String gameName) {
        for (Game g: allGames) {
            if (g.getTitle() == gameName) {
                return g.stringPlayStatus();
            }
        }
        return "Game with this title couldn't be found...";
    }

    // EFFECT: returns list of game titles that have been marked as a favourite
    public String getFavourites() {
        List<String> favourites = new LinkedList<>();
        for (Game game: allGames) {
            if (game.isFavourite()) {
                String title = game.getTitle();
                favourites.add(title);
            }
        }
        return String.join(", ", favourites);
    }

    // EFFECT: returns list of game titles that have the given playing status
    public String assortByPlayStatus(String playStatus) {
        List<String> givenPlayStatus = new LinkedList<>();
        for (Game game: allGames) {
            if (game.stringPlayStatus().equals(playStatus)) {
                String title = game.getTitle();
                givenPlayStatus.add(title);
            }
        }
        return String.join(", ", givenPlayStatus);
    }

    // EFFECT: returns list of game titles that are of the given game genre
    public String assortByGenre(String genre) {
        List<String> givenGenre = new LinkedList<>();
        for (Game game: allGames) {
            if (game.stringGenre().equals(genre)) {
                String title = game.getTitle();
                givenGenre.add(title);
            }
        }
        return String.join(", ", givenGenre);
    }

}
