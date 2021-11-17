package ui;

import model.GameCatalogue;
import model.GameGenre;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static javafx.application.Platform.exit;
import static model.GameGenre.*;

// Represents the console interface of a game sorting application
public class GameSortingApp {
    protected static final String GAME_STORE = "./data/myGameCatalogue.json";
    protected GameCatalogue gameCatalogue;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private GameCatalogueUI catalogueUI;

    // EFFECTS: runs the game sorting application
    public GameSortingApp(GameCatalogueUI catalogueUI) {
        runGameSortingApp(catalogueUI);
    }

    // EFFECTS: processes user input
    private void runGameSortingApp(GameCatalogueUI catalogueUI) {

        init(catalogueUI);

        catalogueUI.startTitleScreen();
    }

    // MODIFIES: this
    // EFFECTS: initializes JsonWriter and JsonReader
    private void init(GameCatalogueUI catalogueUI) {
        this.catalogueUI = catalogueUI;
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(GAME_STORE);
        jsonReader = new JsonReader(GAME_STORE);
        input.useDelimiter("\n");
    }

    // EFFECTS: returns game details if there is a game with the matching string title,
    //          if getting game produces null, says game title can't be found
    protected void searchGameDetails(String targetGame) {
        if (gameCatalogue.getGame(targetGame) == null) {
            catalogueUI.message("Game with this title couldn't be found...", "No Game Found",
                    "plain");
        } else {
            catalogueUI.createGameDetailPage(gameCatalogue.getGame(targetGame));
        }
    }

    // EFFECTS: returns GameGenre to corresponding string input
    private GameGenre askGenre() {
        System.out.println("\nWhat is the game genre?");
        String genreInput = returnGenre();

        if (genreInput.equals("fps")) {
            return FPS;
        } else if (genreInput.equals("rpg")) {
            return RPG;
        } else if (genreInput.equals("adventure")) {
            return ADVENTURE;
        } else if (genreInput.equals("puzzle")) {
            return PUZZLE;
        } else if (genreInput.equals("horror")) {
            return HORROR;
        } else if (genreInput.equals("simulation")) {
            return SIMULATION;
        } else {
            return PLATFORMER;
        }
    }

    // EFFECTS: returns given genre string if valid, if invalid, asks for another genre attempt
    private String returnGenre() {
        System.out.println("- FPS");
        System.out.println("- RPG");
        System.out.println("- Adventure");
        System.out.println("- Puzzle");
        System.out.println("- Horror");
        System.out.println("- Simulation");
        System.out.println("- Platformer");
        String givenGenre = input.next().toLowerCase();
        if (!(givenGenre.equals("fps") || givenGenre.equals("rpg") || givenGenre.equals("adventure")
                || givenGenre.equals("puzzle") || givenGenre.equals("horror") || givenGenre.equals("simulation")
                || givenGenre.equals("platformer"))) {
            System.out.println("Invalid game genre, please try an available genre below.");
            askGenre();
        }

        return givenGenre;
    }

    // EFFECTS: returns given play status string if valid, if invalid, asks for another play status attempt
    private String askValidPlayStatus() {
        System.out.println("- Completed");
        System.out.println("- Currently playing");
        System.out.println("- On hold");
        System.out.println("- Plan to play");
        String initialPlayStatus = input.next().toLowerCase();
        if (!(initialPlayStatus.equals("completed") || initialPlayStatus.equals("currently playing")
                || initialPlayStatus.equals("on hold") || initialPlayStatus.equals("plan to play"))) {
            System.out.println("Invalid play status, please try one of the valid ones below.");
            askValidPlayStatus();
        }
        return initialPlayStatus;
    }

    // EFFECTS: saves the game catalogue to file
    protected void saveGameCatalogue() {
        try {
            jsonWriter.open();
            jsonWriter.write(gameCatalogue);
            jsonWriter.close();
            String saveMessage = "Saved " + gameCatalogue.getUsername() + "'s games to " + GAME_STORE;
            catalogueUI.message(saveMessage, "Saved File", "plain");
        } catch (FileNotFoundException e) {
            String errorMessage = "Unable to write to file: " + GAME_STORE;
            catalogueUI.message(errorMessage, "File Not Found", "error");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game catalogue from file
    protected void loadGameCatalogue(String currentName) {
        try {
            gameCatalogue = jsonReader.read();
            if (gameCatalogue.getAllGames().isEmpty()) {
                catalogueUI.message("There are no games to be loaded", "Empty Load FIle", "plain");
            } else {
                String loadMessage = "Loaded " + gameCatalogue.getUsername() + "'s games from previous" + GAME_STORE;
                gameCatalogue.changeUsername(currentName);
                catalogueUI.message(loadMessage, "Loaded File", "plain");
            }
        } catch (IOException e) {
            String unreadableMessage = "Unable to read from file: " + GAME_STORE;
            catalogueUI.message(unreadableMessage, "Unreadable File", "error");
        }
    }
}
