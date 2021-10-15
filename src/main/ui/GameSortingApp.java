package ui;

import model.Game;
import model.GameCatalogue;
import model.GameGenre;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static model.GameGenre.*;
import static model.PlayStatus.*;

// Represents the console interface of a game sorting application
public class GameSortingApp {
    private GameCatalogue gameCatalogue;
    private Scanner input;
    private boolean keepGoing;

    // EFFECTS: runs the game sorting application
    public GameSortingApp() {
        runGameSortingApp();
    }

    // EFFECTS: processes user input
    private void runGameSortingApp() {

        init();

        String command = null;

        displayAskUsername();

        while (keepGoing) {
            displayMainMenu();
            command = input.next();

            responseMainMenu(command);
        }
        System.out.println("\nGoodbye, " + gameCatalogue.getUsername() + "!  Happy gaming :)");
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner and keepGoing boolean
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        keepGoing = true;
    }

    // MODIFIES: this
    // EFFECTS: displays prompt for username, initializes catalogue with given username
    private void displayAskUsername() {
        System.out.println("\nWhat username would you like?");
        String name = input.next();
        while (name.isEmpty()) {
            System.out.println("\nWhat username would you like?");
            name = input.next();
        }
        gameCatalogue = new GameCatalogue(name);
    }


    // EFFECTS: displays main menu of game catalogue options to user
    private void displayMainMenu() {
        System.out.println("\nTo view games, press one of:");
        System.out.println("1. View all games");
        System.out.println("2. Search for a game");
        System.out.println("3. Search by genre");
        System.out.println("4. Search by playing status");
        System.out.println("\nTo edit games, press one of:");
        System.out.println("5. Add a game");
        System.out.println("6. Change a game's playing status");
        System.out.println("\nTo exit game catalogue, press 0");
    }

    // EFFECTS: processes user response on main menu
    private void responseMainMenu(String command) {
        if (command.equals("1")) {
            System.out.println(gameCatalogue.getAllGameTitles());
        } else if (command.equals("2")) {
            System.out.println("\nWhat game are you looking for?");
            System.out.println(gameCatalogue.searchGameDetails(input.next()));
        } else if (command.equals("3")) {
            System.out.println("\nWhich genre are you looking for?");
            assortValidGenre();
        } else if (command.equals("4")) {
            assortValidPlayStatus();
        } else if (command.equals("5")) {
            displayGamePrompt();
        } else if (command.equals("6")) {
            searchGameToChangeStatus();
        } else if (command.equals("0")) {
            keepGoing = false;
        }  else {
            System.out.println("\nInvalid input, please choose a number from 0 to 6.");
        }
    }

    // EFFECTS: processes user input to search by genre if the given genre is valid,
    //          if invalid, prompts for another attempt
    private void assortValidGenre() {
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
            assortValidGenre();
        } else {
            System.out.println(gameCatalogue.assortByGenre(givenGenre));
        }
    }

    // EFFECTS: processes user input to search by play status if given play status is valid,
    //          if invalid, prompts for another attempt
    private void assortValidPlayStatus() {
        System.out.println("- Completed");
        System.out.println("- Currently playing");
        System.out.println("- On hold");
        System.out.println("- Plan to play");
        String givenPlayStatus = input.next().toLowerCase();
        if (!(givenPlayStatus.equals("completed") || givenPlayStatus.equals("currently playing")
                || givenPlayStatus.equals("on hold") || givenPlayStatus.equals("plan to play"))) {
            System.out.println("Invalid play status, please try one of the valid ones below.");
            assortValidPlayStatus();
        } else {
            System.out.println(gameCatalogue.assortByPlayStatus(givenPlayStatus));
        }
    }

    // EFFECTS: displays prompts for game details to create the game and add it to the catalogue
    private void displayGamePrompt() {
        System.out.println("\nWhat's the name of the game?");
        String gameName = input.next();
        System.out.println("\nWho developed the game?");
        String gameDevName = input.next();
        GameGenre genre = askGenre();
        System.out.println("\nWhat platforms can you play this on? Please separate with commas.");
        List<String> gameAllPlatforms = Arrays.asList(input.next().split(","));
        int gameYear = askValidYear();
        Game newGame = new Game(gameName, gameDevName, genre, gameAllPlatforms, gameYear, PLAN_TO_PLAY);
        System.out.println("\nWhat is its playing status? Please type one of:");
        String initialPlayStatus = askValidPlayStatus();
        newGame.setPlayStatus(initialPlayStatus);
        gameCatalogue.addGame(newGame);
        System.out.println("\nThis game has just been added into your catalogue.");
    }

    // EFFECTS: returns GameGenre to corresponding string input.
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

    // EFFECTS: returns given genre string if valid, if invalid, asks for another genre attempt.
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

    // EFFECTS: if given string contains integer, returns it as an integer, otherwise returns invalid message
    //          and prompts for another attempt.
    private int askValidYear() {
        System.out.println("\nWhat year was this game released?");
        while (true) {
            String givenYear = input.next();
            try {
                int gameYear = Integer.parseInt(givenYear);
                return gameYear;
            } catch (Exception e) {
                System.out.println("Not a number, please enter a year.");
            }
        }
    }

    // EFFECTS:
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

    // EFFECTS:
    private void searchGameToChangeStatus() {
        System.out.println("\nWhich game would you like to change?");
        Game gameToChange = gameCatalogue.getGame(input.next());
        if (gameToChange == null) {
            System.out.println("\nGame not found in catalogue");
        } else {
            System.out.println("\nWhat is its new playing status?");
            changeValidGamePlayStatus(gameToChange);
        }
    }

    // EFFECTS: changes game play status if play status typed in is valid, if invalid,
    //          prompts for another attempt
    private void changeValidGamePlayStatus(Game gameToChange) {
        System.out.println("- Completed");
        System.out.println("- Currently playing");
        System.out.println("- On hold");
        System.out.println("- Plan to play");
        String givenPlayStatus = input.next().toLowerCase();
        if (!(givenPlayStatus.equals("completed") || givenPlayStatus.equals("currently playing")
                || givenPlayStatus.equals("on hold") || givenPlayStatus.equals("plan to play"))) {
            System.out.println("Invalid play status, please try one of the valid ones below.");
            changeValidGamePlayStatus(gameToChange);
        } else {
            gameToChange.setPlayStatus(givenPlayStatus);
        }
    }
}
